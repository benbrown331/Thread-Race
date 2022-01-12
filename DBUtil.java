/*
    Benjamin Brown
    */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static Connection connection;
    
    private DBUtil() {}

    public static synchronized Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        else {
            try {
                // set the db url, username, and password
                String username="admin";
                String password="password";
                String url = "jdbc:mysql://area-perimeterhw.cp79vduudv1c.us-east-1.rds.amazonaws.com/rectschema";
                // get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }            
        }
        return connection;
    }
    
    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;                
            }
        }
    }
    
}

