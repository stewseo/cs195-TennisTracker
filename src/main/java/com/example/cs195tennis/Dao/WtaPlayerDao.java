package com.example.cs195tennis.Dao;

import com.example.cs195tennis.Dao.DataModel.DataHandeler;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.AtpPlayer;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.WtaPlayer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.using;

public class WtaPlayerDao {
    public static ObservableList<WtaPlayer> playerObservableList = FXCollections.observableArrayList();
    public static ObservableList<PlayerRanking> playerRanking = FXCollections.observableArrayList();
    private static String mainTable = "Player";
    private static final String id = "id", firstName = "firstName", lastName = "lastName", hand = "hand";
    private static final String dob = "dob";
    private static final String ioc = "ioc";
    private static final String height = "height";
    private static final String wikiData_id = "wikidata_id";
    private static final String player_Rank_Id = "ID";

    static String rankingTable = "Player_Rank";

    static String rankCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\atp_rankings_current.csv";


    public static Map<String, List<WtaPlayer>> getRankingList() {

        String query = "Select * From " + "WtayerRanking";

        Map<String, List<WtaPlayer>> currentRank = new HashMap<>();

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String id = rs.getString("ranking_date") + rs.getString("player");

                int key = Integer.parseInt(rs.getString("ranking_date") + rs.getString("player"));

                key ^= key >>> 16;

                System.out.println("key " + key);

                currentRank.computeIfAbsent(id, k -> new ArrayList<>());

                currentRank.get(rs.getString("ranking_date")).add(new WtaPlayer(
                        rs.getString("ranking_date"),
                        rs.getString("rank"),
                        rs.getString("player"),
                        rs.getString("points")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentRank;
    }

    String querys = " select" + lastName + " from " + "PLAYERS" + " join ranking on player.id = ranking.player_id where pos == 1 group by lastName; ";

    static ObservableList<PlayerRanking> getPlayerRanks() throws SQLException {
        playerRanking = FXCollections.observableArrayList();

        return playerRanking;
    }

    private void catalogueWta() {
        Result<Record> wtaPlayers = ctx().select().from("WtaPlayers").fetch();
        System.out.println("size of wta players " + wtaPlayers.size());
        Result<Record> wtaRank = ctx().select().from("WtaRank").fetch();
        System.out.println("size of wta rankings " + wtaRank.size());
        Result<Record> wtaTournament = ctx().select().from("WTATournament").fetchSize(100).fetch();
        System.out.println("size of wta tournaments " + wtaTournament.size());
    }


    private DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }
}






