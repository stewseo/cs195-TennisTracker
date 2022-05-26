package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.Record;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.using;

//class that reads invokes Data Helper to read files,
public class TournamentDao {

    private static DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }
//Class that prepares data from ddatabase to model. loads and updates data to controller.
    public static void main(String[] args) {

        Result<Record> tables = ctx().select().fetch();

        List<Table<?>> r = ctx().meta().getTables();

//        populatePointByPointGrandSlams();
    }


    public static <T> ObservableList<Tournament> populateGrandSlam() {

        List<Table<?>> r = ctx().meta().getTables();

        ObservableList<Tournament> temp = FXCollections.observableArrayList();

        Result<Record> grandSlams = ctx().select().from("GrandSlams").fetch();

        String[] values = null;

//        loads data from ssqlite to controller.
        grandSlams.stream().filter(Objects::nonNull).forEach(e -> {"
            var winner = "null";
            int id = e.getValue(0).hashCode();
            String year = e.getValue("year").toString();
            String tourneyName = e.getValue("slam").toString();
            String match_num = e.getValue("match_num").toString();
            String player1 = e.getValue("player1").toString();
            String player2 = e.getValue("player2").toString();
            String status = e.getValue("status").toString();
            //loads Grand Slam Tournament data from db to controller.
            if(e.getValue("winner")!=null) {
                winner = e.getValue("winner").toString();
            } //
            String eventName = e.getValue("event_name").toString();
            String round = e.getValue("round").toString();
            String courtName = e.getValue("court_name").toString();
            String courtId = e.getValue("court_id").toString();
            String player1id = e.getValue("player1id").toString();
            String player2id = e.getValue("player2id").toString();
            String nation1 = e.getValue("nation1").toString();
            String nation2 = e.getValue("nation2").toString();

            Map<Integer, String> map = new HashMap<>();

            int k = id + eventName.hashCode();
            int kRound = id + round.hashCode();
            int kCourtName = id + courtName.hashCode();
            int kCourtId = id + courtId.hashCode();

            int key = id + "eventName".hashCode();
            map.put(k ,eventName);
            k = id + "round".hashCode();

            map.put(k, round);
            map.put(courtName.hashCode(), courtName);
            map.put(courtId.hashCode(), courtId);

            temp.add(new Tournament(id, year,tourneyName,courtId, courtName, new Player(player1id, player1, nation1), new Player(player2id, player2, nation2),
                    new Match(id, match_num,round, status, winner, eventName)));
        });
        return temp;
    }

    public static ObservableList<Tournament> populatePointByPointGrandSlams() {

        ObservableList<Tournament> pointByPoint = FXCollections.observableArrayList();

        Map<Integer, List<Record> > mapPointByPointGrandSlams = new HashMap<>();

        List<Record> list = ctx().selectQuery(table("GrandSlamPointByPoint")).fetch().stream().toList();

        AtomicInteger count = new AtomicInteger();

        List<String> columns = new ArrayList<>();

        list.forEach(e -> {


                int key = e.get(0).hashCode();
                key ^= key >>> 16;

                mapPointByPointGrandSlams.computeIfAbsent(key, v -> new ArrayList<Record>());
                mapPointByPointGrandSlams.get(key).add(e);

        });

        mapPointByPointGrandSlams.forEach((k,  v) -> {
            pointByPoint.addAll(new Tournament(v));
        });
        return pointByPoint;
    }

    private void createTempTables() {

        List<Table<?>> r = ctx().meta().getTables();
        Table<?> table = ctx().selectFrom("AtpPlayerRanking").asTable();
        Table<?> wtaRanks = ctx().selectFrom("WtaRank").asTable();
        Table<?> wtaTournaments = ctx().selectFrom("WTATournament").asTable();
    }

    private static void populateAtp() {
        Result<org.jooq.Record> players = ctx().select().from("ATPPlayer").fetchSize(50).fetch();

        Result<Record> atpRankings = ctx().select().from("AtpPlayerRanking").fetchSize(100).fetch();
    }

}





