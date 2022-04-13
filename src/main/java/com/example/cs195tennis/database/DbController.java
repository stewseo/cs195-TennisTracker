package com.example.cs195tennis.database;

import java.sql.DatabaseMetaData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.DriverManager;


public class DbController {

    private static final Logger LOGGER = LogManager.getLogger(DbController.class.getName());
    private static final String driver = "org.sqlite.JDBC";
    private static final String url = "jdbc:sqlite:wta-tournaments.sqlite";
    private static Connection c = null;

    public DbController() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        c = DriverManager.getConnection(url);
        System.out.println("Connected");
    }


    private Set<String> getDBTables() throws SQLException {
        c = DriverManager.getConnection(url);
        Set<String> set = new HashSet<>();
        DatabaseMetaData dbmeta = c.getMetaData();
        readDBTable(set, dbmeta, "TABLE", null);
        return set;
    }


    private void readDBTable(Set<String> set, DatabaseMetaData dbmeta, String searchCriteria, String schema) throws SQLException {
        ResultSet rs = dbmeta.getTables(null, schema, null, new String[]{searchCriteria});
        while (rs.next()) {
            set.add(rs.getString("TABLE_NAME").toLowerCase());
        }
    }

    private void createTables(List<String> tableData) throws SQLException {
        c = DriverManager.getConnection(url);
        Statement statement = c.createStatement();
        statement.closeOnCompletion();
        for (String command : tableData) {
            System.out.println(command);
            statement.addBatch(command);
        }
        statement.executeBatch();
    }

    public ResultSet execQuery(String query) throws SQLException {
        c = DriverManager.getConnection(url);
        PreparedStatement statement = c.prepareStatement("SELECT * FROM wta_matches_1990_to_2022 WHERE winner_ioc = ?");
        statement.setString(1, query);

        var foundS = statement.executeQuery();

        return foundS;
    }

    public String CustomDateFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(date);
    }

    private void populateDatabase() throws SQLException {
        List<String> tableData = new ArrayList<>();
        Set<String> loadedTables = null;

        try {
            loadedTables = getDBTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Already loaded tables " + loadedTables);

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

}

