package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.WtaPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;

import java.util.*;


import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.using;

public class WtaPlayerDao {
    public static ObservableList<WtaPlayer> playerObservableList = FXCollections.observableArrayList();
    public static ObservableList<PlayerRanking> playerRanking = FXCollections.observableArrayList();
    private static String mainTable = "Player";
    private static final String id = "id", firstName = "firstName", lastName = "lastName", hand = "hand";
    private static final String dob = "dob";
    private static final String ioc = "ioc";
    private static final String height = "height";
    private static final String wikiData_id = "wikidata_id";
    private static final String player_Rank_Id = "ID";

    static String rankingTable = "Player_Rank";

    private String rankCsv = "C:\\Users\\seost\\cs195TennisAnalytics\\cs195-TennisTracker\\src\\main\\resources\\com\\example\\cs195tennis";


    //by decade from 1910 to 2022
    public static ObservableList<WtaPlayer> populateWtaPlayerRanks() {

        ObservableList<WtaPlayer> observableWtaPlayerRanks = FXCollections.observableArrayList();
        List<Record> recordList = ctx().selectQuery(table("WtaRanks2000_2022")).fetch();

        recordList.stream().filter(Objects::nonNull).forEach(e-> {

            var player_id = e.getValue("player_id") == null ? "" : e.getValue("player_id");
            int id = player_id.hashCode();
            var firstName = e.getValue("name_first") == null ? "" : e.getValue("name_first").toString();
            var lastName = e.getValue("name_last") == null ? "" : e.getValue("name_first").toString();
            var dominantHand = e.getValue("hand") == null ? "" : e.getValue("hand").toString();
            var dateOfBirth = e.getValue("dob") == null ? "" : e.getValue("dob").toString();
            var location = e.getValue("ioc") == null ? "" : e.getValue("ioc").toString();
            var height = e.getValue("height") == null ? "" : e.getValue("height").toString();
            var rankDate = e.getValue("ranking_date") == null ? "" : e.getValue("ranking_date").toString();
            var playerId = e.getValue("player") == null ? "" : e.getValue("player").toString();
            var playerRank = e.getValue("rank") == null ? "" : e.getValue("rank").toString();
            var rankingPoints = e.getValue("points") == null ? "" : e.getValue("points").toString();
            //number of columns and rows determined by table filter selections
//            observableWtaPlayerRanks.add(new WtaPlayer(paramObj.columns.get()));

            observableWtaPlayerRanks.add(new WtaPlayer(id, recordList));
            observableWtaPlayerRanks.add(new WtaPlayer(id, firstName, lastName, dominantHand, dateOfBirth, location, height, rankDate, playerId, playerRank, rankingPoints));;

         });

        return observableWtaPlayerRanks;
    }


    private void catalogueWta() {
        Result<Record> wtaPlayers = ctx().select().from("WtaPlayers").fetch();
        System.out.println("size of wta players " + wtaPlayers.size());
        Result<Record> wtaRank = ctx().select().from("WtaRank").fetch();
        System.out.println("size of wta rankings " + wtaRank.size());
        Result<Record> wtaTournament = ctx().select().from("WTATournament").fetchSize(100).fetch();
        System.out.println("size of wta tournaments " + wtaTournament.size());
    }


    private static DSLContext ctx() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }
}






