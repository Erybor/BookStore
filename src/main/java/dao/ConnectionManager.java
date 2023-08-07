package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {
    private static Connection mainConnection;
    private static Connection testConnection;

    public static Connection getConnection() {
        if (mainConnection != null) return mainConnection;
        try {
            Properties props = loadProperties();
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            mainConnection = DriverManager.getConnection(url, username, password);
            return mainConnection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Connection getTestConnection() {
        if (testConnection != null) return testConnection;
        try {
            Properties props = loadProperties();
            String url = props.getProperty("db.testurl");
            String username = props.getProperty("db.testusername");
            String password = props.getProperty("db.testpassword");

            testConnection = DriverManager.getConnection(url, username, password);
            return testConnection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/database.properties");
        props.load(fis);
        return props;
    }
}
