package testQuery.testSakila;//package com.example.cs195tennis.Dao.Table;
//
//import com.example.cs195tennis.Dao.Record.MatchRecord;
//import com.example.cs195tennis.Dao.Record.GrandSlamRecord;
//import MySqlDatabase.Connection.Database;
//import com.example.cs195tennis.Util.Tools;
//import com.example.cs195tennis.model.GrandSlam;
//import org.jooq.*;
//import org.jooq.Record;
//import org.jooq.conf.*;
//import org.jooq.exception.DataChangedException;
//import org.jooq.impl.DSL;
//import org.jooq.impl.DefaultConfiguration;
//import org.jooq.impl.DefaultExecuteListenerProvider;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Arrays;
//
//
//import static com.example.cs195tennis.Dao.PlayerDao.ctx;
//import static com.example.cs195tennis.Util.Tools.connection;
//import static com.example.cs195tennis.model.MatchModel.MATCH;
//import static com.example.cs195tennis.model.WtaPlayer.WTA_PLAYER;
//import static com.example.cs195tennis.model.WtaRank.WTA_RANK;
//import static java.lang.System.out;
//import static org.jooq.impl.DSL.*;
//import static org.jooq.impl.DSL.val;
//
//public class TestQuery {
//
//
//
//    public static void activeRecord() throws SQLException {
//        Connection conn = Database.connect();
//        Configuration configuration = new DefaultConfiguration().set(conn).set(SQLDialect.SQLITE);
//        configuration.set(new DefaultExecuteListenerProvider(new StatisticsListener()));
//
//
//        DSLContext dsl = DSL.using(configuration);
//
//        GrandSlamRecord tournamentRecord;
//
//        try {
//            Tools.title("Select from table Tournament where Tournament.id eq 1 fetch 1");
//            tournamentRecord = dsl.selectFrom(Tables.GRANDSLAM).where(Tables.GRANDSLAM.ID.eq(1)).fetchOne();
//            tournamentRecord.setYear(2020);
////            tournamentRecord.store();
//            Tools.print(tournamentRecord);
//
//
//            Tools.title("Creating a new active record");
//            tournamentRecord = dsl.newRecord(Tables.GRANDSLAM);
//            tournamentRecord.setId(3);
//            tournamentRecord.setSlam("TourneyName");
//            tournamentRecord.setYear(2020);
////            tournamentRecord.store();
//            Tools.print(tournamentRecord);
//
//
//            Tools.title("Refreshing an active record");
//            tournamentRecord = dsl.newRecord(Tables.GRANDSLAM);
//            tournamentRecord.setId(3);
//            tournamentRecord.refresh();
//            Tools.print(tournamentRecord);
//
//
//            Tools.title("Updating an active record");
//            tournamentRecord.setYear(2020);
////            tournamentRecord.store();
//            Tools.print(tournamentRecord);
//
//
//            Tools.title("Deleting an active record");
//            tournamentRecord.delete();
//            Tools.print(ctx().selectFrom(Tables.GRANDSLAM).fetch());
//
//
//            for (ExecuteType type : ExecuteType.values()) {
//                out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//            }
//        }
//
//
//        // Don't store the changes
//        finally {
//            conn.rollback();
//        }
//    }
//
//    public static void testBuildingQueryString() {
//
//        Table<?> wtaPlayerTable = table("WtaPlayer");
//
//        Field<?> wtaPlayerId = field("player_id");
//        Field<?> wtaPlayerFirstName = field("name_first");
//        Field<?> wtaPlayerLastName = field("name_last");
//
//        DSLContext ctx = ctx();
//        Tools.print(
//                select(wtaPlayerId)
//                        .from(wtaPlayerTable)
//                        .fetch());
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName)
//                        .from(wtaPlayerTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName)
//                        .from(wtaPlayerTable)
//                        .orderBy(wtaPlayerFirstName));
//
//        Table<?> wtaRankTable = table("WtaRank");
//
//        Field<?> wtaRankDate = field("ranking_date");
//        Field<?> wtaRankPlayerId = field("player");
//        Field<?> wtaRankPoints = field("player");
//        Field<?> wtaRank = field("name_last");
//        Field<?> wtaRankId = field("id");
//
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .innerJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .crossJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .leftJoin(wtaRankTable));
//
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .rightJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .fullJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .fullOuterJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .naturalJoin(wtaRankTable));
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .leftAntiJoin(wtaRankTable));
//
//    }
//
//    public static void testExecute() {
//        Connection conn = connection();
//
//        DSLContext dsl = DSL.using(conn, new Settings().withExecuteWithOptimisticLocking(true));
//
//        Tools.title("Selecting tourney_name and date from the TestTournament table");
//        Tools.print(
//                dsl
//                        .select(WTA_PLAYER.FIRST_NAME, WTA_PLAYER.LAST_NAME,WTA_PLAYER.ID)
//                        .from(WTA_PLAYER)
//                        .orderBy(WTA_PLAYER.ID)
//                        .fetch()
//        );
//
//
//        Tools.title("Selecting tourney_name and date from the TestTournament table");
//        Tools.print(
//                dsl
//                        .select(WTA_RANK.RANKING_DATE, WTA_RANK.RANKING, WTA_RANK.PLAYER_ID)
//                        .from(WTA_RANK)
//                        .orderBy(WTA_RANK.RANKING_DATE)
//                        .fetch()
//        );
//    }
//
//    public static void testPredicate() {
//
//        Settings settings = new Settings()
//                .withRenderMapping(new RenderMapping()
//                        .withSchemata(
//                                new MappedSchema().withInput("Test")
//                                        .withOutput("public")));
//        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE, settings);
//
//        Tools.title("Test ids from tournament and match tables using and");
//        Tools.print(
//                ctx().select()
//                        .from(GrandSlam.GRANDSLAM)
//                        .where(GrandSlam.GRANDSLAM.ID.like("%a%").and(MATCH.ID.eq(1)))
//                        .fetch()
//        );
//
//
//        Tools.title("Test Tournament and Match tables using in");
//        Tools.print(
//                ctx().select()
//                        .from(Tables.GRANDSLAM)
//                        .where(Tables.GRANDSLAM.ID.in(select(MATCH.ID).from(MATCH)))
//                        .fetch()
//        );
//    }
//
//    public static void expressColumn() {
//        Connection conn = connection();
//
//        DSLContext dsl = DSL.using(conn, new Settings().withExecuteWithOptimisticLocking(true));
//
//        Tools.title("CONCAT() function with prefix notation");
//        Tools.print(
//                dsl.select(DSL.concat(Tables.GRANDSLAM.NAME, val(" "), Tables.GRANDSLAM.MATCH_ID))
//                        .from(Tables.GRANDSLAM)
//                        .orderBy(Tables.GRANDSLAM.NAME)
//                        .fetch()
//        );
//
//        Tools.title("CONCAT() function with infix notation");
//        Tools.print(
//                dsl.select(Tables.GRANDSLAM.NAME.concat(" ").concat(Tables.GRANDSLAM.MATCH_ID))
//                        .from(Tables.GRANDSLAM)
//                        .orderBy(Tables.GRANDSLAM.NAME)
//                        .fetch()
//        );
//
//
//        for (ExecuteType type : ExecuteType.values()) {
//            out.println("name " + type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//        }
//    }
//
//
//
//    public static void optimisticLocking() throws SQLException {
//        Connection connection = connection();
//        DSLContext dsl = DSL.using(connection, new Settings().withExecuteWithOptimisticLocking(true));
//        try {
//            Tools.title("Applying optimistic locking, database does not hold row locks between selecting and updating or deleting a row.");
//
//            MatchRecord match1 = dsl.selectFrom(MATCH).where(MATCH.SET_NO.eq(1)).fetchOne();
//
//        } catch (DataChangedException expected) {
//            expected.printStackTrace();
//        } finally {
//            connection.rollback();
//        }
//    }
//
//    public static void checkedExecptions() {
//        Connection connection = connection();
//
//        Tools.title("Test with null fields, tables");
//        DSL.using(connection)
//                .select(Tables.GRANDSLAM.NAME)
//                .from(Tables.GRANDSLAM)
//                .fetch()
//                .forEach(record -> out.println(record.get(Tables.GRANDSLAM.NAME)));
//    }
//
//    public static void resultSets() {
//        Connection connection = connection();
//        //select * from PointByPoint
//        Tools.title("For each results from table PointByPointGrandSlams");
//        for (Record record : DSL.using(connection)
//                .select(Tables.GRANDSLAM.ID, Tables.GRANDSLAM.NAME)
//                .from(Tables.GRANDSLAM)
//                .limit(100)
//                .fetch()) {
//            out.println(record);
//        }
//
//        //Returns a stream consisting of the results of replacing each element of this stream with the contents
//        // of a mapped stream produced by applying the provided
//        // mapping function to each element.
//        //Each mapped stream is closed after its contents have been placed into this stream. (If a mapped stream is null an empty stream is used, instead.)
//        //The flatMap() operation has the effect of applying a one-to-many transformation to the elements of the stream,
//        // and then flattening the resulting elements into a new stream.
//        Tools.title("Stream GrandSlams");
//        DSL.using(connection)
//                .select(MATCH.ID, MATCH.SET_NO)
//                .from(MATCH)
//                .fetch()
//                .stream()
//                .flatMap(record -> Arrays.stream(record.intoArray()))
//                .limit(100)
//                .forEach(out::println);
//    }
//
//    public static void preparedStatements() throws SQLException {
//        Connection connection = connection();
//
//        Tools.title("Distinguishing between static and prepared statements with JDBC. PreparedStatement is a pre-compiled statement" +
//                "meaning the same SQL command can be used multiple times which accounts for better performance and faster results.");
//
//        // 99% of the time
//        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM GrandSlams")) {
//            stmt.execute();
//        }
//
//        Tools.title("Distinguishing between static and prepared statements with jOOQ");
//        // 1% of the time
//        out.println(
//                DSL.using(connection, new Settings().withStatementType(StatementType.STATIC_STATEMENT))
//                        .fetch("SELECT * FROM GrandSlams")
//        );
//
//        // 99% of the time
//        out.println(
//                DSL.using(connection)
//                        .fetch("SELECT * FROM GrandSlams")
//        );
//    }
//
//    public static void resultAndStatement() {
//        Connection connection = connection();
//
//        Tools.title("JDBC prepare statement syntax");
//        try (PreparedStatement stmt = connection.prepareStatement("SELECT slam FROM GrandSlams")) {
//            boolean moreResults = stmt.execute();
//
//            do {
//                if (moreResults) {
//                    try (ResultSet rs = stmt.getResultSet()) {
//                        while (rs.next()) {
//                            out.println(rs.getString(1));
//                        }
//                    }
//                }
//                else {
//                    out.println(stmt.getUpdateCount());
//                }
//            } while ((moreResults = stmt.getMoreResults()) || stmt.getUpdateCount() != -1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Tools.title("Type is specified");
//        Query q1 = DSL.using(connection).query("UPDATE TOURNAMENT SET tourney_name = tourney_name");
//        out.println(
//                q1.execute()
//        );
//
//        ResultQuery<Record> q2 = DSL.using(connection).resultQuery("SELECT * FROM TOURNAMENT");
//        out.println(
//                q2.fetch()
//        );
//    }
//    public static void settings(){
//        Select<?> select =
//                DSL.select(Tables.GRANDSLAM.NAME, Tables.GRANDSLAM.MATCH_ID, Tables.GRANDSLAM.YEAR)
//                        .from(Tables.GRANDSLAM)
//                        .where(Tables.GRANDSLAM.YEAR.eq(2021));
//
//        Tools.title("settings for Formatting");
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderFormatted(false)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderFormatted(true)).render(select));
//
//        Tools.title("settings for Schema");
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderSchema(false)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderSchema(true)).render(select));
//
//        Tools.title("settings for name case");
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderNameCase(RenderNameCase.AS_IS)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderNameCase(RenderNameCase.UPPER)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderNameCase(RenderNameCase.LOWER)).render(select));
//
//        Tools.title("settings for Name quoting");
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderQuotedNames(RenderQuotedNames.ALWAYS)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_QUOTED)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderQuotedNames(RenderQuotedNames.NEVER)).render(select));
//
//        Tools.title("settings for keyword case");
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderKeywordCase(RenderKeywordCase.AS_IS)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderKeywordCase(RenderKeywordCase.LOWER)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderKeywordCase(RenderKeywordCase.UPPER)).render(select));
//        out.println(using(SQLDialect.SQLITE, new Settings().withRenderKeywordCase(RenderKeywordCase.PASCAL)).render(select));
//
//        Tools.title("Settings for Mapping");
//        out.println(using(SQLDialect.SQLITE, new Settings()
//                .withRenderMapping(new RenderMapping()
//                        .withSchemata(new MappedSchema()
//                                .withInput("PUBLIC")
//                                .withOutput("test")
//                                .withTables(new MappedTable()
//                                        .withInput("GRANDSLAMS")
//                                        .withOutput("test-grandslam"))
//                        )
//                )).render(select));
//
//    }
//
//
//}
