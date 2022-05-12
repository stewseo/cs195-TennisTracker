package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MatchDao {

    MatchDao() {}

    static List<Match> matchList;
    public static ObservableList <Match> matches = FXCollections.observableArrayList();


    static String qualCsvs = "C:\\Users\\seost\\tennis_atp\\atp_matches_qual_chall_";
    static String atpMatches2022 = "C:\\Users\\seost\\tennis_atp\\atp_matches_";
    static String atpPlayers = "C:\\Users\\seost\\tennis_atp\\atp_players.csv";


    public static void insert(int yrStart, int yrEnd, String csv) throws SQLException {

        StringBuilder players = new StringBuilder(atpPlayers);

        for (int i = yrStart; i < yrEnd; i++) {
            StringBuilder sb = new StringBuilder(csv);
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
        DataHandeler.create("WTAPlayers", csvList);
        }
    }


    public static List<List<String>> readCSVRowsToList() throws SQLException {

        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        List<String[]> columns = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(atpMatches2022));) {

            String[] values = null;
            int i = 0;
            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return allMatchesCsv;
    }

    public static Map<Integer, Map<Integer, List<Object>>> mapTempMaps() throws SQLException {

        Map<Integer, Map<Integer, List <Object>>> mapMaps = new HashMap<>();

        readCSVRowsToList().forEach(row -> {

            int key = Objects.hash(row.get(0));

            mapMaps.computeIfAbsent(key, k -> new HashMap<>());

            mapMaps.get(key).computeIfAbsent(key, k -> new ArrayList<>());

            mapMaps.get(key).get(key).add(new Match(row));

        });
        return mapMaps;
    }


    public static Map<String, Map<String,List<Match>>> mapStatsToCategories() throws SQLException {

        Map<String, Map<String, List<Match>>> mapMaps = new HashMap<>();

        List <List<Match>> qualifierKeys = new ArrayList<>();

        readCSVRowsToList().forEach(row -> {

            String qualKey = row.get(0);

            System.out.print("qual tourney key " + qualKey);

            String tourney_id = row.get(0);

            mapMaps.computeIfAbsent(qualKey, k -> new HashMap<String, List<Match>>());

            mapMaps.get(qualKey).computeIfAbsent(tourney_id, k -> new ArrayList<>());

            mapMaps.get(qualKey).get(tourney_id).add(new Match(row));

        });
        return mapMaps;
    }

    public static Map<String, List<Match>> readAtpMatchToMap() throws FileNotFoundException, SQLException {

        Map<String, List<Match>> map = new HashMap<>();

        readCSVRowsToList().forEach(row -> {

            String tourneyDateName = row.get(0) + row.get(5);

            long key = Objects.hash(row.get(0),row.get(5));

            map.computeIfAbsent(row.get(0), k -> new ArrayList<>());

            map.get(row.get(0)).add(new Match(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4),row.get(5),row.get(6), row.get(7), row.get(8), row.get(9), row.get(10), row.get(11), row.get(12), row.get(13), row.get(14),
                            row.get(15), row.get(16), row.get(17),row.get(18), row.get(19), row.get(20), row.get(21), row.get(22),row.get(23),row.get(24), row.get(25), row.get(26),row.get(27),row.get(28), row.get(29), row.get(30),row.get(31),
                            row.get(32),row.get(33), row.get(34), row.get(35),row.get(36),row.get(37), row.get(38), row.get(39),row.get(40),
                            row.get(41),row.get(42), row.get(43), row.get(44),row.get(45),row.get(46), row.get(47), row.get(48)
            ));
            ;});

        return map;
    }

    public static Map<String, List<Match>> readWtaMatches() throws FileNotFoundException, SQLException {
        return DataHandeler.readWtaMatchesToMap();
    }
    public static void main(String[] args) throws SQLException {
        meta();
    }

    public static ObservableList<Match> getSeasonTotal() {
        ObservableList<Match> seasonTotal = FXCollections.observableArrayList();
        return seasonTotal;
    }


    public static void meta() throws SQLException {
        DatabaseMetaData md = Database.connect().getMetaData();
        ResultSet rs = md.getSchemas();
        System.out.println(rs.toString());

        while(rs.next()){
            System.out.println(rs.toString());

        }

    }

    public static ObservableList<Match> getTournamentNames() throws SQLException {

        Statement st = Database.connect().createStatement();

        ResultSet rs = st.executeQuery("Select * from WTATournament");

        Map<String, List<Match>> map = new HashMap<>();

        ObservableList<Match> temp = FXCollections.observableArrayList();

        Set<Match> tNames = new HashSet<>();

        while(rs.next()) {

            String key = rs.getString("tourney_id");
            String date = rs.getString("tourney_date");

//            int id = Objects.hash(key);



            map.computeIfAbsent(key, k -> new ArrayList<>());

            tNames.add(new Match(key, rs.getString("tourney_name"),date));


            map.get(key).add(new Match(key,
                    rs.getString("tourney_name"),
                    rs.getString("surface"),
                    rs.getString("draw_size"),
                    rs.getString("tourney_level"),
                    rs.getString("tourney_date"),
                    rs.getString("match_num"),
                    rs.getString("winner_id"),
                    rs.getString("winner_seed"),
                    rs.getString("winner_entry"),
                    rs.getString("winner_name"),
                    rs.getString("winner_hand"),
                    rs.getString("winner_ht"),
                    rs.getString("winner_ioc"),
                    rs.getString("winner_age"),
                    rs.getString("loser_id"),
                    rs.getString("loser_seed"),
                    rs.getString("loser_entry"),
                    rs.getString("loser_name"),
                    rs.getString("loser_hand"),
                    rs.getString("winner_hand"),
                    rs.getString("loser_ht"),
                    rs.getString("loser_ioc"),
                    rs.getString("loser_age"),
                    rs.getString("score"),
                    rs.getString("best_of"),
                    rs.getString("round"),
                    rs.getString("minutes"),
                    rs.getString("w_ace"),
                    rs.getString("w_df"),
                    rs.getString("w_svpt"),
                    rs.getString("w_1stIn"),
                    rs.getString("w_1stWon"),
                    rs.getString("w_2ndWon"),
                    rs.getString("w_bpSaved"),
                    rs.getString("w_bpFaced"),
                    rs.getString("l_ace"),
                    rs.getString("l_df"),
                    rs.getString("l_svpt"),
                    rs.getString("l_1stIn"),
                    rs.getString("l_1stWon"),
                    rs.getString("l_2ndWon"),
                    rs.getString("l_SvGms"),
                    rs.getString("l_bpSaved"),
                    rs.getString("l_bpFaced"),
                    rs.getString("winner_rank"),
                    rs.getString("winner_rank_points"),
                    rs.getString("loser_rank"),
                    rs.getString("loser_rank_points")
            ));
        }

        return temp;
    }

    public static ObservableList<Match> getMatchData() throws SQLException {
        Statement st = Database.connect().createStatement();
        ResultSet rs = st.executeQuery("Select * in Tournament");
        System.out.println(rs.toString());
        ObservableList<Match> temp = FXCollections.observableArrayList();

        return temp;
    }

}














