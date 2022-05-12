package com.example.cs195tennis.database;

import java.sql.*;
import java.util.Objects;


public class Database {

    private final String driver = "org.sqlite.JDBC";
    private static final String url = "jdbc:sqlite:AtpWta.sqlite";

    public Connection c = DriverManager.getConnection(url);

    private static final String requiredTable = "Tournaments";

    public Database() throws SQLException {
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


    private static boolean checkTables() throws SQLException {
        String checkTables =
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" + requiredTable + "'";

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(checkTables);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getString("tbl_name").equals(requiredTable)) return true;
            }
            System.out.println("Could not find Table");
        }
        return false;
    }

    public static boolean isOK() throws SQLException {
        return checkTables();
    }


    public ResultSet execQuery(String qu) throws SQLException {
        String updateQ = "UPDATE wta_matches_1990_to_2022 set tourney_name=Australian Open";

        String query = "SELECT * FROM wta_matches_1990_to_2022 where tourney_date >= 2022000000";

        Connection connection = Database.connect();

        if(Objects.nonNull(connection)) {
            Statement statement = connection.createStatement();

            return statement.executeQuery(query);

        }else{
            System.out.println("Database or JDBC url error");
            return null;
        }
    }
}

