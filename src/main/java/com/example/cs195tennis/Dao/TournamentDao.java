package com.example.cs195tennis.Dao;
import com.example.cs195tennis.Dao.DataModel.DataHandeler;
import com.example.cs195tennis.Dao.DataModel.TournamentRecord;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Tournament;
import com.example.cs195tennis.model.WtaMatch;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT;

public class TournamentDao {

    static DSLContext create() {
        try(Connection conn = Database.connect()) {

            return DSL.using(conn, SQLDialect.SQLITE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, List<TournamentRecord>> queryWta(String tableName, String field, String indexFieldName, String index, Match match) throws SQLException {
        String id = match.getTourney_id();
        String surface = match.getTourney_date();
        String level = match.getTourney_level();
        String date = match.getTourney_date();
        String winner_name = match.getWinnerName();
        String loser_name = match.getLoserName();

        Map<String, List<TournamentRecord>> mapGrandSlams = new HashMap<>();

        try (Stream<TournamentRecord> stream = create().selectFrom(TOURNAMENT).stream()) {
            stream.forEach(tournamentRecord -> {
                System.out.println(stream.toString());
            });
        }

        ResultSet rs = DataHandeler.read(tableName, field, indexFieldName, index);

        while (rs.next()) {

            String playerkey = rs.getString(id);

            mapGrandSlams.computeIfAbsent(playerkey, k -> new ArrayList<>());
        }
        return mapGrandSlams;
    }

    public static ObservableList<Tournament> getTournamentObservable() throws SQLException {
        Query query = create().select(TOURNAMENT.ID).from(TOURNAMENT);
        String sql = query.getSQL();
        List<Object> bindValues = query.getBindValues();
        TournamentRecord tournament = context.newRecord(TOURNAMENT.ID);


        Result<Record> authors = context.select()
                .from(TOURNAMENT.GRANDSLAMS)
                .fetch();


        ObservableList<Tournament> temp = FXCollections.observableArrayList();

        Set<WtaMatch> tNames = new HashSet<>();

        while(rs.next()) {
            temp.add(new Tournament(
                    rs.getString("tourney_id"),
                    rs.getString("tourney_name"),
                    rs.getString("surface"),
                    rs.getString("draw_size"),
                    rs.getString("tourney_level"),
                    rs.getString("tourney_date")
            ));
        }
        rs.close();
        return temp;
    }

    public void insert(String pre, int yrSt, int yrEnd, String suf, String wtaMatch) throws SQLException {

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

    public static ObservableList<Tournament> grandSlamTournamentsObservable(String table) throws SQLException {
        Connection c = Database.connect();

        ObservableList<Tournament> grandSlamObservable = FXCollections.observableArrayList();

        PreparedStatement ps = c.prepareStatement("select * from " + table);

        ResultSet rs = ps.executeQuery();

        Query query = create().select(TOURNAMENT.ID, TOURNAMENT.NAME, TOURNAMENT.DATE, TOURNAMENT.SURFACE, TOURNAMENT.DRAW_SIZE, TOURNAMENT.LEVEL, TOURNAMENT.DATE)
                .from(TOURNAMENT);


        String sql = query.getSQL();
        List<Object> bindValues = query.getBindValues();
        while (rs.next()) {
            grandSlamObservable.add(
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
        return grandSlamObservable;
    }

//    public static ObservableList<Tournament> getAllTournamentFieldsObservable() throws SQLException {
//        ResultSet rs = Database.connect().createStatement().executeQuery("SELECT * FROM Tournament");
//
//        Result<org.jooq.Record> result = create().fetch(rs);
//
//        Cursor<org.jooq.Record> cursor = create().fetchLazy(rs);
//
//        ObservableList<Tournament> wtaResultsObservable = FXCollections.observableArrayList();
//        wtaResultsObservable.addAll();
//        return wtaResultsObservable;
//    }

}



