package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Multimap;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatchDao {

    MatchDao() {}

    static List<Match> matchList;
    public static ObservableList <Match> matches = FXCollections.observableArrayList();


    static String qualCsvs = "atp_matches_qual_chall_";

    static String atpMatches2022 = "C:\\Users\\seost\\tennis_atp\\atp_matches_2022.csv";
    static String wtaMatchCsvs = "C:\\Users\\seost\\Downloads\\tennis_wta-master\\wta_matches_2022.csv";
    static String wtaPlayers = "C:\\Users\\seost\\Downloads\\tennis_wta-master\\wta_players.csv";
    static String atpPlayers = "C:\\Users\\seost\\tennis_atp\\atp_players.csv";


    public static void insert() throws SQLException {

        int yrStart = 2018, yrEnd = 2022;
        StringBuilder players = new StringBuilder(wtaPlayers);

        for (int i = yrStart; i < yrEnd; i++) {
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
//      insert();
    }

    public static ObservableList<Match> getSeasonTotal() {
        ObservableList<Match> seasonTotal = FXCollections.observableArrayList();
        return seasonTotal;
    }
}














