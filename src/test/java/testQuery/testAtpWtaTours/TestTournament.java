//package testQuery.testAtpWtaTours;//import Data.verify.StatisticsListener;
//import com.example.cs195tennis.Util.Tools;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static com.example.cs195tennis.TestAtpPlayerAtpRank.ctx;
//import static com.example.cs195tennis.model.GrandSlam.GRANDSLAM;
//import static com.example.cs195tennis.model.Match.MATCH;
//import static com.example.cs195tennis.model.WtaPlayer.WTA_PLAYER;
//import static java.lang.System.out;
//import static org.jooq.impl.DSL.table;
//
//public class TestTournament {
//
//
//    public static void testGrandSlams(String table, List<Field<?>> fields) {
//
//        Tools.title("Test results group by id expected: -2102369293");
//        Tools.print(
//                ctx()
//                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
//                        .from(GRANDSLAM)
//                        .groupBy(GRANDSLAM.ID)
//                        .limit(40)
//                        .fetch());
//
//        Tools.title("Test results group by slam ");
//        Tools.print(
//                ctx()
//                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
//                        .from(GRANDSLAM)
//                        .orderBy(GRANDSLAM.NAME.desc())
//                        .limit(40)
//                        .fetch());
//
//        Tools.title("Test by year ascending Expected start year: 2011");
//        Tools.print(
//                ctx()
//                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
//                        .from(GRANDSLAM)
//                        .orderBy(GRANDSLAM.YEAR.asc())
//                        .limit(50)
//                        .fetch());
//
//        Tools.title("Test by year descending Expected start year: 2021 ");
//        Tools.print(
//                ctx()
//                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
//                        .from(GRANDSLAM)
//                        .orderBy(GRANDSLAM.YEAR.desc())
//                        .limit(50)
//                        .fetch());
//
//        Tools.title("Test ordering by match_id descending Expected hash: 1471369950");
//        Tools.print(
//                ctx()
//                        .select(GRANDSLAM.ID, GRANDSLAM.NAME, GRANDSLAM.YEAR, GRANDSLAM.MATCH_ID)
//                        .from(GRANDSLAM)
//                        .orderBy(GRANDSLAM.MATCH_ID.desc())
//                        .limit(40)
//                        .fetch());
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//
//    }
//
//    public static void testPointByPoint() {
//
//
//        Tools.title("Test that id increments starting at: ");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_2)
//                        .from(MATCH)
//                        .orderBy(MATCH.MATCH_ID.asc())
//                        .limit(10)
//                        .fetch());
//
//        Tools.title("Test that game no. : ");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_2)
//                        .from(MATCH)
//                        .orderBy(MATCH.MATCH_NUM.asc())
//                        .fetch());
//
//        Tools.title("Test P1 score column ");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_2)
//                        .from(MATCH)
//                        .where(MATCH.PLAYER_2.eq("15"))
//                        .orderBy(MATCH.MATCH_ID)
//                        .limit(40)
//                        .fetch());
//
//
//        Tools.title("Test p1 score = 30 and game no less than 50");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_2)
//                        .from(MATCH)
//                        .where(MATCH.PLAYER_2.eq("30"))
//                        .and(MATCH.MATCH_NUM.lt(50))
//                        .orderBy(MATCH.MATCH_ID)
//                        .limit(50)
//                        .fetch());
//
//        Tools.title("Test p1 score eq 45 order by set no");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_ONE)
//                        .from(MATCH)
//                        .where(MATCH.PLAYER_ONE.eq("40"))
//                        .orderBy(MATCH.MATCH_ID)
//                        .limit(50)
//                        .fetch());
//
//        Tools.title("Test p1 score eq 0 and game no eq 100");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_ONE)
//                        .from(MATCH)
//                        .where(MATCH.PLAYER_ONE.eq("0"))
//                        .and(MATCH.MATCH_NUM.eq(100))
//                        .limit(50)
//                        .fetch());
//
//        Tools.title("Test Game lt 100 and set no eq 1 ");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_ONE)
//                        .from(MATCH)
//                        .where(MATCH.MATCH_NUM.lt(100))
//                        .and(MATCH.MATCH_ID.eq(1))
//                        .fetch());
//
//        Tools.title("Test Game no greater than 1000 and set no = 2  ");
//        Tools.print(
//                ctx()
//                        .select(MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, MATCH.PLAYER_ONE)
//                        .from(MATCH)
//                        .where(MATCH.MATCH_NUM.gt(1000))
//                        .and(MATCH.MATCH_ID.eq(2))
//                        .limit(40)
//                        .fetch());
//
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//
//    }
//
//
//
//    public static  void testTableFields(String tableName){
//        Tools.title("Fields for Grand SLam and Point by Point");
//
//        Map<String, Result<Record4<Integer, String, String, Integer>>> mapBySlam =
//                ctx()
//                        .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
//                        .from(GRANDSLAM)
//                        .fetchGroups(GRANDSLAM.NAME);
//
//        System.out.println("size: " + mapBySlam.size());
//
//        mapBySlam.keySet().forEach(System.out::println);
//
////        map.()
////                .stream()
////                .map(ResultOrRows::result)
////                .filter(Objects::nonNull)
////                .collect(Collectors.toList());
//
////        Map<Integer, Result<Record5<Integer, Integer, Integer, String, String>>> mapMatchPointIds =
////                ctx()
////                        .select(MATCH.ID, MATCH.SET_ID, MATCH.POINT_NUM, MATCH.PLAYER_ONE, MATCH.PLAYER_TWO)
////                        .from(MATCH)
////                        .fetchGroups(MATCH.ID);
////        System.out.println("size: " + mapMatchPointIds.size());
////
////        mapMatchPointIds.values().stream().limit(5).forEach(System.out::println);
//    }
//
//    /**
//     */
//    public static void joinTableTest() {
//        String title = "Expected Result: Wta Player Rankings ";
//
//        testPlayerRank(title, 1, 10, "T");
//    }
//
//    /**
//     */
//    public static void testPlayerRank(String title, int start, int last, String date){
//
//        Tools.title(title);
//
//        Tools.print(
//                ctx().select(GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.PLAYER_ONE,
//                                MATCH.MATCH_ID, MATCH.MATCH_NUM, MATCH.MATCH_ID, WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME)
//                        .from(GRANDSLAM
//                                .leftOuterJoin(MATCH
//                                        .join(WTA_PLAYER)
//                                        .on(WTA_PLAYER.ID.notEqual(MATCH.MATCH_ID)))
//                                .on(GRANDSLAM.MATCH_ID.eq(MATCH.MATCH_ID.toString())))
//                        .limit(10)
//                        .fetch());
//
//        out.println("Test");
//
//
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//    }
//
//    //        ctx().select(
////                        MATCHPOINT.wtaPlayer().FIRST_NAME,
////                        MATCHPOINT.wtaPlayer().LAST_NAME,
////                        MATCHPOINT.ID
////                        MATCHPOINT.P1_SCORE().CD.as("language"))
////                .from(BOOK)
////                .fetch();
//
//
////        List<Result<Record>> values = ctx()
////                .selectDistinct()
////                .from(Tables.GRANDSLAM)
////                .innerJoin(MATCHPOINT).on(MATCHPOINT.ID.eq(Tables.GRANDSLAM.MATCH_ID))
////                .innerJoin(WTA_PLAYER).on(WTA_PLAYER.ID.eq(MATCHPOINT.P1_SCORE))
////                .innerJoin(WTA_RANK).on(WTA_PLAYER.ID.eq(WTA_RANK.PLAYER_ID))
////                .orderBy(WTA_RANK.ID.asc())
////                .fetch()
////                .intoGroups(WTA_RANK.RANKING_DATE)
////                .values()
////                .stream()
////                .toList();
//
//
////        return values.stream()
////                .map(r -> {
////                    MatchRecord match = r.into(MATCH.ID, TOURNAMENT.NAME, MATCH.MATCH_WINNER).get(0).into(MatchRecord.class);
////                    match.set(r.into(TOURNAMENT.ID, TOURNAMENT.NAME).get(0).into(TOURNAMENT.class));
////                    match.setPointWinner(r.sortAsc(TOPPING.ID).into(Topping.class));
////                    return match;
////                })
////                .collect(Collectors.toList());
////        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n WHERE RANK.ranking BETWEEN " + start + " AND "+last + " ORDERBY RANK.ranking"));
////        Tools.print(
////                ctx()
////                        .select(ATP_PLAYER.ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
////                        .from(ATP_PLAYER)
////                        .join(ATP_RANK)
////                        .on(ATP_PLAYER.ID.eq(ATP_RANK.PLAYER_ID))
////                        .and(ATP_RANK.RANKING_DATE.eq(date))
////                        .where(ATP_RANK.RANKING.between(start).and(last))
////                        .orderBy(ATP_RANK.RANKING)
////                        .fetch());
//////
////        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n AND RANK.ranking LT " + last  + " ORDERBY RANK.ranking"));
////        Tools.print(
////                ctx()
////
////                        .select(ATP_PLAYER.ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
////                        .from(ATP_PLAYER)
////                        .join(ATP_RANK)
////                        .on(ATP_PLAYER.ID.eq(ATP_RANK.PLAYER_ID))
////                        .and(ATP_RANK.RANKING_DATE.eq(date))
////                        .and(ATP_RANK.RANKING.lt(last))
////                        .orderBy(ATP_RANK.RANKING)
////                        .fetch());
////
////        Tools.title(title.concat(" AND RANK.ranking_date LT " + date + " \n WHERE RANK.ranking LT " + last + " ORDERBY RANK.ranking"));
////        Tools.print(
////                ctx()
////                        .select(ATP_PLAYER.ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
////                        .from(ATP_PLAYER)
////                        .join(ATP_RANK)
////                        .on(ATP_RANK.PLAYER_ID.eq(ATP_PLAYER.ID))
////                        .and(ATP_RANK.RANKING_DATE.eq(date))
////                        .where(ATP_RANK.RANKING.lt(last))
////                        .orderBy(ATP_RANK.RANKING)
////                        .fetch());
//
//    public static void testMatchPointByPointFields(String title, String tableName) {
//        //Map<Integer, Result<Record4<Integer, String, String, Integer>>> mapBySlam =
//        //ctx().fetchCount(DSL.selectFrom(GRANDSLAM))
//
//        title = "Number of columns in GrandSlamPointByPoint";
//        Tools.title(title);
//        List<Field<?>> fieldList = new ArrayList<>();
//
////       Tools.print(
////        ctx().selectCount().from(table("GrandSlamPointByPoint"))
////                .where(field("GrandSlamPointByPoint.NAME").isNotNull())
////                .fetch()
////       );
//
//        fieldList = ctx()
//                .select()
//                .from(table("GrandSlamPointByPoint"))
//                .fetch()
//                .fieldsRow()
//                .fieldStream()
//                .collect(Collectors.toList());
//
//        fieldList.forEach(field -> {
//            System.out.println("Checking " + field);
//
//            Tools.print(
//                    ctx().select(field)
//                            .from(table("GrandSlamPointByPoint"))
//                            .fetch()
//            );
////            if(
////            ctx()
////                    .select(field)
////                    .from(table("GrandSlamPointByPoint"))
////                    .fetch()
////                    .size()
////                    == 0
////            ){
////                System.out.println(field + " has 0 records ");
////            }
//
//        });
//    }
//
////        ctx()
////                .select()
////                .from(table("GrandSlamPointByPoint"))
////                .fetchMany()
////                .resultsOrRows()
////                .forEach(System.out::println
////                );
////        title = "Number Null Columns: Column: MATCH_ID";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .selectCount()
////                        .from(GRANDSLAM)
////                        .where(GRANDSLAM.MATCH_ID.isNull())
////                        .fetch()
////
////        );
////
////        title = "If Null Columns > 0";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .select(GRANDSLAM.MATCH_ID)
////                        .from(GRANDSLAM)
////                        .fetch()
////
////        );
////
////        title = "Number Null Columns: Column: NAME";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .selectCount().from(GRANDSLAM)
////                        .where(GRANDSLAM.NAME.isNull())
////                        .fetch()
////        );
////
////        title = "Number Null Columns: Column: YEAR";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .selectCount().from(GRANDSLAM)
////                        .where(GRANDSLAM.YEAR.isNull())
////                        .fetch()
////        );
////
////        title = "Number of Records in GrandSlam table";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
////                        .from(GRANDSLAM)
////                        .fetchGroups(GRANDSLAM.ID)
////                        .size()
////        );
////        //select field and count number of records grouped by that field
////        title = "Number of Records in GrandSlam table grouped by ID. ID = Objects.hash(match_id)";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
////                        .from(GRANDSLAM)
////                        .fetchGroups(GRANDSLAM.ID)
////                        .size()
////        );
////        title = "Number of Records in GrandSlam table grouped by fk_match_id. slam_name + year + match_num = ";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
////                        .from(GRANDSLAM)
////                        .where(GRANDSLAM.MATCH_ID.isNotNull())
////                        .fetchGroups(GRANDSLAM.MATCH_ID)
////                        .size()
////        );
////        title = "Number of Records in GrandSlam table grouped by NAME";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
////                        .from(GRANDSLAM)
////                        .fetchGroups(GRANDSLAM.NAME)
////                        .size()
////        );
////        title = "Number of Records in GrandSlamPointByPoint table grouped by YEAR";
////        Tools.title(title);
////        Tools.print(
////                ctx()
////                        .select(MATCHPOINT.ID, MATCHPOINT.SET_NO, MATCHPOINT.GAME_NO, MATCHPOINT.P1_SCORE)
////                        .from(GRANDSLAM)
////                        .fetchGroups(GRANDSLAM.YEAR)
////                        .size()
////        );
////
////
//
//
//        public static void testGrandSlamAndMatchPointFields(String title, String tableName) {
//            //Map<Integer, Result<Record4<Integer, String, String, Integer>>> mapBySlam =
//            //ctx().fetchCount(DSL.selectFrom(GRANDSLAM))
//
//            title = "Number of Null Columns: ID";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .selectCount()
//                            .from(GRANDSLAM)
//                            .where(GRANDSLAM.ID.isNull())
//                            .fetch()
//            );
//
//            title = "Number Null Columns: Column: MATCH_ID";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .selectCount()
//                            .from(GRANDSLAM)
//                            .where(GRANDSLAM.MATCH_ID.isNull())
//                            .fetch()
//
//            );
//
//            title = "If Null Columns > 0";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .select(GRANDSLAM.MATCH_ID)
//                            .from(GRANDSLAM)
//                            .fetch()
//
//            );
//
//            title = "Number Null Columns: Column: NAME";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .selectCount().from(GRANDSLAM)
//                            .where(GRANDSLAM.NAME.isNull())
//                            .fetch()
//            );
//
//            title = "Number Null Columns: Column: YEAR";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .selectCount().from(GRANDSLAM)
//                            .where(GRANDSLAM.YEAR.isNull())
//                            .fetch()
//            );
//
//            title = "Number of Records in GrandSlam table";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
//                            .from(GRANDSLAM)
//                            .fetchGroups(GRANDSLAM.ID)
//                            .size()
//            );
//            //select field and count number of records grouped by that field
//            title = "Number of Records in GrandSlam table grouped by ID. ID = Objects.hash(match_id)";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
//                            .from(GRANDSLAM)
//                            .fetchGroups(GRANDSLAM.ID)
//                            .size()
//            );
//            title = "Number of Records in GrandSlam table grouped by fk_match_id. slam_name + year + match_num = ";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
//                            .from(GRANDSLAM)
//                            .where(GRANDSLAM.MATCH_ID.isNotNull())
//                            .fetchGroups(GRANDSLAM.MATCH_ID)
//                            .size()
//            );
//            title = "Number of Records in GrandSlam table grouped by NAME";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
//                            .from(GRANDSLAM)
//                            .fetchGroups(GRANDSLAM.NAME)
//                            .size()
//            );
//            title = "Number of Records in GrandSlam table grouped by YEAR";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .select(GRANDSLAM.ID, GRANDSLAM.MATCH_ID, GRANDSLAM.NAME, GRANDSLAM.YEAR)
//                            .from(GRANDSLAM)
//                            .fetchGroups(GRANDSLAM.YEAR)
//                            .size()
//            );
//
//
//
//
//
//            // Map<Integer, Result<Record4<Integer, Integer, Integer, Integer>>> mapPointByPoint =
//
//            title = "Size of Match point by point table";
//            Tools.title(title);
//            Tools.print(
//                    ctx()
//                            .fetchCount(DSL.selectFrom(MATCH))
//            );
//
//
////                ctx()
////                        .select(MATCHPOINT.ID, MATCHPOINT.GAME_NO, MATCHPOINT.SET_NO, MATCHPOINT.P1_SCORE)
////                        .from(MATCHPOINT)
////                        .fetch()
//
//
//
////        mapBySlam.keySet().forEach(System.out::println);
//
////        System.out.println(mapByDate.size());
////
////        mapByDate
////                .keySet()
////                .stream()
////                .sorted(Comparator.reverseOrder())
////                .limit(1)
////                .forEach(date->
////                        testPlayerRank(title, 1, 10, 1)
////                );
////
//////        boolean condition = mapByDate.size() > 0;
////
////                ctx().select(GRANDSLAM.ID)
////                        .from(GRANDSLAM)
////                        .where(condition ? GRANDSLAM.ID.eq(10) : noCondition())
////                        .fetch();
//        }
//}
//
//
