package com.example.cs195tennis.database;

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


public class DataHandeler {


    DataHandeler(){}

    public static ResultSet read(String tableName, String fieldName, String indexFieldName, Object index) {
        int indexDataType = 1;
        int fieldDataType = Types.VARCHAR;
        StringBuilder queryBuilder = new StringBuilder("Select ");
        queryBuilder.append(fieldName);
        queryBuilder.append(" from ");
        queryBuilder.append(tableName);
        queryBuilder.append(" where ");
        queryBuilder.append(indexFieldName);
        queryBuilder.append(" = ");
        queryBuilder.append(convertObjectToSQLField(index, indexDataType));

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            ResultSet rs = statement.executeQuery();
            return rs;
        }
        catch (SQLException exception) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not fetch from " + tableName + " by index " + index +
                            " and column " + fieldName);
            return null;
        }
    }

    public static void createTable(String tableName, List<String>columns) throws SQLException {

        int number = columns.size();

        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE " + tableName + "(ID INT, ");
        int i = 0;

        columns.forEach(e-> {
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


        for(int i = 0; i < cols; i++){
            queryBuilder.append("\"");
            queryBuilder.append(columns.get(0)[i]);
            queryBuilder.append("\"");
            if (i < cols - 1) queryBuilder.append(", ");
        }

        queryBuilder.append(") ");
        queryBuilder.append(" VALUES (");

        System.out.println(queryBuilder.toString());

        for(int i = 1; i < rows;  i++) {
            StringBuilder values = new StringBuilder(queryBuilder);
            for (int j = 0; j < columns.get(i).length; j++) {
                values.append("\"");
                values.append(columns.get(i)[j]);
                values.append("\"");
                if (j < cols - 1) values.append(", ");
            }
            values.append(")");
            inserts.add(values);
        }

        int index = 1;
        System.out.println(inserts.size());
        try (Connection conn = Database.connect()) {

            while(index < rows-1) {
                Statement st = conn.createStatement();

                st.executeUpdate(inserts.get(index++).toString());
            }
            inserts.forEach(System.out::println);
        }
        return true;
    }

    private static String convertObjectToSQLField(Object value, int type) {
        StringBuilder queryBuilder = new StringBuilder();
        switch (type) {
            case Types.VARCHAR -> {
                queryBuilder.append("'");
                queryBuilder.append(value);
                queryBuilder.append("'");
            }
            case Types.INTEGER -> queryBuilder.append(value);
            default -> throw new IllegalArgumentException("Index type " + type + " from sql.Types is not yet supported.");
        }
        return queryBuilder.toString();
    }

    private static List<List<String>> readCsvToDb(String csv) throws FileNotFoundException {

    List<List<String>> records = new ArrayList<List<String>>();

    try (CSVReader csvReader = new CSVReader(new FileReader(csv));) {

        String[] values = null;

        while ((values = csvReader.readNext()) != null) {

            records.add(Arrays.asList(values));
        }
    } catch (CsvValidationException | IOException e) {
        e.printStackTrace();
    }return records;
    }


    public static ObservableList<String> getQueryFields(String[] fields) throws SQLException {

        AtomicInteger i = new AtomicInteger();

        Statement st = Database.connect().createStatement();

        ResultSet rs = st.executeQuery("Select * from WTATournament");

        ObservableList<String> temp = FXCollections.observableArrayList();

        Set<String> set = new HashSet<>();

        while(rs.next()) {
            i.set(0);
            Arrays.stream(fields).forEach(e-> {
                try {
                    set.add(rs.getString(fields[i.getAndIncrement()].toString()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }});
        }
        temp.addAll(set);
        return temp;
    }

    String csv = "\"C:\\Users\\seost\\Downloads\\tennis_slam_pointbypoint-master\\tennis_slam_pointbypoint-master\\2011-ausopen-matches.csv\"";



    public static void buildPath(String s, String s1) throws SQLException, FileNotFoundException {
        String csv = s1.toLowerCase().substring(s1.length()-3);

        int csvSysHash = System.identityHashCode(csv.hashCode());
        System.out.println(csvSysHash);

        if (csvSysHash == System.identityHashCode("csv".hashCode())) {

            createTable(s, readCsvToDb(s1).get(0));
        }
    }

    public void loadCsv(){

    }


}

