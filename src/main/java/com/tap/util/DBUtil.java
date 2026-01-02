package com.tap.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL =
        "jdbc:mysql://localhost:3306/restaurant?useSSL=false&serverTimezone=UTC";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "Prenidarp234"; // 🔴 most systems use empty password

    private DBUtil() {}

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ DB CONNECTED");
            return con;
        } catch (Exception e) {
            System.out.println("❌ DB CONNECTION FAILED");
            e.printStackTrace();
            return null;
        }
    }
}
