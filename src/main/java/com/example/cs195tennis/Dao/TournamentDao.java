package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.Record;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.jooq.impl.DSL.using;


public class TournamentDao {

    private static DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }

    public static void main(String[] args) {
        Result<Record> tables = ctx().select().fetch();
        List<Table<?>> r = ctx().meta().getTables();
        populateGrandSlam();
        System.out.println("meta master table: " + r);
    }

    public static ObservableList<Tournament> populateGrandSlam() {

        List<Table<?>> r = ctx().meta().getTables();

        ObservableList<Tournament> temp = FXCollections.observableArrayList();

        Result<Record> grandSlams = ctx().select().from("GrandSlams").fetch();

        String[] values = null;

        grandSlams.stream().filter(Objects::nonNull).forEach(e -> {

            var winner = "null";
            int id = e.getValue(0).hashCode();
            String year = e.getValue("year").toString();
            String tourneyName = e.getValue("slam").toString();
            String match_num = e.getValue("match_num").toString();
            String player1 = e.getValue("player1").toString();
            String player2 = e.getValue("player2").toString();
            String status = e.getValue("status").toString();
            if(e.getValue("winner")!=null) {
                winner = e.getValue("winner").toString();
            }
            String eventName = e.getValue("event_name").toString();
            String round = e.getValue("round").toString();
            String courtName = e.getValue("court_name").toString();
            String courtId = e.getValue("court_id").toString();
            String player1id = e.getValue("player1id").toString();
            String player2id = e.getValue("player2id").toString();
            String nation1 = e.getValue("nation1").toString();
            String nation2 = e.getValue("nation2").toString();
            System.out.println(match_num +" "+ status +" " + winner +" "+ eventName +" " + round +" "+ courtName);

            temp.add(new Tournament(id, year,tourneyName, new Player(player1id, player1, nation1), new Player(player2id, player2, nation2),
                    new Match(match_num,status, winner, new String[]{eventName,round,courtName,courtId})));
        });
        return temp;
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

        Result<Record> atpTournament = ctx().select().from("Tournament").fetch();
    }

}





