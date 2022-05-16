package io.eho.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni";
        String user = "hbstudent";
        String password = "hbstudent";

        try {
            System.out.println("connecting to database" + jdbcUrl);

            Connection conn = DriverManager.getConnection(jdbcUrl, user,
                                                          password);

            System.out.println("connection successful!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
