package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
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

    static {

    }

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

        Result<Record> qualifierTournaments = ctx().select().from("QualifierTournament").fetch();

        String[] values = null;


        AtomicInteger i = new AtomicInteger();
        qualifierTournaments.stream().filter(Objects::nonNull).forEach(e -> {

            if((i.getAndIncrement()) > 1000) return;

            int id = (int) e.getValue(0);
            String name = e.getValue("tourney_date").toString();
            String date = e.getValue("tourney_name").toString();
            String winnerName = e.getValue("winner_name").toString();
            String loserName = e.getValue("loser_name").toString();
//            Arrays.stream(e.fields()).skip(1);
            String tourneyLevel = e.getValue("tourney_level").toString();
            String surface = e.getValue("surface").toString();
            String drawSize = e.getValue("draw_size").toString();

            temp.add(new Tournament(id, name, date, new Player(winnerName, new PlayerRanking()), new Player(winnerName, new PlayerRanking()), new String[] {tourneyLevel, surface, drawSize}));

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
        System.out.println("size of atp player " + players.size());

        Result<Record> atpRankings = ctx().select().from("AtpPlayerRanking").fetchSize(100).fetch();
        System.out.println("size of atp rankings" + atpRankings.size());

        Result<Record> atpTournament = ctx().select().from("Tournament").fetch();
        System.out.println("size of Tournament" +atpTournament.size());
    }

}





