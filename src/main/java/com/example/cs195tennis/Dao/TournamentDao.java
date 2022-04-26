package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.TournamentStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TournamentDao {

    private static final String tableName = "Tournaments";
    private static final String tourney_nameCol = "tourney_name";
    private static final String tourney_dateCol = "tourney_date";
    private static final String winner_nameCol = "winner_name";
    private static final String loser_nameCol = "loser_name";
    private static final String tourney_idCol = "tourney_id";
    private static String tournamentTable = "wta_matches_1990_to_2022";
    private static final ObservableList<TournamentStats> tournamentStatsObservable;
    private static List<String> tourneyList;


    static {
        tournamentStatsObservable = FXCollections.observableArrayList();
        updateTournamentFromDB();
    }

    public void prepareDummyQuery() throws SQLException {
        Connection c = DatabaseConnection.connect();
        PreparedStatement preparedStatement = c.prepareStatement("select * from UnknownTable where 1 = 0");
        ResultSetMetaData md = preparedStatement.getMetaData();
        int col = md.getColumnCount();
        int cType = md.getColumnType(1);
    }

    public void createTournamentTable() {
        String query = "Select * from " + tournamentTable;
    }

    private static void updateTournamentFromDB() {
        tourneyList = new ArrayList<>();
        String order;
        String dateRange = " WHERE tourney_date >= 20220101 ";
        String query = "SELECT * FROM " + tournamentTable;
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query+dateRange);
            ResultSet rs = statement.executeQuery();
            tournamentStatsObservable.clear();
            while (rs.next()) {
                tournamentStatsObservable.add(new TournamentStats(
                        rs.getString(tourney_nameCol),
                        rs.getString(tourney_dateCol),
                        rs.getString(winner_nameCol),
                        rs.getString(loser_nameCol),
                        rs.getString(tourney_idCol)));
                System.out.println("\nwinner rank: " + rs.getString("winner_rank"));
                tourneyList.add("winner name: " + rs.getString("winner_name"));
                tourneyList.add("winner country: " + rs.getString("winner_ioc"));
                tourneyList.add("winner id: " + rs.getString("winner_id"));
                tourneyList.add("winner dominant hand: " + rs.getString("winner_hand"));
                tourneyList.add("winner height " + rs.getString("winner_ht"));
                tourneyList.add("winner age " + rs.getString("winner_age"));
                tourneyList.add("match num: " + rs.getString("match_num"));
                tourneyList.add("surface type: " + rs.getString("surface"));
                tourneyList.add("draw size " + rs.getString("draw_size"));
                System.out.println(tournamentStatsObservable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(tourneyList.stream().collect(Collectors.joining(", ", "\nHead to Head Columns [ "," ]\n\n")));
        tournamentStatsObservable.clear();
    }

    public static void main(String[] args) throws SQLException {
    }

    public static ObservableList<TournamentStats> getTournament() {
        return FXCollections.unmodifiableObservableList(tournamentStatsObservable);
    }

    public static void insertTournament(String tourneyName, String tourneyDate, String winner_name, String loser_name) {

        int id = (int) DataHandeler.create(
                tableName,
                new String[]{"tourney_name", "tourney_date", "winner_name","loser_name"},
                new Object[]{tourneyName, tourneyDate, winner_name, loser_name},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});

        tournamentStatsObservable.add(new TournamentStats(
                tourney_nameCol,
                tourney_dateCol,
                winner_nameCol,
                loser_nameCol,
                tourney_idCol
        ))
        ;}


    public static Optional<TournamentStats> getTournament(String id) {
        for (TournamentStats tStats : tournamentStatsObservable) {
            if (Objects.equals(tStats.getTourney_id(), id)) return Optional.of(tStats);
        }
        return Optional.empty();
    }
}
