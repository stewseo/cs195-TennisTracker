package com.example.cs195tennis.database;

import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
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

        try (Connection connection = DatabaseConnection.connect()) {
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

    public static void main(String[]  args){

    }

    //create table from dao
    public static boolean createTable(String tableName, String[] columns) throws SQLException {

        int number = columns.length;

        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE " + tableName + "(ID INT, ");

        for (int i = 0; i < number; i++) {
            queryBuilder.append(columns[i]).append(" TEXT");
            if (i < number - 1) queryBuilder.append(", ");
        }

        queryBuilder.append(", PRIMARY KEY (ID))");

        Statement st = null;
        Connection conn = DatabaseConnection.connect();

            st = conn.createStatement();

            st.executeUpdate(queryBuilder.toString());

        return true;
    }

    //read csv in dao and insert to sql
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

        //TODO batch insert instead of singles.
        //TODO parse json to sql
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


    public static Map<String, List<Match>> readAll() {

        String query = "SELECT * FROM " + "Tournament";

        Map<String, List<Match>> allMatches = new HashMap<>();

        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String tourney_id = rs.getString("tourney_id");

                allMatches.computeIfAbsent(tourney_id, k-> new ArrayList<>());

                allMatches.get(tourney_id).add(new Match(
                        rs.getString(tourney_id),
                        rs.getString("tourney_name"),
                        rs.getString("surface"),
                        rs.getString("draw_size"),
                        rs.getString("tourney_level"),
                        rs.getString("tourney_date"),
                        rs.getString("match_num"),
                        rs.getString("winner_id"),
                        rs.getString("winner_seed"),
                        rs.getString("winner_entry"),
                        rs.getString("winner_name"),
                        rs.getString("winner_hand"),
                        rs.getString("winner_ht"),
                        rs.getString("winner_ioc"),
                        rs.getString("winner_age"),
                        rs.getString("loser_id"),
                        rs.getString("loser_seed"),
                        rs.getString("loser_entry"),
                        rs.getString("loser_name"),
                        rs.getString("loser_hand"),
                        rs.getString("winner_hand"),
                        rs.getString("loser_ht"),
                        rs.getString("loser_ioc"),
                        rs.getString("loser_age"),
                        rs.getString("score"),
                        rs.getString("best_of"),
                        rs.getString("round"),
                        rs.getString("minutes"),
                        rs.getString("w_ace"),
                        rs.getString("w_df"),
                        rs.getString("w_svpt"),
                        rs.getString("w_1stIn"),
                        rs.getString("w_1stWon"),
                        rs.getString("w_2ndWon"),
                        rs.getString("w_bpSaved"),
                        rs.getString("w_bpFaced"),
                        rs.getString("l_ace"),
                        rs.getString("l_df"),
                        rs.getString("l_svpt"),
                        rs.getString("l_1stIn"),
                        rs.getString("l_1stWon"),
                        rs.getString("l_2ndWon"),
                        rs.getString("l_SvGms"),
                        rs.getString("l_bpSaved"),
                        rs.getString("l_bpFaced"),
                        rs.getString("winner_rank"),
                        rs.getString("winner_rank_points"),
                        rs.getString("loser_rank"),
                        rs.getString("loser_rank_points")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }return allMatches;
    }

    public static Map<String, List<Match>> readWtaMatchesToMap() {
        String query = "SELECT * FROM " + "WTATournament";

        Map<String, List<Match>> allMatches = new HashMap<>();
        System.out.println(query);

        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {

                String tourney_id = rs.getString("tourney_id");

                allMatches.computeIfAbsent(tourney_id, k-> new ArrayList<>());

                allMatches.get(tourney_id).add(new Match(
                        rs.getString(tourney_id),
                        rs.getString("tourney_name"),
                        rs.getString("surface"),
                        rs.getString("draw_size"),
                        rs.getString("tourney_level"),
                        rs.getString("tourney_date"),
                        rs.getString("match_num"),
                        rs.getString("winner_id"),
                        rs.getString("winner_seed"),
                        rs.getString("winner_entry"),
                        rs.getString("winner_name"),
                        rs.getString("winner_hand"),
                        rs.getString("winner_ht"),
                        rs.getString("winner_ioc"),
                        rs.getString("winner_age"),
                        rs.getString("loser_id"),
                        rs.getString("loser_seed"),
                        rs.getString("loser_entry"),
                        rs.getString("loser_name"),
                        rs.getString("loser_hand"),
                        rs.getString("winner_hand"),
                        rs.getString("loser_ht"),
                        rs.getString("loser_ioc"),
                        rs.getString("loser_age"),
                        rs.getString("score"),
                        rs.getString("best_of"),
                        rs.getString("round"),
                        rs.getString("minutes"),
                        rs.getString("w_ace"),
                        rs.getString("w_df"),
                        rs.getString("w_svpt"),
                        rs.getString("w_1stIn"),
                        rs.getString("w_1stWon"),
                        rs.getString("w_2ndWon"),
                        rs.getString("w_bpSaved"),
                        rs.getString("w_bpFaced"),
                        rs.getString("l_ace"),
                        rs.getString("l_df"),
                        rs.getString("l_svpt"),
                        rs.getString("l_1stIn"),
                        rs.getString("l_1stWon"),
                        rs.getString("l_2ndWon"),
                        rs.getString("l_SvGms"),
                        rs.getString("l_bpSaved"),
                        rs.getString("l_bpFaced"),
                        rs.getString("winner_rank"),
                        rs.getString("winner_rank_points"),
                        rs.getString("loser_rank"),
                        rs.getString("loser_rank_points")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }      System.out.println(allMatches.size());
        return allMatches;
    }
}

