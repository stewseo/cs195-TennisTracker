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

public class AtpPlayerDao {

    public static ObservableList<Match> atpMatchObservable = FXCollections.observableArrayList();
    public static ObservableList<Player> atpRankObservable = FXCollections.observableArrayList();
    public static ObservableList<Player> atpPlayerObservable = FXCollections.observableArrayList();
    static String atpPlayerCsv = "C:\\Users\\seost\\tennis_atp\\atp_players.csv";
    static String atpPlayerRanking = "C:\\Users\\seost\\tennis_atp\\atp_rankings_current.csv";

    public static Set<Match> keys = new HashSet<>();


    public static void main(String[] args) throws SQLException {
        createAndInsert();

    }

    public static void insert(String pre, int yrSt, int yrEnd, String suf) throws SQLException {

        for (int i = yrSt; i < yrEnd; i++) {
            StringBuilder sb = new StringBuilder(atpPlayerCsv);
            sb.append(i).append(".csv");


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
        }
    }
        public static void createAndInsert() throws SQLException {

            List<List<String>> allMatchesCsv = new ArrayList<List<String>>();
            List<String[]> list = new ArrayList<>();
            try (CSVReader csvReader = new CSVReader(new FileReader(atpPlayerRanking));) {
                String[] values = null;

                while ((values = csvReader.readNext()) != null) {
                    allMatchesCsv.add(Arrays.asList(values));
                    list.add(values);
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
            DataHandeler.createTable("AtpPlayerRanking", allMatchesCsv.get(0));
            DataHandeler.create("AtpPlayerRanking",list);

        }
        public static List<List<String>> readCSVRowsToList() throws SQLException {

            List<List<String>> allMatchesCsv = new ArrayList<List<String>>();
            List<String[]> list = new ArrayList<>();
            try (CSVReader csvReader = new CSVReader(new FileReader(atpPlayerCsv));) {
                String[] values = null;

                while ((values = csvReader.readNext()) != null) {
                    allMatchesCsv.add(Arrays.asList(values));
                    list.add(values);
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
            DataHandeler.create("AtpPlayer",list);
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

        public static ObservableList<Player> getAtpPlayers() throws SQLException {

            Connection c = Database.connect();
            System.out.println("Here ");
            PreparedStatement ps = c.prepareStatement("select * from AtpPlayerRanking");

            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            Set<Player> keys = new HashSet<>();

            while (rs.next()) {
                System.out.println(rs);
                keys.add(new Player(
                        rs.getString("player_id"),
                        rs.getString("name_first"),
                        rs.getString("name_last"),
                        rs.getString("hand"),
                        rs.getString("dob"),
                        rs.getString("ioc"),
                        rs.getString("height"),
                        rs.getString("wikidata_id"),
                        rs.getString("rank")
                ));
            }
            atpPlayerObservable.addAll(keys);
            atpPlayerObservable.forEach(System.out::println);
            return atpPlayerObservable;
        }

}

