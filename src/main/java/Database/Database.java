package Database;

import Database.Listeners.CustomVisitListener;
import org.jooq.*;
import org.jooq.conf.*;
import org.jooq.impl.CallbackExecuteListener;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultVisitListenerProvider;

import java.sql.*;

import static com.example.cs195tennis.Util.Tools.print;
import static java.lang.System.out;
import static org.jooq.conf.InvocationOrder.DEFAULT;
import static org.jooq.conf.InvocationOrder.REVERSE;
import static org.jooq.conf.ParseUnknownFunctions.IGNORE;
import static org.jooq.conf.ParseUnsupportedSyntax.FAIL;
import static org.jooq.conf.ParseWithMetaLookups.THROW_ON_FAILURE;
import static org.jooq.impl.DSL.using;

public class Database {

    //============================================================================
    //                          Properties
    //============================================================================
    private static Connection connection;
    private static final SQLDialect MYSQL = SQLDialect.MYSQL;

//    public static void mySqlProperties() {
//
//        new org.jooq.meta.jaxb.Configuration()
//                .withJdbc(
//                        new Jdbc()
//                                .withDriver("com.mysql.cj.jdbc.Driver")
//                                .withUrl("jdbc:mysql://localhost/my_guitar_shop")
//
//                                .withUser("root")
//                                .withPassword("sesame")
//                )
//        ;
//        String sql = "SET PASSWORD FOR 'root'@'localhost' = PASSWORD('sesame') ";
//
//
//    }
//
//    private static void disconnect() {
//        ctx()
//                .configuration()
//                .systemConnectionProvider()
//                .release(connection);
//    }


    private static Settings settings;
    private static Configuration configuration;

    //============================================================================
    //                          DSLContext
    //============================================================================
    public static DSLContext ctx() {
        configuration = null;
        configuration = getParserConfiguration();


        return DSL.using(configuration);
    }

    public static DSLContext create() {

        Connection connection =
                Database.connect();

        Settings settings =
                Database.parseListenerDefaultSettings();

        Configuration configuration =
                Database.config(
                                connection)
                        .set(settings)
                        .set(connection
                        );

        return DSL.using(configuration);
    }

    static DSLContext create(Settings settings) {

        Connection connection =
                Database.connect();

        Configuration configuration =
                Database.config(
                                connection)
                        .set(settings)
                        .set(connection
                        );

        return DSL.using(configuration);
    }

    static DSLContext create(Configuration configuration) {
        connect();

        configuration =
                Database.config(
                                connection)
                        .set(settings)
                        .set(connection
                        );

        return DSL.using(configuration);
    }

    //============================================================================
    //                      Connection
    //============================================================================

    private static final String mySqldriver = "com.mysql.cj.jdbc.Driver";
    private static StringBuilder mySQLUrl;
    private static String database_name;
    private static final String user = "root";
    private static final String password = "sesame";

    public static Connection connection(String dbName) {
        mySQLUrl = new StringBuilder("jdbc:mysql://localhost:3306/");

        if (database_name.isEmpty() ||
                database_name.isBlank()) {

            database_name = "my_guitar_shop";
        }

        return connect(mySQLUrl.toString())
                ;
    }

