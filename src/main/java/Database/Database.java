package Database;

import goal.DrivingGoal;
import goal.Goal;
import goal.HealthGoal;
import goal.SpendingGoal;
import member.Member;
import reward.Reward;
import reward.SubscriptionReward;
import reward.VoucherReward;
import session.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/DISCOVERY";
    private final String username = "postgres";
    private final String password = "postgres";
    private Connection connection;

    public Database() {
        openConnection();
        createAllTables();
    }

    private void openConnection() {
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Could not connect to database");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Connection could not be closed");
        }
    }

    private void createAllTables() {
        createMemberTable();
        createRewardTable();
        createVoucherRewardTable();
        createSubscriptionRewardTable();
        createRewardMemberTable();
        createGoalTable();
    }

    public boolean validateMemberAccount(Member member) {
        try {
            String sql = String.format("SELECT * FROM member WHERE id_number = '%s'", member.getIdNumber());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                if (result.getString(5).equals(member.getPassword()))
                    return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean memberIsInDatabase(Member member) {
        try {
            String sql = String.format("SELECT * FROM member WHERE id_number = '%s'", member.getIdNumber());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            return false;
        }
    }

    public List<Reward> listAllRewardsFromDatabase() {
        try {
            List<Reward> rewards = new ArrayList<>();
            String sql = "SELECT * FROM reward INNER JOIN subscription_reward ON " +
                    "reward.reward_id = subscription_reward.reward_id";
            Statement statement = connection.createStatement();
            ResultSet resultSubscription = statement.executeQuery(sql);
            while (resultSubscription.next()) {
                SubscriptionReward subscriptionReward = new SubscriptionReward(resultSubscription.getInt("reward_id")
                        , resultSubscription.getString("reward_description"), resultSubscription.getInt("mile_cost")
                        , resultSubscription.getString("reward_partner"), resultSubscription.getInt("months_subscription"));
                rewards.add(subscriptionReward);
                System.out.println("subscriptionReward.getItemDescription() = " + subscriptionReward.getItemDescription());
            }
            String sqlVoucher = "SELECT * FROM reward INNER JOIN voucher_reward ON " +
                    "reward.reward_id = voucher_reward.reward_id";
            ResultSet resultVoucher = statement.executeQuery(sqlVoucher);
            while (resultVoucher.next()) {
                VoucherReward voucherReward = new VoucherReward(resultVoucher.getInt("reward_id")
                        , resultVoucher.getString("reward_description"), resultVoucher.getInt("mile_cost")
                        , resultVoucher.getString("reward_partner"), resultVoucher.getInt("monetary_value"));
                rewards.add(voucherReward);
                System.out.println("voucherReward.getItemDescription() = " + voucherReward.getItemDescription());
            }
            return rewards;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Member createMemberFromDatabase(String idNumber){
        try {
            Member member;
            int memberID = 0;
            String sql = String.format("SELECT * FROM member WHERE id_number = '%s'", idNumber);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                memberID = result.getInt("member_id");
                member = new Member(result.getString("name"),result.getString("surname"),
                        result.getString("password"),result.getString("id_number"));
            } else {
                System.out.println("No member account");
                member = new Member("","","","");
            }
            member.setGoals(createGoalsFromDatabase(memberID));
            return member;
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return new Member("","","","");
        }
    }

    public List<Goal> createGoalsFromDatabase(int memberID){
        try {
            List<Goal> goals = new ArrayList<>();
            String sql = String.format("SELECT * FROM goal WHERE member_id = %d", memberID);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Goal goal = new Goal(result.getInt("goal_id"),result.getInt("points_necessary"),
                        result.getInt("points_earned"),result.getBoolean("goal_accomplished"),
                        result.getDate("start_date").toLocalDate());
                if (result.getString("goal_type").equals("DrivingGoal")){
                    DrivingGoal drivingGoal = (DrivingGoal)goal;
                    goals.add(drivingGoal);
                } else if (result.getString("goal_type").equals("HealthGoal")){
                    HealthGoal healthGoal = (HealthGoal)goal;
                    goals.add(healthGoal);
                } else {
                    SpendingGoal spendingGoal = (SpendingGoal)goal;
                    goals.add(spendingGoal);
                }
            }
            return goals;
        } catch (Exception e){
            System.out.println("Error loading goals");
            return new ArrayList<>();
        }
    }

    public int addMemberToDatabase(Member member) {
        int memberID = insertMember(member);
        insertRewards(member.getRewards(), memberID);
        insertGoals(member.getGoals(), memberID);
        return memberID;
    }

    //Update statements
    public void updateRecord(String sql) {
        try {
            System.out.println("sql = " + sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
        }
    }

    public void updateMiles(int memberID, int miles) {
        String sql = String.format("UPDATE member SET miles = %d WHERE member_id = %d", miles, memberID);
        updateRecord(sql);
    }

    public void updateGoal(Goal goal, int memberID) {
        String sql = String.format("UPDATE goal SET points_necessary = %d, points_earned = %d, goal_accomplished = %b, start_date = %tD WHERE member_id = '%s' AND goal_type = '%s'" +
                        goal.getTotalPoints(), goal.getPointsEarned(), goal.isGoalAccomplished(),
                goal.getStartDate(), memberID, goal.getClass());
        updateRecord(sql);
    }

    //Insert statements
    public int insertIntoTable(String sql) {
        try {
            System.out.println("sql = " + sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
            return 0;
        }
    }

    public void insertRewards(List<Reward> rewards, int memberID) {
        for (Reward reward : rewards) {
            insertRewardMember(reward, memberID);
        }
    }

    public void insertGoals(List<Goal> goals, int memberID) {
        for (Goal goal : goals) {
            if (inDatabase("goal", String.format("WHERE member_id = '%s' AND goal_type = '%s'", memberID, goal.getClass())))
                updateGoal(goal, memberID);
            else
                insertGoal(goal, memberID);
        }
    }

    public int insertMember(Member member) {
        String sql = String.format("INSERT INTO member(id_number, name, surname, password, miles) VALUES('%s','%s','%s','%s',%d)",
                member.getIdNumber(), member.getName(), member.getSurname(), member.getPassword(), member.getMiles());
        return insertIntoTable(sql);
    }

    public void insertGoal(Goal goal, int memberID) {
        String sql = String.format("INSERT INTO goal(member_id, goal_type, points_necessary, points_earned, goal_accomplished, start_date ) VALUES(%d,'%s',%d,%d,%b,%tD)",
                memberID, goal.getClass(), goal.getTotalPoints(), goal.getPointsEarned(), goal.isGoalAccomplished(),
                goal.getStartDate());
        insertIntoTable(sql);
    }

    public void insertRewardMember(Reward reward, int memberID) {
        if (reward.getRewardID() > 0) {
            reward.setRewardID(insertReward(reward));
        }
        if (!inDatabase("reward_member", String.format("WHERE member_id = %d and reward_id = %d", memberID, reward.getRewardID()))) {
            String sql = String.format("INSERT INTO reward_member(member_id, reward_id) VALUES(%d,%d)",
                    memberID, reward.getRewardID());
        }
    }

    public int insertReward(Reward reward) {
        String rewardType;
        if (reward instanceof VoucherReward) {
            rewardType = "Voucher";
        } else {
            rewardType = "Subscription";
        }
        String sql = String.format("INSERT INTO reward(reward_type, reward_description, reward_partner, mile_cost) VALUES('%s','%s','%s',%d)",
                rewardType, reward.getItemDescription(), reward.getRewardPartner(), reward.getMileCost());
        reward.setRewardID(insertIntoTable(sql));
        String subSQL;
        if (reward instanceof VoucherReward) {
            VoucherReward voucherReward = (VoucherReward) reward;
            subSQL = String.format("INSERT INTO voucher_reward(reward_id, monetary_value) VALUES(%d,%f)",
                    reward.getRewardID(), ((VoucherReward) reward).getMonetaryValue());
        } else {
            subSQL = String.format("INSERT INTO subscription_reward(reward_id, months_subscription) VALUES(%d,%d)",
                    reward.getRewardID(), ((SubscriptionReward) reward).getMonthsSubscription());
        }
        insertIntoTable(subSQL);
        return reward.getRewardID();
    }

    //Table creation
    //Working

    private void createTable(String tableName, String fields) {
        String sql = String.format("Create table %s(%s)", tableName, fields);
        try {
            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);
            sqlStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Could not create table");
        }
    }

    private void createMemberTable() {
        if (!doesExist("member")) {
            String tableName = "member";
            String fields = "member_id serial primary key, id_number varchar, name varchar, surname varchar, password varchar,miles int";
            createTable(tableName, fields);
            System.out.println("Member table created");
        }
    }

    private void createGoalTable() {
        if (!doesExist("goal")) {
            String tableName = "goal";
            String fields = "goal_id serial primary key, member_id int, goal_type varchar, points_necessary int, " +
                    "points_earned int, goal_accomplished boolean, start_date date";
            createTable(tableName, fields);
            System.out.println("Goal table created");
        }
    }

    private void createRewardTable() {
        if (!doesExist("reward")) {
            String tableName = "reward";
            String fields = "reward_id serial primary key, reward_type varchar, reward_description text, reward_partner varchar, mile_cost int";
            createTable(tableName, fields);
            System.out.println("Reward table created");
        }
    }

    private void createSubscriptionRewardTable() {
        if (!doesExist("subscription_reward")) {
            String tableName = "subscription_reward";
            String fields = "reward_id int, months_subscription int";
            createTable(tableName, fields);
            System.out.println("Subscription reward table created");
        }
    }

    private void createVoucherRewardTable() {
        if (!doesExist("voucher_reward")) {
            String tableName = "voucher_reward";
            String fields = "reward_id int, monetary_value double precision";
            createTable(tableName, fields);
            System.out.println("Voucher reward table created");
        }
    }

    private void createRewardMemberTable() {
        if (!doesExist("reward_member")) {
            String tableName = "reward_member";
            String fields = "reward_code serial primary key, reward_id int, member_id int";
            createTable(tableName, fields);
            System.out.println("Reward member table created");
        }
    }

    // Table / data existence
    //Working

    private boolean inDatabase(String tableName, String whereStatement) {
        return doesExist(String.format("%s %s", tableName, whereStatement));
    }

    private boolean doesExist(String sqlSection) {
        try {
            String sql = "SELECT * FROM " + sqlSection;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            return false;
        }
    }
}
