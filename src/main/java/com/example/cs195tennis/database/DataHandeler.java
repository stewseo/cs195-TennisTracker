package com.example.cs195tennis.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DataHandeler {

    private Database database;
    private final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getName());

    public DataHandeler() throws SQLException {
    }

    public ResultSet executeDbQuery(String query) throws SQLException {
        return database.execQuery(query);
    }

    public Database getDatabase() {
        return database;
    }

}
