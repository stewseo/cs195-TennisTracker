package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.AtpPlayer;
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


public class PlayerDao {

    static {

    }

    private static DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }

    public static void main(String[] args) {
        Result<Record> tables = ctx().select().fetch();
        List<Table<?>> r = ctx().meta().getTables();
        System.out.println(ctx().meta().getTables());

        System.out.println(ctx().meta().getSchemas());

        populateAtpPlayer();
        populatePlayerTable();

        System.out.println("meta master table: " + r);
    }

    public static ObservableList<AtpPlayer> observableAtpPlayer() {
        ObservableList<AtpPlayer> temp = FXCollections.observableArrayList();
        List<Table<?>> r = ctx().meta().getTables();

        System.out.println(r);

        Result<Record> playerTable = ctx().select().from("AtpPlayer").fetch();

        AtomicInteger i = new AtomicInteger();

        playerTable.stream().filter(Objects::nonNull).forEach(e -> {

            String id = e.getValue("player_id").toString();
            String firstName = e.getValue("name_first").toString();
            String lastName = e.getValue("name_last").toString();
            String fullName = firstName + " " + lastName;
            String dominantHand = e.getValue("hand").toString();
            String dateOfBirth = e.getValue("dob").toString();
            String location = e.getValue("ioc").toString();
            String height = e.getValue("height").toString();
            String wikiData = e.getValue("wikidata_id").toString();

            temp.add(new AtpPlayer(id, firstName, lastName, fullName, dominantHand, dateOfBirth, location, height, wikiData));
        });
        return temp;
    }

    public static ObservableList <PlayerRanking> oberservablePlayerRanking() {

        ObservableList<PlayerRanking> temp = FXCollections.observableArrayList();
        List<Table<?>> r = ctx().meta().getTables();

        System.out.println(r);

        Result<Record> playerRankTable = ctx().select().from("AtpPlayerRanking").fetch();


        playerRankTable.stream().filter(Objects::nonNull).forEach(e -> {

//            System.out.println(e.fieldsRow());

//            for(int i = 0; i< e.size(); i++) {
//                var va = e.getValue(i);
//            }
            String rankDate =   e.getValue("ranking_date").toString();
            String playerRank = e.getValue("rank").toString();
            String playerId = e.getValue("player").toString();
            String rankingPoints = e.getValue("points").toString();

            temp.add(new PlayerRanking(rankDate, playerRank, playerId,rankingPoints));

        });
        return temp;
    }

    public static void populateAtpPlayer() {
        List<Table<?>> r = ctx().meta().getTables();
        Result<Record> atpRankings = ctx().select().from("AtpPlayerRanking").fetch();

        Result<Record> wtaRankings = ctx().select().from("WTARank").fetch();
    }

    private static void populatePlayerTable() {
        ObservableList<Tournament> temp = FXCollections.observableArrayList();

        Result<Record> wtaPlayer = ctx().select().from("ATPPlayer").fetch();

        Result<org.jooq.Record> atpPlayer = ctx().select().from("WTAPlayers").fetch();

    }

}





