//package com.example.cs195tennis;
//
//import com.example.cs195tennis.Data.Record.AtpPlayerRecord;
//import com.example.cs195tennis.Data.Record.AtpRankRecord;
//import Data.verify.StatisticsListener;
//import com.example.cs195tennis.Util.Tools;
//import org.jooq.*;
//import org.jooq.Record;
//import org.jooq.exception.DataAccessException;
//import org.jooq.impl.*;
//import org.jooq.meta.jaxb.Logging;
//import org.jooq.meta.jaxb.OnError;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Comparator;
//import java.util.Map;
//
//import static com.example.cs195tennis.Data.Table.Tables.*;
//import static com.example.cs195tennis.Util.Tools.connection;
//import static com.example.cs195tennis.model.AtpRank.ATP_RANK;
//import static com.example.cs195tennis.model.WtaRank.WTA_RANK;
//import static java.lang.System.out;
//import static org.jooq.impl.DSL.*;
//import static org.jooq.impl.SQLDataType.INTEGER;
//import static org.jooq.impl.SQLDataType.VARCHAR;
//
//public class TestAtpPlayerAtpRank {
//
//    public static DSLContext ctx() {
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
//
//        Configuration configuration = new DefaultConfiguration().set(connection()).set(SQLDialect.SQLITE);
//        return DSL.using(configuration
//                .set(SQLDialect.MYSQL)
//                .set(new DefaultConnectionProvider(connection()))
////                .set(
////                        new StatisticsListener(),
////                        new LoggerListener(),
////                        new StopWatchListener()
//                );
//    }
//
//    public static void unwrap() {
//        SelectField<?>[] select = {
//                WTA_PLAYER.FIRST_NAME.concat(WTA_PLAYER.LAST_NAME),
//                count()
//        };
//
//        Table<?> from = WTA_PLAYER.join(WTA_RANK)
//                .on(WTA_PLAYER.PLAYER_ID.eq(WTA_RANK.PLAYER_ID));
//
//        GroupField[] groupBy = { WTA_PLAYER.PLAYER_ID, WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME };
//
//        SortField<?>[] orderBy = { count().desc() };
//
//        ctx().select(select)
//                .from(from)
//                .groupBy(groupBy)
//                .orderBy(orderBy)
//                .fetch();
//    }
//
//    public Condition condition(Result<Record> rs) {
//        Condition result = noCondition();
//
//        if (rs == null) {
//            return null;
//        }
//        if(rs.get(0) != null) {
//            result = result.and(ATP_PLAYER.FIRST_NAME.like("%" + rs.get(0) + "%"));
//        }
//
//        if (rs.get(1) != null)
//            result = result.and(ATP_RANK.PLAYER_ID.in(
//                    selectOne().from(ATP_PLAYER).where(
//                            ATP_PLAYER.LAST_NAME.like("%" + rs.get(1) + "%")
//                                    .or(ATP_PLAYER.LAST_NAME .like("%" + rs.get(1) + "%"))
//                    )
//            ));
//
//        return result;
//    }
//
//
//
//    /**
//     * @key = ranking_date
//     */
//    public static void joinTableTest() {
//        String title = "Expected Result: Wta Player Rankings ";
//
//        Map<Integer, Result<Record5<Integer, Integer, Integer, Integer, Integer>>> mapByDate =
//                ctx()
//                        .select(WTA_RANK.ID, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID, WTA_RANK.RANKING, WTA_RANK.POINTS)
//                        .from(WTA_RANK)
//                        .fetchGroups(WTA_RANK.RANKING_DATE);
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
//    public static void testPlayerRank(String title, int start, int last, int date){
//
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n AND RANK.ranking BETWEEN " + start + " AND "+last));
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_PLAYER)
//                        .join(ATP_RANK)
//                        .on(ATP_RANK.PLAYER_ID.eq(ATP_PLAYER.PLAYER_ID))
//                        .and(ATP_RANK.RANKING_DATE.eq(date))
//                        .and(ATP_RANK.RANKING.between(start).and(last))
//                        .orderBy(ATP_RANK.RANKING)
//                        .fetch());
//
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n WHERE RANK.ranking BETWEEN " + start + " AND "+last + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_PLAYER)
//                        .join(ATP_RANK)
//                        .on(ATP_PLAYER.PLAYER_ID.eq(ATP_RANK.PLAYER_ID))
//                        .and(ATP_RANK.RANKING_DATE.eq(date))
//                        .where(ATP_RANK.RANKING.between(start).and(last))
//                        .orderBy(ATP_RANK.RANKING)
//                        .fetch());
////
//        Tools.title(title.concat(" AND RANK.ranking_date EQ " + date + " \n AND RANK.ranking LT " + last  + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_PLAYER)
//                        .join(ATP_RANK)
//                        .on(ATP_PLAYER.PLAYER_ID.eq(ATP_RANK.PLAYER_ID))
//                        .and(ATP_RANK.RANKING_DATE.eq(date))
//                        .and(ATP_RANK.RANKING.lt(last))
//                        .orderBy(ATP_RANK.RANKING)
//                        .fetch());
//
//        Tools.title(title.concat(" AND RANK.ranking_date LT " + date + " \n WHERE RANK.ranking LT " + last + " ORDERBY RANK.ranking"));
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID , ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_PLAYER)
//                        .join(ATP_RANK)
//                        .on(ATP_RANK.PLAYER_ID.eq(ATP_PLAYER.PLAYER_ID))
//                        .and(ATP_RANK.RANKING_DATE.eq(date))
//                        .where(ATP_RANK.RANKING.lt(last))
//                        .orderBy(ATP_RANK.RANKING)
//                        .fetch());
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//    }
//
//    public static void testTableFieldsPlayer() {
//        Tools.title("Test results order by first name");
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME)
//                        .from(ATP_PLAYER)
//                        .groupBy(ATP_PLAYER.FIRST_NAME)
//                        .limit(40)
//                        .fetch());
//
//        Tools.title("Test results order by last name");
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME)
//                        .from(ATP_PLAYER)
//                        .groupBy(ATP_PLAYER.LAST_NAME)
//                        .limit(40)
//                        .fetch());
//
//        Tools.title("Test order by player_id");
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME)
//                        .from(ATP_PLAYER)
//                        .orderBy(ATP_PLAYER.PLAYER_ID.asc())
//                        .limit(40)
//                        .fetch());
//
//        Tools.title("Test descending order player_id");
//        Tools.print(
//                ctx()
//                        .select(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME)
//                        .from(ATP_PLAYER)
//                        .orderBy(ATP_PLAYER.PLAYER_ID.desc())
//                        .limit(40)
//                        .fetch());
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//
//    }
//
//    public static void testAtpRank() {
//
//
//        Tools.title("Test that first 10 records in AtpRank table equal order by AtpRank.ranking_date");
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.POINTS, ATP_RANK.RANKING)
//                        .from(ATP_RANK)
//                        .orderBy(ATP_RANK.RANKING_DATE.asc())
//                        .limit(10)
//                        .fetch());
//
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.POINTS, ATP_RANK.RANKING)
//                        .from(ATP_RANK)
//                        .limit(10)
//                        .fetch());
//
//        Tools.title("Test order by ranking each weekly update equals expected record");
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.POINTS, ATP_RANK.RANKING)
//                        .from(ATP_RANK)
//                        .where(ATP_RANK.RANKING_DATE.eq(20220110))
//                        .orderBy(ATP_RANK.POINTS)
//                        .limit(10)
//                        .fetch());
//
//
//        Tools.title("Fetch SELECT Fields FROM AtpRank order by player_id desc ");
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.POINTS, ATP_RANK.RANKING)
//                        .from(ATP_RANK)
//                        .where(ATP_RANK.PLAYER_ID.gt(200001))
//                        .and(ATP_RANK.PLAYER_ID.lt(300000))
//                        .orderBy(ATP_RANK.RANKING_DATE)
//                        .limit(10)
//                        .fetch());
//
//        Tools.title("Fetch SELECT Fields FROM WtaRank WHERE WtaRank.points EQ 1");
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.RANKING, ATP_RANK.POINTS)
//                        .from(ATP_RANK)
//                        .where(ATP_RANK.RANKING.eq(500))
//                        .fetch());
//
//        Tools.title("Fetch SELECT Fields FROM WtaRank WHERE WtaRank.ranking EQ 1 LIMIT 5");
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.POINTS, ATP_RANK.RANKING)
//                        .from(ATP_RANK)
//                        .where(ATP_RANK.RANKING.eq(1))
//                        .limit(5)
//                        .fetch());
//
//        Tools.title("Fetch SELECT fields() FROM WtaRank WHERE RANK.ranking LESS THAN 10 AND 10 AND RANK.ranking_date EQ 01/03/2000 ORDERBY RANK.points ");
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.POINTS, ATP_RANK.RANKING)
//                        .from(ATP_RANK)
//                        .where(ATP_RANK.RANKING.lt(10))
//                        .and(ATP_RANK.RANKING_DATE.eq(20220103))
//                        .fetch());
//
//        Tools.title("Fetch SELECT fields() FROM WtaRank WHERE RANK.ranking BETWEEN 1 AND 10 AND RANK.ranking_date EQ 01/03/2000 ORDERBY RANK.points ");
//        Tools.print(
//                ctx()
//                        .select(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.POINTS, ATP_RANK.RANKING)
//                        .from(ATP_RANK)
//                        .where(ATP_RANK.RANKING_DATE.eq(20220103))
//                        .and(ATP_RANK.RANKING.between(1).and(5))
//                        .fetch());
//
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//
//    }
//
//    public static void testCreateAtpPlayerTable() {
//
//        Tools.title("Creating AtpPlayer table, Primary key: id");
//        try {
//            Tools.print(
//                    ctx()
//                            .createTable("AtpPlayer")
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
//    public static void testCreateAtpRankingTable() {
//        Tools.title("Creating WtaRank table, Primary key: ranking_date");
//        try {
//            Tools.print(
//                    ctx()
//                            .createTable("AtpRank")
//                            .column("id", INTEGER)
//                            .column("ranking_date", INTEGER)
//                            .column("player_id", INTEGER)
//                            .column("rank", INTEGER)
//                            .column("points", INTEGER)
//                            .primaryKey("id")
//                            .execute());
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testBatchInsertAtpPlayer() throws SQLException, IOException {
//        Result<Record3<Object,Object,Object>> rs = ctx().select(field("player_id"), field("name_first"), field("name_last")).from("AtpPlayer").fetch();
//
//        Tools.title("Load into WtaPlayer all records from the rs, Primary key: ranking_date");
//
//        new org.jooq.meta.jaxb.Configuration().withLogging(Logging.WARN);
//        new org.jooq.meta.jaxb.Configuration().withOnError(OnError.LOG);
//
//        Loader<AtpPlayerRecord> loader = ctx()
//                .loadInto(ATP_PLAYER)
//                .batchAll()
////                .bulkAll()
//                .commitAll()
//                .loadRecords(rs)
//                .fields(ATP_PLAYER.PLAYER_ID, ATP_PLAYER.FIRST_NAME, ATP_PLAYER.LAST_NAME)
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
//    private static void testKeys(Schema schema) {
//        //Returns a stream consisting of the results of replacing each element of this stream with the contents
//        //of a mapped stream produced by applying the provided
//        // mapping function to each element.
//        //Each mapped stream is closed after its contents have been placed into this stream. (If a mapped stream is null an empty stream is used, instead.)
//        //The flatMap() operation has the effect of applying a one-to-many transformation to the elements of the stream,
//        // and then flattening the resulting elements into a new stream.
//        Tools.title("Test Primary Keys");
//        DSL.using(Tools.connection())
//                .meta()
//                .getTables()
//                .stream()
//                .flatMap(Fields::fieldStream)
//                .limit(200)
//                .forEach(out::println);
//    }
//
//    public static void testBatchInsertAtpRank() throws SQLException, IOException {
//
//        Result<Record5<Integer,Integer,Integer,Integer,Integer>> rs = ctx().select(ATP_RANK.ID,ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.RANKING, ATP_RANK.POINTS).from("AtpPlayerRanking").fetch();
//        System.out.println(rs.size());
//
//
//        Tools.title("Batch insert a result set to AtpRank table, Primary key: ranking_date");
//        Loader<AtpRankRecord> loader = ctx()
//                .loadInto(ATP_RANK)
//                .batchAll()
////                .bulkAll()
//                .onDuplicateKeyIgnore()
//                .commitAll()
//                .loadRecords(rs)
//                .fields(ATP_RANK.ID, ATP_RANK.RANKING_DATE, ATP_RANK.PLAYER_ID, ATP_RANK.RANKING, ATP_RANK.POINTS)
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
//    }
//
//
//
//}
