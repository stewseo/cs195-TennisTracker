package com.example.cs195tennis.Dao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.WtaMatch;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class WtaMatchDao {

    public static ObservableList<WtaMatch> wtaObservable = FXCollections.observableArrayList();

    public static Map<String, List<WtaMatchDao>> queryWta(String tableName, String field, String indexFieldName, String index, Match match) throws SQLException {
        String id = match.getTourney_id();
        String tourneyDate = match.getTourney_date();
        String winner_name = match.getWinnerName();
        String loser_name = match.getLoserName();

        Map<String, List<WtaMatchDao>> wtaMap = new HashMap<>();

        ResultSet rs = DataHandeler.read(tableName, field, indexFieldName, index);

        while (rs.next()) {

            String playerkey = rs.getString(id);

            wtaMap.computeIfAbsent(playerkey, k -> new ArrayList<>());

        }
        return wtaMap;
    }


    public static ObservableList<WtaMatch> getTournamentNames() throws SQLException {

        Statement st = Database.connect().createStatement();

        ResultSet rs = st.executeQuery("Select * from WTATournament");

        Map<String, List<Match>> map = new HashMap<>();

        ObservableList<WtaMatch> temp = FXCollections.observableArrayList();

        Set<WtaMatch> tNames = new HashSet<>();

        while(rs.next()) {

            String key = rs.getString("tourney_id");

            String date = rs.getString("tourney_date");
//
//            map.computeIfAbsent(key, k -> new ArrayList<>());
//
//            tNames.add(new WtaMatch(key, rs.getString("tourney_name"),date));

//            map.get(key).add((key +
            System.out.println(" test ");
                    temp.add(new WtaMatch(
                            rs.getString("tourney_id"),
                            rs.getString("tourney_name"),
                            rs.getString("surface"),
                            rs.getString("draw_size"),
                            rs.getString("tourney_level"),
                            rs.getString("tourney_date")));
        };

        return temp;
    }

    public static void insert(String pre, int yrSt, int yrEnd, String suf, String wtaMatch) throws SQLException {

        for (int i = yrSt; i < yrEnd; i++) {
            StringBuilder sb = new StringBuilder(wtaMatch);
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

    public static List<List<String>> readCSVRowsToList(String url) throws SQLException {

        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(url));) {
            String[] values = null;

            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return allMatchesCsv;
    }


    public static ObservableList<WtaMatch> readWtaMatchesToObservable() throws SQLException {

        Connection c = Database.connect();
        PreparedStatement ps = c.prepareStatement("select * from WtaTournament");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            wtaObservable.add(
                    new WtaMatch(
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

    public static ObservableList<WtaMatch> getWtaMatchesCustom() {

        Set<WtaMatch> set = new HashSet<>();
        ObservableList<WtaMatch> tournamentNames = FXCollections.observableArrayList();

        tournamentNames.addAll(set);

        return tournamentNames;
    }


    public static ObservableList<WtaMatch> getTournamentResults(String tournamentId, String rangeYears, String filters) throws SQLException {


        ObservableList<WtaMatch> temp = FXCollections.observableArrayList();

        String.join(", " + Arrays.stream(new String[]{tournamentId,rangeYears,filters}).collect(Collectors.joining(", ")));

        ResultSet rs =
                Database.connect().prepareStatement("select * from "+ " Table " + " inner join " + " second param " + " where Tournament.id = " +  " param key ")
                        .executeQuery();

        int i = 0;

        while(rs.next()) {
            temp.add(new WtaMatch(rs.getObject(i++)));
        }
        return temp;
    }
}



