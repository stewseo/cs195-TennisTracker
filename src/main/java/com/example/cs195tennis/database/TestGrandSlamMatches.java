package com.example.cs195tennis.database;

import com.example.cs195tennis.Dao.Table.StatisticsListener;
import com.example.cs195tennis.Dao.Table.Tables;
import com.example.cs195tennis.model.GrandSlam;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.example.cs195tennis.Dao.Table.Tables.ATP_PLAYER;
import static com.example.cs195tennis.database.TestAtpPlayerAtpRank.ctx;
import static com.example.cs195tennis.model.AtpRank.ATP_RANK;
import static com.example.cs195tennis.model.GrandSlam.GRANDSLAM;
import static com.example.cs195tennis.model.MatchPointByPoint.MATCHPOINT;
import static com.example.cs195tennis.model.WtaPlayer.WTA_PLAYER;
import static com.example.cs195tennis.model.WtaRank.WTA_RANK;
import static java.lang.System.out;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.noCondition;
import static org.jooq.impl.SQLDataType.INTEGER;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class TestGrandSlamMatches {

    public static void testGrandSlam() {

        Tools.title("Creating AtpPlayer table, Primary key: id");
        try {
            Tools.print(
                    ctx()
                            .createTable("GrandSlams")
                            .column("player_id", INTEGER)
                            .column("name_first",VARCHAR(255))
                            .column("name_last", VARCHAR(255))
                            .primaryKey("player_id")
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createTableServeStats() {
        Tools.title("Creating WtaRank table, Primary key: ranking_date");
        try {
            Tools.print(
                    ctx()
                            .createTable("GrandSlamPointByPoint")
                            .column("id", INTEGER)
                            .column("ranking_date", INTEGER)
                            .column("player_id", INTEGER)
                            .column("rank", INTEGER)
                            .column("points", INTEGER)
                            .primaryKey("id")
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createTableMatchPoint() {
        Tools.title("Creating WtaRank table, Primary key: ranking_date");
        try {
            Tools.print(
                    ctx()
                            .createTable("GrandSlamPointByPoint")
                            .column("id", INTEGER)
                            .column("ranking_date", INTEGER)
                            .column("player_id", INTEGER)
                            .column("rank", INTEGER)
                            .column("points", INTEGER)
                            .primaryKey("id")
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
cd 

    public static void createTableGrandSlam() {
        Tools.title("Creating WtaRank table, Primary key: ranking_date");
        try {
            Tools.print(
                    ctx()
                            .createTable("GrandSlamPointByPoint")
                            .column("id", INTEGER)
                            .column("ranking_date", INTEGER)
                            .column("player_id", INTEGER)
                            .column("rank", INTEGER)
                            .column("points", INTEGER)
                            .primaryKey("id")
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    public static void testGrandSlams(String table, List<Field<?>> fields) {

        Tools.title("Test results group by id expected: -2102369293");
        Tools.print(
                ctx()
                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
                        .from(GRANDSLAM)
                        .groupBy(GRANDSLAM.ID)
                        .limit(40)
                        .fetch());

        Tools.title("Test results group by slam ");
        Tools.print(
                ctx()
                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
                        .from(GRANDSLAM)
                        .orderBy(GRANDSLAM.NAME.desc())
                        .limit(40)
                        .fetch());

        Tools.title("Test by year ascending Expected start year: 2011");
        Tools.print(
                ctx()
                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
                        .from(GRANDSLAM)
                        .orderBy(GRANDSLAM.YEAR.asc())
                        .limit(50)
                        .fetch());

        Tools.title("Test by year descending Expected start year: 2021 ");
        Tools.print(
                ctx()
                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
                        .from(GRANDSLAM)
                        .orderBy(GRANDSLAM.YEAR.desc())
                        .limit(50)
                        .fetch());

        Tools.title("Test ordering by match_id descending Expected hash: 1471369950");
        Tools.print(
                ctx()
                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
                        .from(GRANDSLAM)
                        .orderBy(GRANDSLAM.MATCH_ID.desc())
                        .limit(40)
                        .fetch());

        for (ExecuteType type : ExecuteType.values()) {
            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
        }

    }

    public static void testPointByPoint() {


        Tools.title("Test that id increments starting at: ");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .orderBy(MATCHPOINT.ID.asc())
                        .limit(10)
                        .fetch());

        Tools.title("Test that game no. : ");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .orderBy(MATCHPOINT.GAME_NO.asc())
                        .fetch());

        Tools.title("Test P1 score column ");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .where(MATCHPOINT.P1_SCORE.eq(15))
                        .orderBy(MATCHPOINT.ID)
                        .limit(40)
                        .fetch());


        Tools.title("Test p1 score = 30 and game no less than 50");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .where(MATCHPOINT.P1_SCORE.eq(30))
                        .and(MATCHPOINT.GAME_NO.lt(50))
                        .orderBy(MATCHPOINT.ID)
                        .limit(50)
                        .fetch());

        Tools.title("Test p1 score eq 45 order by set no");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .where(MATCHPOINT.P1_SCORE.eq(40))
                        .orderBy(MATCHPOINT.SET_NO)
                        .limit(50)
                        .fetch());

        Tools.title("Test p1 score eq 0 and game no eq 100");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .where(MATCHPOINT.P1_SCORE.eq(0))
                        .and(MATCHPOINT.GAME_NO.eq(100))
                        .limit(50)
                        .fetch());

        Tools.title("Test Game lt 100 and set no eq 1 ");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .where(MATCHPOINT.GAME_NO.lt(100))
                        .and(MATCHPOINT.SET_NO.eq(1))
                        .fetch());

        Tools.title("Test Game no greater than 1000 and set no = 2  ");
        Tools.print(
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .where(MATCHPOINT.GAME_NO.gt(1000))
                        .and(MATCHPOINT.SET_NO.eq(2))
                        .limit(40)
                        .fetch());


        for (ExecuteType type : ExecuteType.values()) {
            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
        }

    }

    public static  void testTableFields(String tableName){
        Tools.title("Fields for Grand SLam and Point by Point");

        Map<String, Result<Record4<Integer, String, String, Integer>>> mapBySlam =
                ctx()
                        .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
                        .from(GRANDSLAM)
                        .fetchGroups(GRANDSLAM.NAME);

        System.out.println("size: " + mapBySlam.size());

        mapBySlam.keySet().forEach(System.out::println);

//        map.()
//                .stream()
//                .map(ResultOrRows::result)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());

        Map<Integer, Result<Record4<Integer, Integer, Integer, Integer>>> mapMatchPointIds =
                 ctx()
                         .select(MATCHPOINT.ID, MATCHPOINT.SET_NO, MATCHPOINT.GAME_NO, MATCHPOINT.P1_SCORE)
                         .from(MATCHPOINT)
                         .fetchGroups(MATCHPOINT.ID);
        System.out.println("size: " + mapMatchPointIds.size());

        mapMatchPointIds.values().stream().limit(5).forEach(System.out::println);
    }

    /**
     * @key = ranking_date
     * equals actual Wta Player Rank results
     */
    public static void joinTableTest() {
        String title = "Expected Result: Wta Player Rankings ";

        Map<Integer, Result<Record4<Integer, String, String, Integer>>> mapBySlam =
                ctx()
                        .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
                        .from(GRANDSLAM)
                        .fetchGroups(GRANDSLAM.ID);

        System.out.println("size: " + mapBySlam.size());

        Map<Integer, Result<Record4<Integer, Integer, Integer, Integer>>> mapPointByPoint =
                ctx()
                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
                        .from(MATCHPOINT)
                        .fetchGroups(MATCHPOINT.ID);

        System.out.println("size: " + mapPointByPoint.size());

//        mapBySlam.keySet().forEach(System.out::println);

//        System.out.println(mapByDate.size());
//
//        mapByDate
//                .keySet()
//                .stream()
//                .sorted(Comparator.reverseOrder())
//                .limit(1)
//                .forEach(date->
//                        testPlayerRank(title, 1, 10, 1)
//                );
//
////        boolean condition = mapByDate.size() > 0;
//
//                ctx().select(GRANDSLAM.ID)
//                        .from(GRANDSLAM)
//                        .where(condition ? GRANDSLAM.ID.eq(10) : noCondition())
//                        .fetch();



        testPlayerRank(title, 1, 10, "T");

    }

    /**
     * @param date ranking_date field
     */
    public static void testPlayerRank(String title, int start, int last, String date){

        Tools.title(title);

        Tools.print(
                ctx().select(GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.PLAYER_ONE,
                                MATCHPOINT.ID, MATCHPOINT.P1_SCORE, MATCHPOINT.GAME_NO , MATCHPOINT.SET_NO, WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME)
                        .from(GRANDSLAM
                                .leftOuterJoin(MATCHPOINT
                                        .join(WTA_PLAYER)
                                        .on(WTA_PLAYER.ID.notEqual(MATCHPOINT.P1_SCORE)))
                                .on(GRANDSLAM.MATCH_ID.eq(MATCHPOINT.SET_NO.toString())))
                        .limit(10)
                        .fetch());

        System.out.println("Test");


        // Get all books, their authors, and their respective language
//        ctx().select(
//                        MATCHPOINT.wtaPlayer().FIRST_NAME,
//                        MATCHPOINT.wtaPlayer().LAST_NAME,
//                        MATCHPOINT.ID
//                        MATCHPOINT.P1_SCORE().CD.as("language"))
//                .from(BOOK)
//                .fetch();

        // Count the number of books by author and language
//        ctx().select(
//                        GrandSlam.author().FIRST_NAME,
//                        GrandSlam.author().LAST_NAME,
//                        GrandSlam.language().CD.as("language"),
//                        count())
//                .from(BOOK)
//                .groupBy(
//                        GrandSlam.AUTHOR_ID,
//                        GrandSlam.author().FIRST_NAME,
//                        GrandSlam.author().LAST_NAME,
//                        GrandSlam.language().CD)
//                .orderBy(
//                        GrandSlam.author().FIRST_NAME,
//                        GrandSlam.author().LAST_NAME,
//                        GrandSlam.language().CD)
//                .fetch();

//        create.select()
//                .from(AUTHOR
//                        .leftOuterJoin(BOOK
//                                .join(BOOK_TO_BOOK_STORE)
//                                .on(BOOK_TO_BOOK_STORE.BOOK_ID.eq(BOOK.ID)))
//                        .on(BOOK.AUTHOR_ID.eq(AUTHOR.ID)))
//                .fetch();
//        List<Result<Record>> values = ctx()
//                .selectDistinct()
//                .from(Tables.GRANDSLAM)
//                .innerJoin(MATCHPOINT).on(MATCHPOINT.ID.eq(Tables.GRANDSLAM.MATCH_ID))
//                .innerJoin(WTA_PLAYER).on(WTA_PLAYER.ID.eq(MATCHPOINT.P1_SCORE))
//                .innerJoin(WTA_RANK).on(WTA_PLAYER.ID.eq(WTA_RANK.PLAYER_ID))
//                .orderBy(WTA_RANK.ID.asc())
//                .fetch()
//                .intoGroups(WTA_RANK.RANKING_DATE)
//                .values()
//                .stream()
//                .toList();


//        return values.stream()
//                .map(r -> {
//                    MatchRecord match = r.into(MATCH.ID, TOURNAMENT.NAME, MATCH.MATCH_WINNER).get(0).into(MatchRecord.class);
//                    match.set(r.into(TOURNAMENT.ID, TOURNAMENT.NAME).get(0).into(TOURNAMENT.class));
//                    match.setPointWinner(r.sortAsc(TOPPING.ID).into(Topping.class));
//                    return match;
//                })
//                .collect(Collectors.toList());
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n WHERE RANK.ranking BETWEEN " + start + " AND "+last + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_PLAYER)
//                        .join(ATP_RANK)
//                        .on(ATP_PLAYER.ID.eq(ATP_RANK.PLAYER_ID))
//                        .and(ATP_RANK.RANKING_DATE.eq(date))
//                        .where(ATP_RANK.RANKING.between(start).and(last))
//                        .orderBy(ATP_RANK.RANKING)
//                        .fetch());
////
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n AND RANK.ranking LT " + last  + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//
//                        .select(ATP_PLAYER.ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_PLAYER)
//                        .join(ATP_RANK)
//                        .on(ATP_PLAYER.ID.eq(ATP_RANK.PLAYER_ID))
//                        .and(ATP_RANK.RANKING_DATE.eq(date))
//                        .and(ATP_RANK.RANKING.lt(last))
//                        .orderBy(ATP_RANK.RANKING)
//                        .fetch());
//
//        Tools.title(title.concat(" AND RANK.ranking_date LT " + date + " \n WHERE RANK.ranking LT " + last + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_PLAYER)
//                        .join(ATP_RANK)
//                        .on(ATP_RANK.PLAYER_ID.eq(ATP_PLAYER.ID))
//                        .and(ATP_RANK.RANKING_DATE.eq(date))
//                        .where(ATP_RANK.RANKING.lt(last))
//                        .orderBy(ATP_RANK.RANKING)
//                        .fetch());

        for (ExecuteType type : ExecuteType.values()) {
            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
        }
    }



}
