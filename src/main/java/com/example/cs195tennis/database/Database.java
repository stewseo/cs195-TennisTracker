package com.example.cs195tennis.database;

import java.sql.*;
import java.util.stream.Stream;

public class Database {

    private static final String driver = "org.sqlite.JDBC";

    private static final String url = "jdbc:sqlite:Database/wta-tournaments.sqlite";

    public Database() throws SQLException {
    }

    public static void main(String args) {
        connect();
    }



    public static Connection connect() {

        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(true);

//            DatabaseMetaData meta = connection.getMetaData();
//            meta.getDriverName();
//            ResultSet resultSet = meta.getTables(null, null, null, new String[]{"TABLE"});

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

