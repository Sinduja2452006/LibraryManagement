package com.wipro.book.test;

import java.sql.Connection;
import com.wipro.book.util.DBUtil;

public class DBTest {
    public static void main(String[] args) {
        Connection con = DBUtil.getDBConnection();

        if (con != null) {
            System.out.println("✅ Database Connected Successfully");
        } else {
            System.out.println("❌ Database Connection Failed");
        }
    }
}
