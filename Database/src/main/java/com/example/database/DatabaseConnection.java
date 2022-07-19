package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.out;

public class DatabaseConnection {
    private static final String mySqldriver = "com.mysql.cj.jdbc.Driver";
    private static StringBuilder mySQLUrl;
    private static Connection connection;
    private static final String user = "root";
    private static final String password = "sesame";

    public static Connection connect(String dbName) {
        mySQLUrl = new StringBuilder("jdbc:mysql://localhost:3309/").append(dbName);

        connection = null;

        try {
            Class.forName(mySqldriver);
            out.println(mySQLUrl.toString());
            connection = DriverManager.getConnection(mySQLUrl.toString(), user, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
