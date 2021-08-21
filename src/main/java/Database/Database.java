package Database;

import member.Member;
import session.Session;

import java.sql.*;

public class Database {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/DISCOVERY";
    private final String username = "postgres";
    private final String password = "postgres";
    private Connection connection;

    public Database() {
        openConnection();
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

    public int insertMember(Member member) {
        String sql = String.format("INSERT INTO member(id_number, name, surname, miles) VALUES('%s','%s','%s',%d)",
                member.getIdNumber(), member.getName(), member.getSurname(), member.getMiles());
        return insertIntoTable(sql);
    }

    public int insertGoal(Session session, int goalNum, String goalType) {
        String sql = String.format("INSERT INTO goal(member_id, goal_type, points_necessary, points_earned, goal_accomplished, start_date ) VALUES(%d,'%s',%d,%d,%b,%tD)",
                session.getMemberID(), goalType, session.getMember().getGoals().get(goalNum).getTotalPoints(),
                session.getMember().getGoals().get(goalNum).getPointsEarned(), session.getMember().getGoals().get(goalNum).isGoalAccomplished(),
                session.getMember().getGoals().get(goalNum).getStartDate());
        return insertIntoTable(sql);
    }

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

    public void createMemberTable() {
        if (!doesExist("member")) {
            String tableName = "member";
            String fields = "member_id serial primary key, id_number varchar, name varchar, surname varchar, miles int";
            createTable(tableName, fields);
            System.out.println("Member table created");
        }
    }

    public void createGoalTable() {
        if (!doesExist("goal")) {
            String tableName = "goal";
            String fields = "goal_id serial primary key, member_id int, goal_type varchar, points_necessary int, " +
                    "points_earned int, goal_accomplished boolean, start_date date";
            createTable(tableName, fields);
            System.out.println("Goal table created");
        }
    }

    private boolean doesExist(String tableName) {
        try {
            String sql = "SELECT * FROM " + tableName;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            return false;
        }
    }
}
