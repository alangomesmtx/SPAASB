package org.prth.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AppConfig {

    private static final Properties properties = new Properties();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("db.properties");
            if (input == null) {
                throw new RuntimeException("❌ 'db.properties' not found in classpath.");
            }

            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load DB config.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );
    }
}