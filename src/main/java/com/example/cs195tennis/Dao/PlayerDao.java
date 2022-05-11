package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Device;
import com.example.cs195tennis.model.Person;
import com.example.cs195tennis.model.Rankings;
import com.example.cs195tennis.model.Player;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PlayerDao {
        public static ObservableList<Player> players = FXCollections.observableArrayList();
        private static String mainTable = "Player";
        private static List<Player> playerList;
        private static List<Rankings> playerRankList;
        private static final String id = "id";
        private static final String firstName = "firstName";
        private static final String lastName = "lastName";
        private static final String hand = "hand";
        private static final String dob = "dob";
        private static final String ioc = "ioc";
        private static final String height = "height";
        private static final String wikiData_id = "wikidata_id";
        private static final String player_Rank_Id = "ID";

        static String rankingTable = "Player_Rank";

    static String rankCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\atp_rankings_current.csv";

    public static Map<String, List<Player>> readCsvToMap() {

            List<List<String>> playerRankCsv = new ArrayList<List<String>>();

            Map<String, List<Player>> map = new HashMap<>();

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

            Map<String, List<Player>> playerMap = getPlayerMap();


            playerRankCsv.forEach(row -> {
                map.computeIfAbsent(row.get(0), k -> new ArrayList<>());

                map.get(row.get(0)).add(new Player(row.get(0), row.get(1), row.get(2), row.get(3)));
            });
        return map;
        }

        public static Map<String, List<Player>> getRankingList() {
            String query = "SELECT * FROM " + "RANK";
            Map<String, List<Player>> currentRank = new HashMap<>();

            try (Connection connection = DatabaseConnection.connect()) {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    System.out.println(rs);
                    currentRank.computeIfAbsent(rs.getString("ranking_date"), k->new ArrayList<>());

                    currentRank.get(rs.getString("ranking_date")).add(new Player(
                            rs.getString("ranking_date"),
                            rs.getString("rank"),
                            rs.getString("player"),
                            rs.getString("points")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }return currentRank;
        }

        String querys = " select"+ lastName+ " from " +"PLAYERS"+" join ranking on player.id = ranking.player_id where pos == 1 group by lastName; ";


        public static Map<String, List<Player>> getPlayerMap() {

            String query = "SELECT * FROM " + "Player";

            Map<String, List<Player>> player = new HashMap<>();

            try (Connection connection = DatabaseConnection.connect()) {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    String playerkey = rs.getString(id);

                    player.computeIfAbsent(playerkey, k-> new ArrayList<>());

                    player.get(playerkey).add(new Player(
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

            } catch (SQLException e) {
                e.printStackTrace();

            }return player;
        }

    public static List<String> query(String tourney_id, String[] s) throws SQLException {
            System.out.println(tourney_id);
            Connection c = DatabaseConnection.connect();
            PreparedStatement ps = c.prepareStatement("Select * from WtaTournament");
            ResultSet rs = ps.executeQuery();
            List<String> l = new ArrayList<>();

            while(rs.next()) {

                for (int i = s.length-1; i >= 0; i--) {
                    if(!s[i].equals("")) {
                        System.out.println(rs.getString(s[i]));
                        l.add(rs.getString(s[i]));
                    }
                }
            }return l;
    }
}





