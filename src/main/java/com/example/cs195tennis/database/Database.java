package com.example.cs195tennis.database;

import java.sql.*;
import java.util.Objects;

public class Database {

    private final String driver = "org.sqlite.JDBC";

    private static final String url = "jdbc:sqlite:Database/wta-tournaments.sqlite";

    public Database() throws SQLException {
    }

    public static Connection connect() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

