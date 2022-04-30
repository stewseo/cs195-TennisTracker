package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Tournament;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                tourneyList.add(rs.getString("tourney_date"));
                tourneyList.add(rs.getString("surface"));
                tourneyList.add(rs.getString("draw_size"));
                tourneyList.add(rs.getString("tourney_level"));

                System.out.println(tournamentStatsObservable);
            }
        }
    }

    static {
        tournamentStatsObservable = FXCollections.observableArrayList();
    }

}
