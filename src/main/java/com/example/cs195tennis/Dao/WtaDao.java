package com.example.cs195tennis.Dao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.SQLException;

public class WtaDao {

        WtaDao() {}

        public static ObservableList<Player> wtaObservablePlayer = FXCollections.observableArrayList();
        public static ObservableList<Match> wtaObservable = FXCollections.observableArrayList();
        static String wtaMatchCsvs = "C:\\Users\\seost\\Downloads\\tennis_wta-master\\wta_matches_";
        static String wtaPlayers = "C:\\Users\\seost\\Downloads\\tennis_wta-master\\wta_players.csv";

        public static Set<Match> keys = new HashSet<>();

        public static Map<String, List<Match>> queryWta(String tableName, String field, String indexFieldName, String index, Match match) throws SQLException {
            String id = match.getTourney_id();
            String tourneyDate = match.getTourney_date();
            String winner_name = match.getWinner_name();
            String loser_name = match.getLoser_name();

            Map<String, List<Match>> wtaMap = new HashMap<>();

            ResultSet rs = DataHandeler.read(tableName, field, indexFieldName, index);

            while (rs.next()) {

                String playerkey = rs.getString(id);

                wtaMap.computeIfAbsent(playerkey, k -> new ArrayList<>());

            }
            return wtaMap;
        }

        public static void main(String[] args) throws SQLException {
            System.out.println(getWtaList().size());
        }

        public static Map<String, List<Match>> getWtaList() throws SQLException {

            Map<String, List<Match>> wtaMap = new HashMap<>();

            return wtaMap;
        }

        public static void insert(String pre, int yrSt, int yrEnd, String suf) throws SQLException {

            for (int i = yrSt; i < yrEnd; i++) {
                StringBuilder sb = new StringBuilder(wtaMatchCsvs);
                sb.append(i).append(".csv");

                System.out.println(sb.toString());

                List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

                List<String[]> csvList = new ArrayList<>();

                try (CSVReader csvReader = new CSVReader(new FileReader(sb.toString()));) {

                    String[] values = null;

                    while ((values = csvReader.readNext()) != null) {
                        allMatchesCsv.add(Arrays.asList(values));
                        csvList.add(values);
                    }
                } catch (CsvValidationException | IOException e) {
                    e.printStackTrace();
                }
                DataHandeler.create("WTATournament", csvList);
            }
        }

        public static List<List<String>> readCSVRowsToList() throws SQLException {

            List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

            try (CSVReader csvReader = new CSVReader(new FileReader(wtaMatchCsvs));) {
                String[] values = null;

                while ((values = csvReader.readNext()) != null) {
                    allMatchesCsv.add(Arrays.asList(values));
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
            return allMatchesCsv;
        }

        public static Map<Integer, Map<Integer, List<Object>>> mapTempMaps() throws SQLException {

            Map<Integer, Map<Integer, List<Object>>> mapMaps = new HashMap<>();

            readCSVRowsToList().forEach(row -> {

                int key = Objects.hash(row.get(0));

                mapMaps.computeIfAbsent(key, k -> new HashMap<>());

                mapMaps.get(key).computeIfAbsent(key, k -> new ArrayList<>());

                mapMaps.get(key).get(key).add(new Match(row));

            });
            return mapMaps;
        }

        public static ObservableList<Player> readWtaPlyersToObservable() throws SQLException {
            Connection c = Database.connect();
            PreparedStatement ps = c.prepareStatement("select * in WtaPlayer");

            ResultSet rs = ps.executeQuery();

            Set<Player> keys = new HashSet<>();

            while (rs.next()) {
                keys.add(new Player(
                        rs.getString("player_id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("hand"),
                        rs.getString("dob"),
                        rs.getString("player_ioc"),
                        rs.getString("height"),
                        rs.getString("wikiData_id")
                        ));
            }

            wtaObservablePlayer.addAll(keys);
            return wtaObservablePlayer;
        }

        public static ObservableList<Match> readWtaMatchesToObservable() throws SQLException {

            Connection c = Database.connect();
            PreparedStatement ps = c.prepareStatement("select * from WtaTournament");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                wtaObservable.add(
                        new Match(
                                rs.getString("tourney_id"),
                                rs.getString("tourney_name"),
                                rs.getString("tourney_date"),
                                rs.getString("winner_name"),
                                rs.getString("loser_name"),
                                rs.getString("score"),
                                rs.getString("round"),
                                rs.getString("winner_id"),
                                rs.getString("loser_id")

                        ));
            }
            return wtaObservable;
        }

    public static ObservableList<String> getTournamentNames() {

        Set<String> set = new HashSet<>();
        ObservableList<String> tournamentNames = FXCollections.observableArrayList();
        wtaObservable.forEach(e-> set.add(e.getTourney_name()));
        tournamentNames.addAll(set);

        return tournamentNames;
    }
}



