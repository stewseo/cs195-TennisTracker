package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.AtpPlayer;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.WtaPlayer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AtpPlayerDao {


    ObservableList<PlayerRanking> playerRanking;

    String rankingTable = "Player_Rank";

    private Map<String, List<WtaPlayer>> readCsvToMap(String rankCsv) throws SQLException {

        List<List<String>> playerRankCsv = new ArrayList<List<String>>();

        Map<String, List<WtaPlayer>> map = new HashMap<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(rankCsv));) {

            String[] values = null;

            while ((values = csvReader.readNext()) != null) {

                playerRankCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        AtomicInteger i = new AtomicInteger(0);

        List<String> list = new ArrayList<>();

        List<AtpPlayer> playerList = getAtpObservablePlayer();

        playerRankCsv.forEach(row -> {

            map.computeIfAbsent(row.get(0), k -> new ArrayList<>());

            map.get(row.get(0)).add(new WtaPlayer(row.get(0), row.get(1), row.get(2), row.get(3)));
        });

        return map;
    }

        private ObservableList<AtpPlayer> observablePlayerList () throws SQLException {

            ObservableList<AtpPlayer> playerList = null;

            Connection c = Database.connect();

            PreparedStatement ps = c.prepareStatement("Select * in player");

            return playerList;
        }


        public static ObservableList<PlayerRanking> getAtpRanking () throws SQLException {

            ObservableList<PlayerRanking> playerRankingObservable = FXCollections.observableArrayList();

            String query = "Select * From " + "AtpPlayerRanking";

            ResultSet rs = Database.connect().prepareStatement(query).executeQuery();

            while (rs.next()) {
                playerRankingObservable.add(new PlayerRanking(rs.getString("ranking_date"),
                        rs.getString("rank"),
                        rs.getString("player"),
                        rs.getString("points")));
            }
            return playerRankingObservable;
        }

        private ObservableList<String> getPlayerNames () {

            Set<String> set = new HashSet<>();

            ObservableList<String> temp = FXCollections.observableArrayList();

            ObservableList<AtpPlayer> playerObservableList = FXCollections.observableArrayList();

            playerObservableList.forEach(e -> set.add(e.getFirstName() + " " + e.getLastName()));

            temp.addAll(set);

            return temp;
        }

        public static ObservableList<AtpPlayer> getAtpObservablePlayer () {

            String query = "SELECT * FROM " + "Player";

            ObservableList<AtpPlayer> playerObservableList = null;

//            playerObservableList = FXCollections.observableArrayList()) {

            try (Connection connection = Database.connect()) {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    playerObservableList.add(new AtpPlayer(
                            rs.getString("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("hand"),
                            rs.getString("dob"),
                            rs.getString("ioc"),
                            rs.getString("height"),
                            rs.getString("wikiData_id")
                    ));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return playerObservableList;
        }
    }










