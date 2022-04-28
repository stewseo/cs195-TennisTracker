package com.example.cs195tennis.Dao;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.TournamentStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TournamentDao {

    private static final String tableName = "Tournaments";
    private static final String tourney_nameCol = "tourney_name";
    private static final String tourney_dateCol = "tourney_date";
    private static final String winner_nameCol = "winner_name";
    private static final String loser_nameCol = "loser_name";
    private static final String tourney_idCol = "tourney_id";
    private static String tournamentTable = "Tournaments";
    private static final ObservableList<TournamentStats> tournamentStatsObservable;
    private static List<String> tourneyList;


    static {
        tournamentStatsObservable = FXCollections.observableArrayList();
    }


    private static void executeTourneyQuery() throws SQLException {

        String query = "SELECT * FROM " + tournamentTable;
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            tournamentStatsObservable.clear();
            while (rs.next()) {
                System.out.println(rs.getString("tourney_name"));
                tourneyList.add(rs.getString("tourney_name"));
                tourneyList.add(rs.getString("tourney_date"));
                tourneyList.add(rs.getString("winner_name"));
                tourneyList.add(rs.getString("loser_name"));
                tourneyList.add(rs.getString("tourney_id"));
                tourneyList.add(rs.getString("winner_age"));
                tourneyList.add(rs.getString("match_num"));
                tourneyList.add(rs.getString("surface"));
                tourneyList.add(rs.getString("draw_size"));
                System.out.println(tournamentStatsObservable);

            }
        }
    }
}
