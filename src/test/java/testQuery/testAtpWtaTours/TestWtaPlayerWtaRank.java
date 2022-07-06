//package testQuery.testAtpWtaTours;
//import com.example.cs195tennis.Data.Dao.DataHandeler;
//import WtaPlayerRecord;
//import WtaRankRecord;
//import Data.verify.StatisticsListener;
//import com.example.cs195tennis.Util.Tools;
//import org.jooq.Record;
//import org.jooq.*;
//import org.jooq.exception.DataAccessException;
//import org.jooq.impl.CallbackExecuteListener;
//import org.jooq.impl.DSL;
//import org.jooq.impl.DefaultConfiguration;
//import org.jooq.impl.DefaultConnectionProvider;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//
//import static com.example.cs195tennis.Util.Tools.connection;
//import static com.example.cs195tennis.model.WtaPlayer.WTA_PLAYER;
//import static com.example.cs195tennis.model.WtaRank.WTA_RANK;
//import static java.lang.System.out;
//import static org.jooq.impl.DSL.field;
//import static org.jooq.impl.DSL.table;
//import static org.jooq.impl.SQLDataType.INTEGER;
//import static org.jooq.impl.SQLDataType.VARCHAR;
//
//public class TestWtaPlayerWtaRank {
//    private static DataHandeler data;
//
//    public static DSLContext ctx() {
//        data = new DataHandeler();
//        ExecuteListener listener = new CallbackExecuteListener()
//                .onStart(ctx -> {
//
//                    ctx.data("time", System.nanoTime());
//                })
//                .onEnd(ctx -> {
//                    Long time = (Long) ctx.data("time");
//
//                    System.out.println("Execution time : " + ((System.nanoTime() - time) / 1000 / 1000.0) + "ms. Query : " + ctx.sql());
//                });
//
//        return DSL.using(new DefaultConfiguration()
//                .set(SQLDialect.SQLITE)
//                .set(new DefaultConnectionProvider(connection()))
//                .set(listener, new StatisticsListener()));
//    }
//
//
//    /**
//     * @key = ranking_date
//     * Test expected Wta Player Rank from last 5 Wta Rank updates
//     * equals actual Wta Player Rank results
//     */
//    public static void betweenRankTest() {
//        String title = "Expected Result: Wta Player Rankings ";
//
//        Map<Integer, Result<Record5<Integer, Integer, Integer, Integer, Integer>>> mapByDate =
//                ctx()
//                .select(WTA_RANK.ID, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID, WTA_RANK.RANKING, WTA_RANK.POINTS)
//                .from(WTA_RANK)
//                .fetchGroups(WTA_RANK.RANKING_DATE);
//
//        mapByDate
//                .keySet()
//                .stream()
//                .sorted(Comparator.reverseOrder())
//                .limit(5)
//                .forEach(date->
//                        testPlayerRank(title, 1, 10, date)
//                );
//    }
//
//    /**
//     * @param start WtaRank.ranking start range inclusive
//     * @param last WtaRank.ranking stop range exclusive
//     * @param date ranking_date field
//     */
//
//    public static void testPlayerRank(String title, int start, int last, int date){
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n AND RANK.ranking BETWEEN " + start + " AND "+last));
//
//
//
//        Tools.print(
//               ctx()
//                        .select(WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID , WTA_RANK.RANKING, WTA_RANK.POINTS)
//                        .from(WTA_PLAYER)
//                        .join(WTA_RANK)
//                        .on(WTA_RANK.PLAYER_ID.eq(WTA_PLAYER.ID))
//                        .and(WTA_RANK.RANKING_DATE.eq(date))
//                        .and(WTA_RANK.RANKING.between(start).and(last))
//                        .orderBy(WTA_RANK.RANKING)
//                        .fetch());
//
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n WHERE RANK.ranking BETWEEN " + start + " AND "+last + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//                        .select(WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID , WTA_RANK.RANKING, WTA_RANK.POINTS)
//                        .from(WTA_PLAYER)
//                        .join(WTA_RANK)
//                        .on(WTA_PLAYER.ID.eq(WTA_RANK.PLAYER_ID))
//                        .and(WTA_RANK.RANKING_DATE.eq(date))
//                        .where(WTA_RANK.RANKING.between(start).and(last))
//                        .orderBy(WTA_RANK.RANKING)
//                        .fetch());
////
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n AND RANK.ranking LT " + last  + " ORDERBY RANK.ranking"));
//        Tools.print(
//               ctx()
//
//                        .select(WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME, WTA_RANK.ID, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID , WTA_RANK.RANKING, WTA_RANK.POINTS)
//                        .from(WTA_PLAYER)
//                        .join(WTA_RANK)
//                        .on(WTA_PLAYER.ID.eq(WTA_RANK.PLAYER_ID))
//                        .and(WTA_RANK.RANKING_DATE.eq(date))
//                        .and(WTA_RANK.RANKING.lt(last))
//                        .orderBy(WTA_RANK.RANKING)
//                        .fetch());
//
//        Tools.title(title.concat(" AND RANK.ranking_date LT " + date + " \n WHERE RANK.ranking LT " + last + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//                        .select(WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME, WTA_RANK.ID, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID , WTA_RANK.RANKING, WTA_RANK.POINTS)
//                        .from(WTA_PLAYER)
//                        .join(WTA_RANK)
//                        .on(WTA_RANK.PLAYER_ID.eq(WTA_PLAYER.ID))
//                        .and(WTA_RANK.RANKING_DATE.eq(date))
//                        .where(WTA_RANK.RANKING.lt(last))
//                        .orderBy(WTA_RANK.RANKING)
//                        .fetch());
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//    }
//
//    public List<Object> findRankBeforeTournament() {
////        List<Result<Record>> values = ctx()
////                .selectDistinct()
////                .from(GRANDSLAM)
////                .innerJoin(MATCHPOINT).on(MATCHPOINT.ID.eq(GRANDSLAM.MATCH_ID))
////                .innerJoin(WTA_PLAYER).on(WTA_PLAYER.ID.eq(MATCHPOINT.P1_SCORE))
////                .innerJoin(WTA_RANK).on(WTA_PLAYER.ID.eq(WTA_RANK.PLAYER_ID))
////                .orderBy(WTA_RANK.ID.asc())
////                .fetch()
////                .intoGroups(WTA_RANK.RANKING_DATE)
////                .values()
////                .stream()
////                .toList();
//
////        if (values.isEmpty()) {
////            return Collections.emptyList();
////        }
////        return values.stream()
////                .map(r -> {
////                    MatchRecord match = r.into(MATCH.ID, TOURNAMENT.NAME, MATCH.MATCH_WINNER).get(0).into(MatchRecord.class);
////                    match.set(r.into(TOURNAMENT.ID, TOURNAMENT.NAME).get(0).into(TOURNAMENT.class));
////                    match.setPointWinner(r.sortAsc(TOPPING.ID).into(Topping.class));
////                    return match;
////                })
////                .collect(Collectors.toList());
//        return null;
//    }
//
//    public static void testCreateWtaPlayerTable() {
//
//        Tools.title("Creating WtaPlayer table, Primary key: player_id");
//        try {
//            Tools.print(
//                    ctx()
//                            .createTable("WtaPlayer")
//                            .column("player_id", INTEGER)
//                            .column("name_first",VARCHAR(255))
//                            .column("name_last", VARCHAR(255))
//                            .primaryKey("player_id")
//                            .execute());
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testCreateWtaRankingTable() {
//        Tools.title("Creating WtaRank table, Primary key: ranking_date");
//        try {
//            Tools.print(
//                    ctx()
//                            .createTable("WtaRank")
//                            .column("id", INTEGER)
//                            .column("ranking_date", INTEGER)
//                            .column("player", INTEGER)
//                            .column("rank", INTEGER)
//                            .column("points", INTEGER)
//                            .primaryKey("id")
//                            .execute());
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void testBatchInsertWtaPlayer() throws SQLException, IOException {
//        Result<Record3<Object,Object,Object>> rs = ctx().select(field("player_id"), field("name_first"), field("name_last")).from("WtaPlayers").fetch();
//
//        Tools.title("Load into WtaPlayer all records from the rs, Primary key: ranking_date");
//        Loader<WtaPlayerRecord> loader = ctx()
//                .loadInto(WTA_PLAYER)
//                .batchAll()
////                .bulkAll()
//                .commitAll()
//                .loadRecords(rs)
//                .fields(WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME)
//                .execute();
//
//        int processed = loader.processed();
//        int stored = loader.stored();
//        int ignored = loader.ignored();
//        LoaderError error = loader.errors().get(0);
//
//        System.out.println("processed: " + processed + " stored" + stored + " ignored " + ignored + " error " + error);
//
//    }
//
//        public static void testBatchInsertWtaRank() throws SQLException, IOException {
//
//        Result<Record4<Object,Object,Object,Object>> rs = ctx().select(field("ranking_date"), field("player"), field("rank"), field("points")).from("WtaRanks2000_2022").fetch();
//        System.out.println(rs.size());
//
//
//        Tools.title("Batch insert a result set to WtaRank table, Primary key: ranking_date");
//        Loader<WtaRankRecord> loader = ctx()
//                .loadInto(WTA_RANK)
//                .batchAll()
////                .bulkAll()
//                .onDuplicateKeyIgnore()
//                .commitAll()
//                .loadRecords(rs)
//                .fields(WTA_RANK.ID, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID, WTA_RANK.RANKING, WTA_RANK.POINTS)
//                .execute();
//
//        int processed = loader.processed();
//        int stored = loader.stored();
//        int ignored = loader.ignored();
//        LoaderError error = loader.errors().get(0);
//
//        DataAccessException exception = error.exception();
//        System.out.println("processed: " + processed + " stored" + stored + " ignored " + ignored + " error " + error);
//
//        int rowIndex = error.rowIndex();
//        String[] row = error.row();
//        Query query = error.query();
//
////                Tools.print(
//
////                DSL.using(new DefaultConfiguration()
////                        .set(SQLDialect.SQLITE)
////                        .set(new DefaultConnectionProvider(connection()))
////                        .set(new DefaultExecuteListenerProvider(new StatisticsListener())))
////                        .loadInto(RANK)
////                        .batchAll()
////                        .bulkAll()
////                        .commitAll()
////                        .loadRecords(rs)
////                        .fields(RANK.ID, RANK.PLAYER_ID, RANK.RANKING, RANK.POINTS)
////                        .execute());
//
//    }
//
//    private static Table<Record> getAsTable(String name) {
//        Tools.title("Get Table from Result");
//        Table<Record> table = ctx().select().from(table(name)).asTable();
//        return table;
//    }
//
//
//
//    public static void testPrimaryKeyToForeignKey(String primary_key, String foreign_key) {
//        Tools.print(
//                ctx()
//                        .select(WTA_PLAYER.ID, WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME)
//                        .from(WTA_PLAYER)
//                        .orderBy(WTA_PLAYER.ID.asc())
//                        .limit(20)
//                        .fetch());
//        Tools.print(
//                ctx()
//                        .select(WTA_RANK.ID, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID, WTA_RANK.POINTS, WTA_RANK.RANKING)
//                        .from(WTA_RANK)
//                        .orderBy(WTA_RANK.RANKING_DATE.asc())
//                        .limit(100)
//                        .fetch());
//
//    }
//
//
//    private static Result<Record1<Integer>> getOneResult(Table<?> table) {
//        Tools.title("Get One Result from param table");
//        Result<Record1<Integer>> result = ctx().selectOne().from(table).fetch();
//        return result;
//    }
//
//    private static Record getAsRecord(String name) {
//        Tools.title("Get Record from Fields");
//        Record record = ctx().newRecord();
//
//        Tools.title("Get Record from Row Number");
//        return record;
//    }
//
//    private static  SelectQuery<Record> getSelectQuery(String name){
//        SelectQuery<Record> selectQuery = ctx().select().from(table(name)).getQuery();
//        return selectQuery;
//    }
//
//    private static List<Object> getBindValues(String name){
//        Tools.title("Get only bind values");
//        List<Object> bindValues = ctx().select().from(table(name)).getBindValues();
//        return bindValues;
//    }
//
//    private static Class<? extends Record> getRecordType(String name){
//        Tools.title("Get record type produced by query string");
//        Class<? extends Record> recordType = ctx().select().from(table(name)).getRecordType();
//        return recordType;
//    }
//
//    private static Map<String, Param<?>> getParams(String name){
//        Tools.title("Get mapping of keys to parameter fields");
//        Map<String, Param<?>> tableMappings = ctx().select().from(table(name)).getParams();
//        return tableMappings;
//    }
//
//
//}
//
//
//
