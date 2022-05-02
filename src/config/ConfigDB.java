package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    
private static String URL = "jdbc:postgresql://localhost:5432/postgres";
private static String USERNAME ="postgres";
private static String PASSWORD = "postgres";

public static Connection getConnection() throws SQLException{
    Connection connection = 
    DriverManager.getConnection(URL, USERNAME, PASSWORD);
    connection.setAutoCommit(false);
    return connection;
}

}
