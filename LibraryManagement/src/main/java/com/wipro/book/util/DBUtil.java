package com.wipro.book.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() {

        Connection con = null;
        try {
            System.out.println("1️⃣ Loading Oracle Driver");
            Class.forName("oracle.jdbc.driver.OracleDriver");

            System.out.println("2️⃣ Connecting to DB");
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@Sinduja2452006:1521/XE",
                "SYSTEM",
                "717823f253"
            );

            System.out.println("✅ DB CONNECTED SUCCESSFULLY");

        } catch (Exception e) {
            System.out.println("❌ DB CONNECTION FAILED");
            e.printStackTrace();
        }
        return con;
    }
}