    public static Connection connect(String dbName) {
        mySQLUrl = new StringBuilder("jdbc:mysql://localhost:3306/");

        database_name = dbName;

        mySQLUrl.append(database_name);

        try {
            Class.forName(mySqldriver);

            connection = DriverManager.getConnection(mySQLUrl.toString(), user, password
            );

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection connect() {
        connection = null;
        return connect(database_name);
    }

    //============================================================================
    //                      Settings
    //============================================================================
    public static Settings getParseSettings() {
        settings = new Settings()
                .withParseDialect(SQLDialect.MYSQL)
                .withParseWithMetaLookups(THROW_ON_FAILURE)
                .withParseSearchPath(
                        new ParseSearchSchema().withSchema("my_guitar_shop"),
                        new ParseSearchSchema().withCatalog(""))
                .withParseUnsupportedSyntax(FAIL)
                .withParseUnknownFunctions(IGNORE)
                .withParseIgnoreComments(true)
                .withParseIgnoreCommentStart("<ignore>")
                .withParseIgnoreCommentStop("</ignore>");
        return settings;

    }

    public static Settings defaultListenerSettings() {
        return new Settings()
                .withTransactionListenerStartInvocationOrder(DEFAULT)
                .withTransactionListenerEndInvocationOrder(REVERSE)
                .withVisitListenerStartInvocationOrder(DEFAULT)
                .withVisitListenerEndInvocationOrder(REVERSE)
                .withRecordListenerStartInvocationOrder(DEFAULT)
                .withRecordListenerEndInvocationOrder(REVERSE)
                .withExecuteListenerStartInvocationOrder(DEFAULT)
                .withExecuteListenerEndInvocationOrder(REVERSE);
    }

    public static Settings parseListenerDefaultSettings() {

        return settings
                .withParseDialect(SQLDialect.MYSQL)                         // Defaults to DEFAULT
                .withParseWithMetaLookups(THROW_ON_FAILURE)          // Defaults to OFF
                .withParseSearchPath(
                        new ParseSearchSchema().withCatalog(""))
                .withParseRetainCommentsBetweenQueries(true)
                .withParseUnsupportedSyntax(FAIL)
                .withRenderFormatted(true)
                .withParseUnknownFunctions(IGNORE)
                .withAttachRecords(true)
                .withParseIgnoreComments(true)
                .withParseIgnoreCommentStart("<ignore>")
                .withParseIgnoreCommentStop("</ignore>");

    }

    public static void printParseSettings(Settings parseSettings) {
        if (parseSettings == null) {
            return;
        }
        out.println("Get FetchSize: " + parseSettings.getFetchSize());
        out.println("Fetch intermediate result: " + parseSettings.getFetchIntermediateResult());
        out.println("Parse Param Cast Mode: " + parseSettings.getParamCastMode());
        out.println("Parse search paths: " + parseSettings.getParseSearchPath());
    }

    //============================================================================
    //                      Configuration
    //============================================================================

    public static Configuration config() {
        ExecuteListener listener = getCallBackExecutionListener();

        Settings settings =
                defaultListenerSettings()
                        .withParamType(
                                ParamType.NAMED
                        );


        return new DefaultConfiguration()
                .set(connect())
                .set(SQLDialect.MYSQL)
                .set(settings)
                .set(listener);
    }

    public static Configuration config(Connection connection) {
        ExecuteListener listener = getCallBackExecutionListener();

        Settings settings =
                defaultListenerSettings()
                        .withParamType(
                                ParamType.NAMED
                        );


        return new DefaultConfiguration()
                .set(connection)
                .set(SQLDialect.MYSQL)
                .set(settings)
                .set(listener);
    }

    public static Configuration parseConfig() {
        ExecuteListener listener = getCallBackExecutionListener();

        return new DefaultConfiguration()
                .set(connection)
                .set(MYSQL)
                .set(settings);
    }

    public static Configuration getParserConfiguration() {
        return
                configuration =
                        new DefaultConfiguration()
                                .set(
                                        settings
                                                .withParseDialect(SQLDialect.MYSQL)                         // Defaults to DEFAULT
                                                .withParseWithMetaLookups(THROW_ON_FAILURE)
                                                .withParseSearchPath(
                                                        new ParseSearchSchema().withCatalog(""))
                                                .withParseRetainCommentsBetweenQueries(true)
                                                .withParseUnsupportedSyntax(FAIL)
                                                .withRenderFormatted(true)
                                                .withParseUnknownFunctions(IGNORE)
                                                .withParseIgnoreComments(true)
                                                .withParseIgnoreCommentStart("<ignore>")
                                                .withParseIgnoreCommentStop("</ignore>")
                                )
                                .set(connection
                                )
                ;
    }

    public static Configuration getVisitConfiguration() {

        Configuration configuration = new DefaultConfiguration()
                .set(connection)
                .set(SQLDialect.MYSQL)
                .set(new DefaultVisitListenerProvider(new CustomVisitListener()
                        )
                ).set(new Settings().withRenderFormatted(true)
                        .withExecuteLogging(true)
                        .withDebugInfoOnStackTrace(true)
                );

        return configuration;
    }

    //============================================================================
    //                      Parsers
    //============================================================================
    public static Parser getParserWithIgnoreComments() {
        Settings parseSettings = getParseSettings();
        Configuration conn = config()
                .set(parseSettings.withRenderFormatted(true)
                );
        return DSL.using(conn).parser();
    }

    //============================================================================
    //                      Listeners
    //============================================================================
    public static CallbackExecuteListener getCallBackExecutionListener() {
        return new CallbackExecuteListener()
                .onStart(ctx -> {

                    ctx.data("time", System.nanoTime());
                })
                .onEnd(ctx -> {
                    Long time = (Long) ctx.data("time");

                    System.out.println("Execution time : " + ((System.nanoTime() - time) / 1000 / 1000.0) + "ms. Query : " + ctx.sql());
                });

    }

}


