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
import org.jooq.impl.DSL;

import java.util.*;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.table;


public class PlayerDao {

    static {

    }

    public static DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }


    public static void main(String[] args) {

        List<Table<?>> metaPlayer = ctx().meta().getTables().stream().filter(e->e.getName().equals("AtpPlayerRanking")).toList();
        List<Table<?>> metaRank = ctx().meta().getTables().stream().filter(e->e.getName().equals("AtpPlayer")).toList();

        Table<?> tableAtpPlayerRank = table("AtpPlayerRanking");
        Table<?> tableAtpPlayer = table("AtpPlayer");

        Field<?>[] fieldsPlayerRank = new Field<?>[metaRank.get(0).fields().length + metaPlayer.get(0).fields().length];
        System.out.println(Arrays.toString(metaPlayer.get(0).fields()));

        for(int i = 0;i < 5; i++) {
            if(metaPlayer.get(0).fields()[i] != null) {
                fieldsPlayerRank[i] = metaPlayer.get(0).fields()[i];
            }
        }
        for(int i = 0;i<metaRank.get(0).fields().length-1; i++) {
            if(metaRank.get(0).fields()[i] != null) {
                fieldsPlayerRank[i + 5] = metaRank.get(0).fields()[i];
            }
        }
        Result<?> result =
                DSL.using(Database.connect(), SQLDialect.SQLITE)
                        .select(fieldsPlayerRank)
                        .from(tableAtpPlayerRank)
                        .innerJoin(tableAtpPlayer).on(field("AtpPlayer.player_id").eq(field("AtpPlayerRanking.player")))
                        .where(field("AtpPlayer.player_id").notEqual("player_id"))
                        .orderBy(field("AtpPlayerRanking.rank"))
                        .limit(5000)
                        .fetch();
        result.forEach(System.out::println);
//        Result<Record> tables = ctx().select().fetch();
//        List<Table<?>> r = ctx().meta().getTables();
//        System.out.println(ctx().meta().getTables().get(3).fieldsRow());
//
//        System.out.println(ctx().meta().getSchemas());
//
//        System.out.println("meta master table: " + r);
    }

    public static ObservableList<PlayerRanking> observableAtpPlayer() {
        List<Table<?>> metaPlayer = ctx().meta().getTables().stream().filter(e->e.getName().equals("AtpPlayerRanking")).toList();
        List<Table<?>> metaRank = ctx().meta().getTables().stream().filter(e->e.getName().equals("AtpPlayer")).toList();

        Table<?> tableAtpPlayerRank = table("AtpPlayerRanking");
        Table<?> tableAtpPlayer = table("AtpPlayer");

        Field<?>[] fieldsPlayerRank = new Field<?>[metaRank.get(0).fields().length + metaPlayer.get(0).fields().length];
        System.out.println(Arrays.toString(metaPlayer.get(0).fields()));

        for(int i = 0;i < 5; i++) {
            if(metaPlayer.get(0).fields()[i] != null) {
                fieldsPlayerRank[i] = metaPlayer.get(0).fields()[i];
            }
        }
        for(int i = 0;i<metaRank.get(0).fields().length-1; i++) {
            if(metaRank.get(0).fields()[i] != null) {
                fieldsPlayerRank[i + 5] = metaRank.get(0).fields()[i];
            }
        }
        Result<?> result =
                DSL.using(Database.connect(), SQLDialect.SQLITE)
                        .select(fieldsPlayerRank)
                        .from(tableAtpPlayerRank)
                        .innerJoin(tableAtpPlayer).on(field("AtpPlayer.player_id").eq(field("AtpPlayerRanking.player")))
                        .where(field("AtpPlayer.player_id").notEqual("player_id"))
                        .orderBy(field("AtpPlayerRanking.rank"))
                        .fetch();

        ObservableList<PlayerRanking> playerAndRankObservable = FXCollections.observableArrayList();

        result.forEach(e-> {
            Object rankDate =   e.getValue("ranking_date");
            Object playerRank = e.getValue("rank");
            Object rankingPoints = e.getValue("points");
            Object playerId = e.getValue("player_id");
            Object firstName = e.getValue("name_first");
            Object lastName = e.getValue("name_last");
            Object fullName = firstName + " " + lastName;
            Object dominantHand = e.getValue("hand");
            Object dateOfBirth = e.getValue("dob");
            Object location = e.getValue("ioc") == null ? "" :e.getValue("ioc");
            Object height = e.getValue("height")== null ? "" : e.getValue("height");

            playerAndRankObservable.add(new PlayerRanking(playerId,fullName,rankDate,playerRank,rankingPoints,dominantHand,location,dateOfBirth));
        });
        return playerAndRankObservable;
    }

    public static void populateAtpPlayer() {
        List<Table<?>> r = ctx().meta().getTables();

        Result<Record> atpRankings = ctx().select().from("AtpPlayerRanking").fetch();

        Results wtaRankings = ctx().select().from("WTARank").fetchMany();
    }

    private static void populatePlayerTable(String input) {
        ObservableList<Tournament> temp = FXCollections.observableArrayList();
        Table<?> a = table("WtaPlayerRank");
        Table<?> b = table("WtaPlayer");

        Result<?> result =
                DSL.using(Database.connect(), SQLDialect.SQLITE)
                        .select()
                        .from(a)
                        .join(b)
                        .on(field("player", field("player_id")).eq(input))
                        .orderBy(field("rankPoints"))
                        .fetch();
    }

}





