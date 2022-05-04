package com.example.cs195tennis.Dao;

import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.MatchStats;
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

    public static List<Match> allMatchesCsvToTournamentList() {
        return matchList;
    }


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

    public static List<MatchStats> writeMatchStatsFromCsv() throws FileNotFoundException {
        List<MatchStats> matchStats = new ArrayList<>();

        Match match = new Match();
        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(matchCsv));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        allMatchesCsv.forEach(row -> {

            matchStats.add(
                    new MatchStats(row.get(24), row.get(25), row.get(26),row.get(27),row.get(28), row.get(29), row.get(30),row.get(31),
                    row.get(32),row.get(33), row.get(34), row.get(35),row.get(36),row.get(37), row.get(38), row.get(39),row.get(40),
                            row.get(41),row.get(42), row.get(43), row.get(44),row.get(45),row.get(46), row.get(47), row.get(48)
                    ));}
        );
        ;;
        return matchStats;
    }

    public static List<Match> writeAllAtpMatchesToList() throws FileNotFoundException {
        List<Tournament> tournamentList = new ArrayList<>();

        List<Match> winnerList = new ArrayList<>();
        List<Match> loserList = new ArrayList<>();
        List<MatchStats> matchStats = new ArrayList<>();
        List<String[]> matchStat = new ArrayList<>();
        Match match = new Match();
        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(matchCsv));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        allMatchesCsv.forEach(row -> {

            tournamentList.add(
                    new Tournament(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6)));

            winnerList.add(
                    new Match(row.get(7), row.get(8), row.get(9), row.get(10), row.get(11), row.get(12), row.get(13), row.get(14)));

            loserList.add(
                    new Match(new String[]{row.get(16), row.get(17), row.get(18),row.get(19), row.get(20), row.get(21), row.get(22), row.get(23), row.get(24)}));

            matchStats.add(
                    new MatchStats(row.get(24), row.get(25), row.get(26),row.get(27),row.get(28), row.get(29), row.get(30),row.get(31)));

            matchStat.add(
                    match.setMatchStats(new String[]{row.get(32),row.get(33), row.get(34), row.get(35),row.get(36),row.get(37), row.get(38), row.get(39),row.get(40),
                            row.get(41),row.get(42), row.get(43), row.get(44),row.get(45),row.get(46), row.get(47), row.get(48)

                    }));

            ;});

        System.out.println(" Index is a row: " + tournamentList.size() + " columns per row");
        return winnerList;
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














