package Database;

import java.sql.Connection;

public class Database {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/DISCOVERY";
    private final String username = "postgres";
    private final String password = "postgres";
    private Connection connection;


}
