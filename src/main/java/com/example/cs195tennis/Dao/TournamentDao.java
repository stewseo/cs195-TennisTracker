package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.Tournament;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TournamentDao {

    private static final String tableName = "Tournament";
    private static final String tourney_idCol = "tourney_id";
    private static final String tourney_nameCol = "tourney_name";
    private static final String tourney_dateCol = "tourney_date";
    private static final String surfaceCol = "surface";
    private static final String draw_sizeCol = "draw_size";
    private static final String tourney_levelCol = "tourney_level";
    private static final String match_numCol = "match_num";
    private static String tournamentTable = "Tournament";
    private static final ObservableList<Tournament> tournamentStatsObservable;
    private static List<String> tourneyList;

    private static final String CREATE_TABLE_TOURNAMENT = "CREATE TABLE IF NOT EXISTS TOURNAMENT"
            + "("
            + " tourney_id INTEGER,"
            + " tourney_name TEXT NOT NULL,"
            + " tourney_date TEXT NOT NULL,"
            + " surface TEXT,"
            + " draw_size TEXT,"
            + " tourney_level TEXT,"
            + " match_num TEXT,"
            + " PRIMARY KEY (tourney_id)"
            + ")";

    public static void createTableTournament() {

        try(Connection c = DatabaseConnection.connect()) {

            Statement st = c.createStatement();

            st.executeUpdate(CREATE_TABLE_TOURNAMENT);

            System.out.println("Table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String insertTournamentSql = "INSERT INTO TOURNAMENT(tourney_id, tourney_name, tourney_date, surface, draw_size, tourney_level, match_num)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

//    private static void updateTableTournament() throws SQLException {
//        Statement st = null;
//
//        try (Connection c = DatabaseConnection.connect()) {
//
//            c.setAutoCommit(false);
//
//            PreparedStatement statement = c.prepareStatement(insertTournamentSql);
//
//            List<List<String>> fullMatchList = MatchDao.writeAllAtpMatchesToList();
//
//            fullMatchList.stream().map(Tournament -> {
//
//                    statement.setString(1,tourney_idCol),
//                            statement.setString(2, tourney_nameCol),
//                            statement.setString(3, tourney_dateCol),
//                            statement.setString(4, surfaceCol),
//                            statement.setString(5, draw_sizeCol),
//                            statement.setString(6, tourney_levelCol)
//                    );}
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//

    private static void executeTourneyQuery() throws SQLException {

        String query = "SELECT * FROM " + tournamentTable;
        try (Connection connection = DriverManager.getConnection("")) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            tournamentStatsObservable.clear();

            while (rs.next()) {
                System.out.println(rs.getString("tourney_id"));
                tourneyList.add(rs.getString("tourney_name"));
                tourneyList.add(rs.getString("surface"));
                tourneyList.add(rs.getString("draw_size"));
                tourneyList.add(rs.getString("tourney_date"));
                tourneyList.add(rs.getString("tourney_level"));
                tourneyList.add(rs.getString("match_num"));

                System.out.println(tournamentStatsObservable);
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        allMatchesCsvToTournamentList();
    }

    public static void insertToTournament() throws SQLException, IOException {
        Connection c = DatabaseConnection.connect();

        c.setAutoCommit(false);
        int batchSize = 20;
        String sql = "INSERT INTO Tournament (tourney_id, tourney_name, surface, draw_size, tourney_date, tourney_level, match_num) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = c.prepareStatement(sql);

        BufferedReader lineReader = new BufferedReader(new FileReader(matchCsv));
        String lineText = null;
        int count = 0;
        List<Tournament> tournamentList = new ArrayList<>();

        File inputF = new File(matchCsv);
        InputStream inputFS = new FileInputStream(inputF);
        Tournament tournament = new Tournament();

        lineReader.readLine();

        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            System.out.println(data.length);
            String tourney_id = data[0];
            String tourney_name = data[1];
            String surface = data[2];
            String draw_size = data[3];
            String tourney_date = data.length > 4 ? data[4] : "";
            String tourney_level  = data.length >  5 ? data[5] : "";
            String match_num = data.length > 6 ? data[6] : "";


            statement.setString(1, tourney_id);
            statement.setString(2, tourney_name);
            statement.setString(3, surface);
            statement.setString(4, draw_size);
            statement.setString(5, tourney_date);
            statement.setString(6, tourney_level);
            statement.setString(7, match_num);

            statement.addBatch();

            statement.executeBatch();
        }
        lineReader.close();

        statement.executeBatch();

        c.commit();
        c.close();
    }

    static String matchCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\atp_matches_2022.csv";

    public static List<Tournament> allMatchesCsvToTournamentList() throws FileNotFoundException, SQLException {
        Connection c = DatabaseConnection.connect();

        c.setAutoCommit(false);
        int batchSize = 20;
        String sql = "INSERT INTO Tournament (tourney_id, tourney_name, surface, draw_size, tourney_date, tourney_level, match_num) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = c.prepareStatement(sql);

        List<Tournament> tournamentList = new ArrayList<>();

        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(matchCsv));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        allMatchesCsv.forEach(row -> {

            tournamentList.add(
                    new Tournament(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6)));

            ;});

        tournamentList.forEach(e-> System.out.println(e.toString()));

        return tournamentList;
    }


    static {
        tournamentStatsObservable = FXCollections.observableArrayList();
    }

    public static List<Tournament> getTempList() {
        String query = "SELECT * FROM " + "Tournament";

        List<Tournament> tournamentList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                tournamentList.add(new Tournament(
                        rs.getString(tourney_idCol),
                        rs.getString(tourney_nameCol),
                        rs.getString(surfaceCol),
                        rs.getString(draw_sizeCol),
                        rs.getString(tourney_levelCol),
                        rs.getString(tourney_dateCol),
                        rs.getString(match_numCol)
                ));
            }
            System.out.println("Size of temp player list: " + tournamentList.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }return tournamentList;
    }
}
