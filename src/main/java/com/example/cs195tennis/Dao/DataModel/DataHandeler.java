package com.example.cs195tennis.Dao.DataModel;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Tournament;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.tools.jdbc.MockExecuteContext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jooq.impl.DSL.using;


public class DataHandeler {

    DataHandeler() {
    }


    public static void createTable(String tableName, List<String> columns) throws SQLException {

        int number = columns.size();

        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE " + tableName + "(ID INT, ");
        int i = 0;

        columns.forEach(e -> {
            queryBuilder.append(e).append(" TEXT");
            if (i < number - 1) {
                queryBuilder.append(", ");
            }
        });

        queryBuilder.append(" PRIMARY KEY (ID))");
        System.out.println(queryBuilder.toString());
        Database.connect()
                .prepareStatement(queryBuilder.toString())
                .execute();
    }

    public static boolean create(String tableName, List<String[]> columns) throws SQLException {

        int cols = columns.get(0).length, rows = columns.size() - 1;

        List<StringBuilder> inserts = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");


    }
}


