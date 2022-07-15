//package com.example.cs195tennis.Dao;
//
//import MySqlDatabase.Connection.Database;
//import com.example.cs195tennis.model.Match;
//import com.example.cs195tennis.model.Player;
//import com.example.cs195tennis.model.Tournament;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import org.jooq.*;
//import org.jooq.Record;
//import org.jooq.impl.DSL;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.jooq.impl.DSL.*;
//import static org.jooq.impl.DSL.field;
//
//public class TournamentDao {
//    Table<?> TABLE_GrandSlamPointByPoint, TABLE_GrandSlams;
//    private static DSLContext ctx() {
//        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
//        return ctx;
//    }
//    public static void main(String[] args) {
//        //Get table meta for GrandSlams
//        //Make table Object where a row is a match, and columns are data from Objects Tournament, Match, Player
//    }
//
//    public ObservableList<Record> getObservablePointByPointMatch() {
////        List<Table<?>> metaTab = ctx().meta().getTables().stream().filter(e->e.getName().equals("GrandSlams")).toList();
//        List<Table<?>> metaTabPt = ctx().meta().getTables().stream().filter(e->e.getName().equals("GrandSlamPointByPoint")).toList();
////        System.out.println(metaTab.get(0).fieldsRow());
//        System.out.println(metaTabPt.get(0).fieldsRow());
//
//        ObservableList<Record> observableList = FXCollections.observableArrayList();
//        Field<?>[] fieldsGrandSlam = new Field<?>[metaTabPt.get(0).fields().length + metaTabPt.get(0).fields().length];
//
//        for(int i = 0;i<metaTabPt.get(0).fields().length; i++) {
//            if(metaTabPt.get(0).fields()[i] != null) {
//                fieldsGrandSlam[i + metaTabPt.get(0).fields().length] = metaTabPt.get(0).fields()[i];
//            }
//        }
//
//        TABLE_GrandSlamPointByPoint = table("GrandSlamPointByPoint");
//        Result<?> result =
//                DSL.using(Database.connect(), SQLDialect.SQLITE)
//                        .select(fieldsGrandSlam)
//                        .from(TABLE_GrandSlamPointByPoint)
////                        .innerJoin(tableGrandSLamPtbyPt).on(field("GrandSlams.matchId").eq(field("GrandSlamPointByPoint.match_id")))
////                        .where(field("GrandSlams.matchId").notEqual("match_id"))
//                        .orderBy(field("GrandSlamPointByPoint.match_id"))
//                        .limit(80000)
//                        .fetch();
//
//        return observableList;
//    }
//
//    public ObservableList<Record> getRecentTournaments(){
//        Result<Record5<Object, Object, Object, Object, Object>> rs = ctx().select(field("slam"), field("year"), field("player1"), field("player2"), field("match_num")).from(table("GrandSlams"))
//                .innerJoin(table("AtpPlayer"))
//                .on(field("GrandSlams.player1").eq(field("AtpPlayer.name_first").concat(field("AtpPlayer.name_last"))))
//                .and(field("GrandSlams.winner").isNotNull())
//                .orderBy(field("year")).fetch();
//        ObservableList<Record> ol = FXCollections.observableArrayList();
//        ol.addAll(rs);
//        System.out.println("size: " +  ol.size());
//
//        assert ol.size() > 0;
//
//        return ol;
//    }
//    //db.schema.view
//    public ObservableList<Record> getFromThreeTables() {
//        int rs = ctx().createOrReplaceView("GrandSlams.slams","GrandSlams.year","GrandSlams.match_num","WTAPlayer.name_first")
//                .as(select(field("GrandSlams.ID"), field("GrandSlams.ID"),field("GrandSlams.ID"), field("GrandSlams.ID"),field("GrandSlams.ID"))
//                        .from(table("GrandSlams"))
//                        .join(table("WTAPlayers")).on(field("GrandSlams.ID").eq(field("GrandSlams.player1id"))))
//                        .execute();
//        System.out.println("rs: " + rs);
//
//        return null;
//    }
//
//
//    public static <T> ObservableList<Match> populateGrandSlam() {
//
//        List<Table<?>> r = ctx().meta().getTables();
//
//        ObservableList<Match> matchObservable = FXCollections.observableArrayList();
//
//        Result<Record> grandSlams = ctx().select().from("GrandSlams").fetch();
//        String[] values = null;
//
//        grandSlams.stream().filter(Objects::nonNull).forEach(e -> {
//
//            var winner =  e.getValue(field("winner"));
//            int id = e.getValue(0).hashCode();
//            var tourneyName =  e.getValue(field("slam"));
//            var tourneyDate =  e.getValue(field("year"));
//            var matchNum =  e.getValue(field("match_num"));
//
//            String status = e.getValue("status").toString();
//
//            List<Object> tournamentFields = new ArrayList<>();
//            tournamentFields.add(e.getValue(field("court_id")));
//            tournamentFields.add(e.getValue(field("court_name")));
//            tournamentFields.add(e.getValue(field("round")));
//            tournamentFields.add(e.getValue(field("event_name")));
//
//            List<Object> playerFields = new ArrayList<>();
//            playerFields.add(e.getValue(field("player1id")));
//            playerFields.add(e.getValue(field("player1")));
//            playerFields.add(e.getValue(field("nation1")));
//            playerFields.add(e.getValue(field("player2")));
//            playerFields.add(e.getValue(field("player2id")));
//            playerFields.add(e.getValue(field("nation2")));
//
//            if(winner != null) {
//                matchObservable.add(new Match(id, tourneyName, tourneyDate, tournamentFields, matchNum, winner, playerFields));
//            }
//            else {
//                matchObservable.add(new Match(id,  tourneyName, tourneyDate,tournamentFields, matchNum, playerFields));
//            }
//        });
//        return matchObservable;
//    }
//
//    public static ObservableList<Tournament> populatePointByPointGrandSlams() {
//
//        ObservableList<Tournament> pointByPoint = FXCollections.observableArrayList();
//
//        Map<Integer, List<Record> > mapPointByPointGrandSlams = new HashMap<>();
//
//        List<Record> list = ctx().selectQuery(table("GrandSlamPointByPoint")).fetch().stream().toList();
//
//        AtomicInteger count = new AtomicInteger();
//
//        List<String> columns = new ArrayList<>();
//
//        list.forEach(e -> {
//
//                int key = e.get(0).hashCode();
//                key ^= key >>> 16;
//
//                mapPointByPointGrandSlams.computeIfAbsent(key, v -> new ArrayList<Record>());
//                mapPointByPointGrandSlams.get(key).add(e);
//        });
//
//        return pointByPoint;
//    }
//
//    private void createTempTables() {
//
//        List<Table<?>> r = ctx().meta().getTables();
//        Table<?> table = ctx().selectFrom("AtpPlayerRanking").asTable();
//        Table<?> wtaRanks = ctx().selectFrom("WtaRank").asTable();
//        Table<?> wtaTournaments = ctx().selectFrom("WTATournament").asTable();
//    }
//
//    private static void populateAtp() {
//        Result<org.jooq.Record> players = ctx().select().from("ATPPlayer").fetchSize(50).fetch();
//
//        Result<Record> atpRankings = ctx().select().from("AtpPlayerRanking").fetchSize(100).fetch();
//    }
//
//}
//
//
//
//
//
