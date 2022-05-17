package com.example.cs195tennis.database;

import java.sql.*;
import java.util.Objects;

public class Database {

    private final String driver = "org.sqlite.JDBC";

    private static final String url = "jdbc:sqlite:Database/wta-tournaments.sqlite";

    public Database() throws SQLException {}

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public static boolean isOK() throws SQLException {
//        return checkTables();
//    }

//
//    public ResultSet execQuery(String qu) throws SQLException {
//        String updateQ = "UPDATE wta_matches_1990_to_2022 set tourney_name=Australian Open";
//
//        String query = "SELECT * FROM wta_matches_1990_to_2022 where tourney_date >= 2022000000";
//
//        Connection connection = Database.connect();
//
//        if(Objects.nonNull(connection)) {
//            Statement statement = connection.createStatement();
//
//            return statement.executeQuery(query);
//
//        }else{
//            System.out.println("Database or JDBC url error");
//            return null;
//        }
//    }

}

