package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.TournamentStats;
import com.opencsv.CSVReader;
import javafx.scene.chart.PieChart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchDao {

    List<Match> listMatches;

    MatchDao() {
    }

    private final String CREATE_TABLE_MATCH_ANALYSIS_OVERVIEW = "match_id,player, set, serve_pts,a ces,c,first_in,first_won,second_in,second_won,bk_pts,bp_saved,return_pts,return_pts_won, winners, winners_fh, winners_bh, unforced, unforced_fh, unforced_bh";

    private final String CREATE_TABLE_SHOT_TYPE = "shot_type";

    private final String CREATE_TABLE_SHOT_DIRECTION = "shot_direction";

    private final String CREATE_ = "match_id, row, pts,pts_won, first_in, aces, svc_winners,rally_winners, rally_forced, unforced, dfs";

    private final String CREATE_TABLE_STAT_OVERVIEW = "Stat Overview";

    static List<Match> matchList;
    private static final String CREATE_TABLE_KEY_POINT_OUTCOME = "CREATE TABLE KEY_POINT_OUTCOME"

            + "("
            + " ID match_id,"
            + " player TEXT NOT NULL,"
            + " row TEXT NOT NULL,"
            + " shots TEXT NOT NULL,"
            + " pt_ending,winners,,"
            + " induced_forced TEXT,"
            + " height TEXT,"
            + " first_won TEXT,"
            + " second_in_pts_won "
            + " second_wonEX T ";

    static String matchCsv = "cs195-TennisTracker\\src\\main\\resources\\com\\example";

    public static List<Player> getTempMatchList() {

        String query = "SELECT * FROM " + "Player";

        List<Player> matchList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                matchList.add(new Match(
                        rs.getString(id),
                        rs.getString(firstName),
                        rs.getString(lastName),
                        rs.getString(hand),
                        rs.getString(dob),
                        rs.getString(ioc),
                        rs.getString(height),
                        rs.getString(wikiData_id)
                ));
            }
            System.out.println("Size of temp player list: " + player.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }return player;
    }

}














