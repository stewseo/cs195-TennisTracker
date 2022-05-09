package com.example.cs195tennis.database;

import com.example.cs195tennis.model.Match;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataHandeler {

    DataHandeler(){}

    public static Object read(String tableName, String fieldName, String indexFieldName, Object index) {
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
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                switch (fieldDataType) {
                    case Types.INTEGER:
                        return rs.getInt(fieldName);
                    case Types.VARCHAR:
                        return rs.getString(fieldName);
                    default:
                        throw new IllegalArgumentException("Index type " + indexDataType + " from sql.Types is not yet supported.");
                }
            }
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not fetch from " + tableName + " by index " + index +
                            " and column " + fieldName);
            return null;
        }
    }

    public static void main(String[]  args){
    }

    public static boolean createTable(String tableName, String[] columns) throws SQLException {

        int number = columns.length;

        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE " + tableName + "(ID INT, ");

        for (int i = 0; i < number; i++) {
            queryBuilder.append(columns[i]).append(" TEXT");
            if (i < number - 1) queryBuilder.append(", ");
        }

        queryBuilder.append(", PRIMARY KEY (ID))");
        System.out.println(queryBuilder.toString());

        Statement st = null;
        Connection conn = DatabaseConnection.connect();

            st = conn.createStatement();

            st.executeUpdate(queryBuilder.toString());

        return true;
    }


    public static boolean create(String tableName, List<String[]> columns) throws SQLException {

        int cols = columns.get(0).length;

        int rows = columns.size() - 1;

        List<StringBuilder> inserts = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");

        for (int i = 0; i < cols; i++) {
            queryBuilder.append("\"");
            queryBuilder.append(columns.get(0)[i]);
            queryBuilder.append("\"");
            if (i < cols - 1) queryBuilder.append(", ");
        }
        queryBuilder.append(") ");
        queryBuilder.append(" VALUES (");

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
        System.out.println(inserts.get(2).toString());
        try (Connection conn = DatabaseConnection.connect()) {

            while(index < rows-1) {
                Statement st = conn.createStatement();
                st.executeUpdate(inserts.get(index++).toString());
            }
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

    private static void readCsvToDb() throws FileNotFoundException {

    List<List<String>> records = new ArrayList<List<String>>();

    try (CSVReader csvReader = new CSVReader(new FileReader("current-rankings.csv"));) {

        String[] values = null;

        while ((values = csvReader.readNext()) != null) {

            records.add(Arrays.asList(values));
        }
    } catch (CsvValidationException | IOException e) {
        e.printStackTrace();
    }
    }


}
