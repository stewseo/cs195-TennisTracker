package com.example.cs195tennis.Dao;

import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.Tournament;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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

    private static final String CREATE_TABLE_WINNER = "CREATE TABLE MATCH_WINNER"
            + "("
            + " winner_id PRIMARY KEY AUTOINCREMENT,"
            + " winner_seed TEXT,"
            + " winner_att TEXT,"
            + " winner_name TEXT,"
            + " winner_height TEXT,"
            + " winner_cc TEXT,"
            + " winner_age TEXT,"
            + " winner_rank TEXT,"
            + " winner_pts TEXT,";

    private static final String CREATE_TABLE_BAGELS_BY_YEAR = "CREATE TABLE BAGELS_BY_YEAR"
            + "("
            + " ID INT,"
            + " tourney_id TEXT NOT NULL,"
            + " tourney_name TEXT NOT NULL,"
            + " surface TEXT,"
            + " field TEXT,"
            + " tourney_ley TEXT,"
            + " tourney_date TEXT,"
            + " match_num TEXT,"
            + " winner_id,"
            + " winner_seed TEXT,"
            + " winner_att TEXT,"
            + " winner_name TEXT,"
            + " winner_height TEXT,"
            + " winner_cc TEXT,"
            + " winner_age TEXT,"
            + " winner_rank TEXT,"
            + " winner_pts TEXT,"
            + " PRIMARY KEY (ID)"
            + ")";

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

    static String matchCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis\\atp_matches_2022.csv";


    public List<String[]> oneByOne(Reader reader) throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        reader.close();
        csvReader.close();
        return list;
    }

    public static List<List<String>> writeAllAtpMatchesToList() throws FileNotFoundException {
        List<Tournament> tournamentList = new ArrayList<>();
        List<Tournament> tournamentWinnerList;
        List<Tournament> tournamentLoserList;
        List<Match> matchStats = new ArrayList<>();

        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(matchCsv));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        allMatchesCsv.forEach(row ->
                tournamentList.add(
                        new Tournament(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5))));

        System.out.println((tournamentList.get(0).getTourney_id()));
        tournamentList.forEach(System.out::println);
        System.out.println(" matches cache " + allMatchesCsv.size());
        System.out.println(" Index is a row: " + tournamentList.size() + " columns per row");
        return allMatchesCsv;
    }

    public static void main(String[] args) throws FileNotFoundException {
        writeAllAtpMatchesToList();
    }

//    public static List<Player> getTempMatchList() {
//
//        String query = "SELECT * FROM " + "Match";
//
//        List<Player> matchList = new ArrayList<>();
//        try (Connection connection = DatabaseConnection.connect()) {
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                matchList.add(new Match(
//
//                ));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }return matchList;
//    }

}














