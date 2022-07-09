package com.example.cs195tennis;

import com.example.cs195tennis.Data.Dao.DataHandeler;
import com.example.cs195tennis.model.Record.MatchRecord;
import com.example.cs195tennis.Util.Tools;
import org.jooq.*;
import org.jooq.impl.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static Database.Connection.Database.ctx;
import static com.example.cs195tennis.model.Tables.GAME;
import static com.example.cs195tennis.model.Tables.MATCH;

import static java.lang.System.out;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.SQLDataType.INTEGER;

public class insertFrenchOpen2020 {

    static int c = 0;

    public static int pathCsvToTable(String name) throws Exception {

        Converter<String, Long> c1 =
                Converter.ofNullable(String.class, Long.class, Long::parseLong, Object::toString);

        Converter<Long, Integer> c2 =
                Converter.ofNullable(Long.class, Integer.class, Number::intValue, Number::longValue);

        DataType<Long> d1 = SQLDataType.VARCHAR.asConvertedDataType(c1);

        DataType<Integer> d2 = d1.asConvertedDataType(c2);


        DataHandeler dataHandeler = new DataHandeler();

        List<List<String>> list = dataHandeler.parseCsvToListString("C:\\Users\\seost\\tennis_atp_datasets\\New folder\\2020-frenchopen-points.csv");
        Tools.title(" Rows: " + list.size() + " Columns: " + list.get(0).size());

        for (int i = 1; i < list.size(); i++) {
            int game_id = list.get(i).get(0).hashCode();
            int game_num = INTEGER.convert(list.get(i).get(6));
            int point_winner = INTEGER.convert(list.get(i).get(9));
            int p1score = INTEGER.convert(list.get(i).get(13));
            int p2score = INTEGER.convert(list.get(i).get(14));
            int p1Winner = INTEGER.convert(list.get(i).get(21));
            int p2Winner = INTEGER.convert(list.get(i).get(22));

            ctx().insertInto(GAME,
                            GAME.GAME_ID,
                            GAME.GAME_NUM,
                            GAME.POINT_WINNER,
                            GAME.P1_SCORE,
                            GAME.P2_SCORE,
                            GAME.P1_WINNER,
                            GAME.P2_WINNER

                    )
                    .values(game_id,
                            game_num,
                            point_winner,
                            p1score,
                            p2score,
                            p1Winner,
                            p2Winner
                    )
                    .execute();
        }
            return 1;

    }



    public static int loadCsvToTable(String tableName, String pathToFile) throws IOException, SQLException {
        DataHandeler dataHandeler = new DataHandeler();

        Loader<MatchRecord> loader =
                ctx()
                        .loadInto(MATCH)
                        .batchAll()
                        .commitAll()
                        .loadCSV(new FileReader(pathToFile))
                        .fields(MATCH.MATCH_ID, MATCH.PLAYER_1, MATCH.PLAYER_2, MATCH.MATCH_NUM, MATCH.TOURNAMENT_ID)
                        .execute();

        int processed = loader.processed();
        int stored = loader.stored();
        int ignored = loader.ignored();
        LoaderError error = loader.errors().get(0);

        out.println("processed: " + processed + " stored " + stored + " ignored " + ignored + " error " + error);
        return stored;


//                    ctx().insertInto(MATCH,
//                                    MATCH.MATCH_ID,
//                                    MATCH.PLAYER_1,
//                                    MATCH.PLAYER_2,
//                                    MATCH.MATCH_NUM,
//                                    MATCH.TOURNAMENT_ID
//                            )
//                            .values(match_id,
//                                    player1,
//                                    player2,
//                                    match_num,
//                                    tournament_id
//                            )
//                            .execute();
//
//        }

        }



//                String match_id;
//                int set_num = Integer.parseInt(e.get(2));
//                System.out.println("set num "+ set_num);
//                int point_num = Integer.parseInt(e.get(8));
//                System.out.println("set num "+ point_num);
//
//                int GameNo;
//                int PointNumber;
//                int PointWinner;
//                int PointServer;
//                int Speed_KMH;
//                int P1Score;
//                int P2Score;


//        Result<Record6<Integer, Integer, Integer, Integer, Integer, Integer>> rs =
//                ctx()
//                        .select(field("gameNo").cast(Integer.class),
//                                field("PointWinner").cast(Integer.class),
//                                field("P1Score").cast(Integer.class),
//                                field("P2Score").cast(Integer.class),
//                                field("p1Winner").cast(Integer.class),
//                                field("p2Winner").cast(Integer.class)
//                        )
//                        .from(table("GrandSlamPointByPoint"))
//                        .fetch();
//
//        System.out.println(rs.size());
//
//
//        int game_num = e.getValue(field("gameNo").cast(Integer.class));
//        int point_winner = e.getValue(field("PointWinner").cast(Integer.class));
//        int p1_score = e.getValue(field("P1Score").cast(Integer.class));
//        int p2_score = e.getValue(field("P2Score").cast(Integer.class));
//        int p1_winner = e.getValue(field("p1Winner").cast(Integer.class));
//        int p2_winner = e.getValue(field("p2Winner").cast(Integer.class));
//
//        ctx().insertInto(GAME,
//                        GAME.ID,
//                        GAME.GAME_NUM,
//                        GAME.POINT_WINNER,
//                        GAME.P1_SCORE,
//                        GAME.P2_SCORE,
//                        GAME.P1_WINNER,
////                        GAME.P2_WINNER
//
//                )
//                .values(i++,
//                        game_num,
//                        point_winner,
//                        p1_score,
//                        p2_score,
//                        p1_winner,
//                        p2_winner
//
//                )
//                .execute();


//    public static void testFinalsMatch() {
//        Tools.title("Select fields from Match table join GrandSlamTournament on MATCH.ID eq GRANDSLAM.MATCH_ID");
//        Tools.print(
//                ctx()
//                        .select(MATCH.ID,GRANDSLAM.MATCH_ID,GRANDSLAM.MATCH_NUM,GRANDSLAM.PLAYER_ONE,GRANDSLAM.PLAYER_TWO)
//                        .from(MATCH)
//                        .join(GRANDSLAM)
//                        .on(GRANDSLAM.MATCH_NUM.eq(MATCH.MATCH_NUM))
//                        .and(MATCH.PLAYER_ONE.eq("N Djokovic"))
//                        .where(GRANDSLAM.PLAYER_TWO.eq("R Nadal"))
//                        .and(GRANDSLAM.MATCH_NUM.eq(1701))
//                        .fetch());
//
//        Tools.title("Select fields from Match table join GrandSlamTournament on MATCH.ID eq GRANDSLAM.MATCH_ID");
//        Tools.print(
//                ctx()
//                        .select(MATCH.ID,
//                                GRANDSLAM.MATCH_ID,
//                                GRANDSLAM.MATCH_NUM,
//                                GRANDSLAM.PLAYER_ONE,
//                                GRANDSLAM.PLAYER_TWO)
//                        .from(MATCH)
//                        .join(GRANDSLAM)
//                        .on(GRANDSLAM.MATCH_NUM.eq(MATCH.MATCH_NUM))
//                        .and(MATCH.PLAYER_ONE.eq("N Djokovic"))
//                        .where(GRANDSLAM.PLAYER_TWO.eq("R Nadal"))
//                        .and(GRANDSLAM.MATCH_NUM.eq(1701))
//                        .fetch());
//
//        System.out.println(ctx().select(SET.GAME_ID).from(SET).fetch());

