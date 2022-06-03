package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.table;


public class PlayerDao {


    public static DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }

    static String csvDirectory = "C:\\Users\\seost\\Downloads\\tennis_wta-master\\New folder (2)\\wta_players.csv";
    static String csvDirectory2 = "C:\\Users\\seost\\tennis_atp_datasets\\New folder\\New folder";

    public static void main(String[] args) throws IOException {

        DataHandeler dataHandeler = new DataHandeler();
        //get all paths of csvs in directory
//        List<String> getFilesFromFolder = dataHandeler.getFilesFromFolder(csvDirectory);
        System.out.println("count: " + ctx().fetchCount(table("GrandSlamPointByPoint")));
//        dataHandeler.clearTable();
//        System.out.println("count: " + ctx().fetchCount(table("2010_2022GrandSlams")));
//        dataHandeler.updateColumn("WtaPlayers", new String[]{"ID"}, true);
        dataHandeler.printTableFields();

        List<String> list = dataHandeler.getFilesFromFolder(csvDirectory2);
        System.out.println("done");
        Result<Record> tables = ctx().select().fetch();
        List<Table<?>> r = ctx().meta().getTables();
        System.out.println(ctx().meta().getTables());
        System.out.println(ctx().meta().getSchemas());
        System.out.println("meta master table: " + r);

    }

    public static ObservableList<PlayerRanking> observableWtaPlayer(){
        //fields from tables rank and player
        List<Table<?>> metaPlayer = ctx().meta().getTables().stream().filter(e->e.getName().equals("WTAPlayers")).toList();
        List<Table<?>> metaRank = ctx().meta().getTables().stream().filter(e->e.getName().equals("WtaRanks2000_2022")).toList();
        //tables with current and all time ranks by date
        Table<?> tableWtaPlayerRank = table("WtaRanks2000_2022");
        System.out.println("wta players "+ metaPlayer.get(0).fieldsRow());
        Table<?> tableWtaPlayers = table("WTAPlayers");
        System.out.println("wta ranks "+metaRank.get(0).fieldsRow());
        //fields to be selected in query
        Field<?>[] fieldsPlayerRank = new Field<?>[metaRank.get(0).fields().length + metaPlayer.get(0).fields().length];

        for(int i = 0;i < 6; i++) {
            if(metaPlayer.get(0).fields()[i] != null) {
                fieldsPlayerRank[i] = metaPlayer.get(0).fields()[i];
            }
        }
        System.out.println(metaPlayer.get(0).fields().length);
        for(int i = 0;i<4; i++) {
            if(metaRank.get(0).fields()[i] != null) {
                fieldsPlayerRank[i + 5] = metaRank.get(0).fields()[i];
            }
        }
        System.out.println(fieldsPlayerRank.length);
        Result<Record> result =
                DSL.using(Database.connect(), SQLDialect.SQLITE)
                        .select(fieldsPlayerRank)
                        .from(tableWtaPlayers)
                        .innerJoin(tableWtaPlayerRank).on(field("WtaPlayers.player_id").eq(field("WtaRanks2000_2022.player")))
//                        .where(field("WtaPlayers.player_id").notEqual("player_id"))
                        .orderBy(field("WtaRanks2000_2022.rank"))
//                        .limit(100000)
                        .fetch();

        ObservableList<PlayerRanking> playerAndRankObservable = FXCollections.observableArrayList();


        result.forEach(e-> {
            int j = 0;
            e.fieldsRow().fields(field("dateOfBirth"));

            e.valuesRow().$fields().stream().filter(Objects::nonNull).forEach(f->{

            });
                Object playerId = e.getValue(1);
                Object firstName = e.getValue(2);
                Object lastName = e.getValue(3);
                Object dominantHand = e.getValue(4);
                Object fullName = firstName + " " + lastName;
                Object rankDate = e.getValue(5);
                Object playerRank = e.getValue(7);
                Object rankingPoints = e.getValue(8);
                playerAndRankObservable.add(new PlayerRanking(playerId, fullName, rankDate, playerRank, rankingPoints, dominantHand));
        });
        return playerAndRankObservable;

    }

    public static ObservableList<PlayerRanking> observableAtpPlayer() {
        //meta info for fields row
        List<Table<?>> metaPlayer = ctx().meta().getTables().stream().filter(e->e.getName().equals("AtpPlayerRanking")).toList();
        List<Table<?>> metaRank = ctx().meta().getTables().stream().filter(e->e.getName().equals("AtpPlayer")).toList();
        //join atp players and rank by date and id
        Table<?> tableAtpPlayerRank = table("AtpPlayerRanking");
        Table<?> tableAtpPlayer = table("AtpPlayer");

        Field<?>[] fieldsPlayerRank = new Field<?>[metaRank.get(0).fields().length + metaPlayer.get(0).fields().length];
        System.out.println(Arrays.toString(metaPlayer.get(0).fields()));
        //fields from meta tables
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
//                        .where(field("AtpPlayer.player_id").notEqual("player_id"))
                        .orderBy(field("AtpPlayerRanking.rank"))
                        .fetch();
        System.out.println(result.size());
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
}





