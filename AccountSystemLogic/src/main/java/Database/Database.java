package Database;

import goal.DrivingGoal;
import goal.Goal;
import goal.HealthGoal;
import goal.SpendingGoal;
import member.Member;
import reward.Reward;
import reward.SubscriptionReward;
import reward.VoucherReward;

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

    public boolean validateMemberAccount(String idNumber, String password) {
        try {
            String sql = String.format("SELECT * FROM member WHERE id_number = '%s'", idNumber);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                return result.getString(5).equals(password);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean memberIsInDatabase(String idNumber) {
        try {
            String sql = String.format("SELECT * FROM member WHERE id_number = '%s'", idNumber);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            return false;
        }
    }

    public int findMemberIDFromDatabase(Member member) {
        try {
            String sql = String.format("SELECT * FROM member WHERE id_number = '%s'", member.getIdNumber());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();
            return result.getInt("member_id");
        } catch (Exception e) {
            return -1;
        }
    }

    public int findIDFromDatabase(String tableName, String whereStatement) {
        try {
            String sql = String.format("SELECT * FROM %s %s", tableName, whereStatement);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();
            return result.getInt(1);
        } catch (Exception e) {
            return -1;
        }
    }

    //Works
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

    public Member createMemberFromDatabase(String idNumber) {
        try {
            Member member;
            int memberID = 0;
            String sql = String.format("SELECT * FROM member WHERE id_number = '%s'", idNumber);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                memberID = result.getInt("member_id");
                member = new Member(result.getString("name"), result.getString("surname"),
                        result.getString("password"), result.getString("id_number"), result.getInt("miles"));
            } else {
                System.out.println("No member account");
                member = new Member("", "", "", "");
            }
            member.setRewards(createRewardsFromDatabase(memberID));
            member.setGoals(createGoalsFromDatabase(memberID));
            return member;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return new Member("", "", "", "");
        }
    }

    public List<Goal> createGoalsFromDatabase(int memberID) {
        try {
            List<Goal> goals = new ArrayList<>();
            String sql = String.format("SELECT * FROM goal WHERE member_id = %d", memberID);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                switch (result.getString("goal_type")) {
                    case "Driving":
                        DrivingGoal drivingGoal = new DrivingGoal(result.getInt("points_necessary"),
                                result.getInt("points_earned"), result.getBoolean("goal_accomplished"),
                                result.getDate("start_date").toLocalDate(), result.getInt("goal_id"));
                        goals.add(drivingGoal);
                        break;
                    case "Health":
                        HealthGoal healthGoal = new HealthGoal(result.getInt("points_necessary"),
                                result.getInt("points_earned"), result.getBoolean("goal_accomplished"),
                                result.getDate("start_date").toLocalDate(), result.getInt("goal_id"));
                        goals.add(healthGoal);
                        break;
                    case "Spending":
                        SpendingGoal spendingGoal = new SpendingGoal(result.getInt("points_necessary"),
                                result.getInt("points_earned"), result.getBoolean("goal_accomplished"),
                                result.getDate("start_date").toLocalDate(), result.getInt("goal_id"));
                        goals.add(spendingGoal);
                        break;
                }
            }
            return goals;
        } catch (Exception e) {
            System.out.println("Error loading goals");
            return new ArrayList<>();
        }
    }

    public List<Reward> createRewardsFromDatabase(int memberID) {
        try {
            List<Reward> rewards = new ArrayList<>();
            List<Reward> possibleRewards = this.listAllRewardsFromDatabase();
            String sql = String.format("SELECT * FROM reward_member  WHERE member_id = %d", memberID);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println("result.getInt(\"reward_id\") = " + result.getInt("reward_id"));
                int rewardID = result.getInt("reward_id");
                Reward rewardToAdd = possibleRewards.stream()
                        .filter(reward -> reward.getRewardID() == rewardID)
                        .findFirst()
                        .orElse(new VoucherReward(-1, "", 0, "", 0));
                rewards.add(rewardToAdd);
            }
            return rewards;
        } catch (SQLException e) {
            System.out.println("Error loading goals");
            return new ArrayList<>();
        }
    }

    public int addMemberToDatabase(Member member) {
        if (this.memberIsInDatabase(member.getIdNumber())) {
            int memberID = findMemberIDFromDatabase(member);
            updateMiles(memberID, member.getMiles());
            insertRewards(member.getRewards(), memberID);
            insertGoals(member.getGoals(), memberID);
            return memberID;
        } else {
            int memberID = insertMember(member);
            insertRewards(member.getRewards(), memberID);
            insertGoals(member.getGoals(), memberID);
            return memberID;
        }
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("UPDATE goal SET " +
                            "points_necessary = %d, points_earned = %d, goal_accomplished = %b, start_date = ? WHERE member_id = '%s' AND goal_type = '%s'"
                    , goal.getTotalPoints(), goal.getPointsEarned(), goal.isGoalAccomplished(), memberID, goal.getGoalType()));
            preparedStatement.setDate(1, java.sql.Date.valueOf(goal.getStartDate()));
            insertIntoTableWithPreparedStatement(preparedStatement);
        } catch (Exception e) {
            System.out.println("e = " + e.getLocalizedMessage());
        }
    }

    //Insert statements
    private int insertIntoTable(String sql) {
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

    private void insertIntoTableWithPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
        }
    }

    private int insertMember(Member member) {
        String sql = String.format("INSERT INTO member(id_number, name, surname, password, miles) VALUES('%s','%s','%s','%s',%d)",
                member.getIdNumber(), member.getName(), member.getSurname(), member.getPassword(), member.getMiles());
        return insertIntoTable(sql);
    }

    private void insertRewards(List<Reward> rewards, int memberID) {
        for (Reward reward : rewards) {
            insertRewardMember(reward, memberID);
        }
    }

    private void insertGoals(List<Goal> goals, int memberID) {
        for (Goal goal : goals) {
            if (inDatabase("goal", String.format("WHERE member_id = '%s' AND goal_type = '%s'", memberID, goal.getGoalType())))
                updateGoal(goal, memberID);
            else
                insertGoal(goal, memberID);
        }
    }

    private void insertGoal(Goal goal, int memberID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("INSERT INTO goal(member_id, goal_type, points_necessary, points_earned," +
                    " goal_accomplished, start_date ) VALUES(%d,'%s',%d,%d,%b,?)", memberID, goal.getGoalType(), goal.getTotalPoints(), goal.getPointsEarned(), goal.isGoalAccomplished()));
            preparedStatement.setDate(1, java.sql.Date.valueOf(goal.getStartDate()));
            insertIntoTableWithPreparedStatement(preparedStatement);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    private void insertRewardMember(Reward reward, int memberID) {
        if (reward.getRewardID() > 0) {
            reward.setRewardID(insertReward(reward));
        }
        if (!inDatabase("reward_member", String.format("WHERE reward_id = %d and member_id = %d", reward.getRewardID(), memberID))) {
            String sql = String.format("INSERT INTO reward_member(reward_id, member_id) VALUES(%d,%d)",
                    reward.getRewardID(), memberID);
            insertIntoTable(sql);
            System.out.println("sql = " + sql);
        }
    }

    public int insertReward(Reward reward) {
        String whereStatement = String.format("WHERE reward_description = '%s' AND reward_partner = '%s' AND mile_cost = %d", reward.getItemDescription(), reward.getRewardPartner(), reward.getMileCost());
        if (this.inDatabase("reward", whereStatement)) {
            return findIDFromDatabase("reward", whereStatement);
        }

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
            String fields = "goal_id serial primary key, member_id int, foreign key(member_id) references member(member_id)" +
                    ", goal_type varchar, points_necessary int, " +
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
            String fields = "reward_id int, foreign key(reward_id) references reward(reward_id), months_subscription int";
            createTable(tableName, fields);
            System.out.println("Subscription reward table created");
        }
    }

    private void createVoucherRewardTable() {
        if (!doesExist("voucher_reward")) {
            String tableName = "voucher_reward";
            String fields = "reward_id int, foreign key(reward_id) references reward(reward_id), monetary_value double precision";
            createTable(tableName, fields);
            System.out.println("Voucher reward table created");
        }
    }

    private void createRewardMemberTable() {
        if (!doesExist("reward_member")) {
            String tableName = "reward_member";
            String fields = "reward_code serial primary key, reward_id int, " +
                    "foreign key(reward_id) references reward(reward_id), " +
                    "member_id int, foreign key(member_id) references member(member_id)";
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