        //set num is point num

//        Tools.print(
//        ctx().
//                select(
////                        MATCH.PLAYER_ONE,
//                        SET.MATCH_ID, SET.SET_NUM, SET.ID, SET.SET_NUM
//                )
//                .from(SET)
//                .where(SET.ID.gt(100))
////                .on(SET.POINT_NUM.eq(MATCH.POINT_NUM))
//                .fetch()
//        );


//        ctx().
//                select(
//                        GRANDSLAM.NAME,
//                        MATCH.SET_ID,
//                        SET.ID
//                        GAME.POINT_WINNER
//                )
//                .from(GRANDSLAM)
//                .join(MATCH)
//                .on(MATCH.ID.eq(GRANDSLAM.MATCH_ID))
//                .join(WTA_PLAYER)
//                .on(MATCH.PLAYER_ONE.eq(WTA_PLAYER)
//                .join(GAME)
//                .on(MATCH.POINT_NUM.eq(GAME.ID))
//                .orderBy(1, 2, 3, 4)
//                .fetch();


//        var result =
//                ctx().select(
//                                GRANDSLAM.YEAR,
//                                multiset(
//                                        select(
//                                                MATCH.PLAYER_ONE,
//                                                MATCH.PLAYER_TWO)
//                                                .from(MATCH)
//                                                .where(MATCH.MATCH_NUM.eq(GRANDSLAM.MATCH_NUM))
//                                ).as("Match"),
//                                multiset(
//                                        select(GRANDSLAM.category().NAME)
//                                                .from(FILM_CATEGORY)
//                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
//                                ).as("categories")
//                        )
//                        .from(FILM)
//                        .orderBy(FILM.TITLE)
//                        .fetch();


//        Tools.title("Finals Match of 2020 French Open between Rafael Nadal and Novak Djokavic");
//        Tools.print(
//                ctx()
//                        .select(GRANDSLAM.MATCH_ID,GRANDSLAM.PLAYER_ONE,GRANDSLAM.PLAYER_TWO,GRANDSLAM.YEAR,GRANDSLAM.NAME,GRANDSLAM.MATCH_NUM,
//                        MATCH.MATCH_ID
//                        )
//                        .from(GRANDSLAM)
//                        .join(MATCH)
//                        .on(MATCH.MATCH_ID.eq(GRANDSLAM.MATCH_ID))
//                        .where(GRANDSLAM.YEAR.eq(2020))
//                        .and(GRANDSLAM.PLAYER_TWO.eq("R Nadal"))
////                        .and(GRANDSLAM.PLAYER_ONE.eq("N Djokovic"))
//                        .fetch()
//        );


//    }
//
//    public void testConnection() {
//        Tools.title("Testing connection and field value size");
//        Tools.meta();
//    }
    }

