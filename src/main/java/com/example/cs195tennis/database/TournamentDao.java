package com.example.cs195tennis.database;

import com.example.cs195tennis.model.TournamentStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class TournamentDao {

    //Use this as entry model
    private static final String tableName = "Tournaments";
    private static final String tourney_nameCol = "tourney_name";
    private static final String tourney_dateCol = "tourney_date";
    private static final String winner_nameCol = "winner_name";
    private static final String loser_nameCol = "loser_name";
    private static final String tourney_idCol = "tourney_id";

    private static final ObservableList<TournamentStats> tournamentStatsObservable;

    static {
        tournamentStatsObservable = FXCollections.observableArrayList();
        updateTournamentFromDB();
    }

    //Entry model for tournament. match_id will probably be showed with this in our tables and searchboxes
    private static void updateTournamentFromDB() {
        String query = "SELECT * FROM " + tableName;
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            tournamentStatsObservable.clear();
            while (rs.next()) {
                tournamentStatsObservable.add(new TournamentStats(
                        rs.getString(tourney_nameCol),
                        rs.getString(tourney_dateCol),
                        rs.getString(winner_nameCol),
                        rs.getString(loser_nameCol),
                        rs.getString(tourney_idCol)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tournamentStatsObservable.clear();
    }
    public static void main(String[] args) throws SQLException {
       insertTournament("US Open", "1990", "Winner name", "Loser name");
    }

    public static ObservableList<TournamentStats> getTournament() {
        return FXCollections.unmodifiableObservableList(tournamentStatsObservable);
    }

    public static void insertTournament(String tourneyName, String tourneyDate, String winner_name, String loser_name) {
        //update database
        int id = (int) DataHandeler.create(
                tableName,
                new String[]{"tourney_name", "tourney_date", "winner_name","loser_name"},
                new Object[]{tourneyName, tourneyDate, winner_name, loser_name},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});

        //update cache
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
