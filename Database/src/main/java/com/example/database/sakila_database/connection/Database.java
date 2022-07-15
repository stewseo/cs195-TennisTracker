package com.example.database.sakila_database.connection;

import com.example.database.sakila_database.Listeners.CustomVisitListener;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.ParseSearchSchema;
import org.jooq.conf.Settings;
import org.jooq.impl.CallbackExecuteListener;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultVisitListenerProvider;
import org.jooq.tools.JooqLogger;
import org.jooq.tools.LoggerListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.out;
import static org.jooq.conf.InvocationOrder.DEFAULT;
import static org.jooq.conf.InvocationOrder.REVERSE;
import static org.jooq.conf.ParseUnknownFunctions.IGNORE;
import static org.jooq.conf.ParseUnsupportedSyntax.FAIL;
import static org.jooq.conf.ParseWithMetaLookups.THROW_ON_FAILURE;
import static org.jooq.impl.DSL.using;

public abstract class Database {
    //============================================================================
    //                          Driver Connection,
    //                          Sql Dialect
    //                          Settings that influences how sql code is rendered,
    //                          Configurataion configures a DSLContext,
    //                          providing it with information for query rendering and execution.
    //============================================================================
    private static Connection connection;
    private static final SQLDialect MYSQL = SQLDialect.MYSQL;
    private static Settings settings;
    private static Configuration configuration;

    protected static DSLContext ctx;
    protected static final JooqLogger log = JooqLogger.getLogger(Database.class);

    static {
        ctx = null;
            try {
                if(connection == null) {
                    log.info("default connection");
                    connection = defaultConnection();
                }

                ExecuteListener listener = new LoggerListener();

                settings = new Settings()
                        .withRenderFormatted(true);

                configuration = new DefaultConfiguration()
                        .set(connection)
                        .set(MYSQL)
                        .set(settings)
                        .set(listener);

                ctx = DSL.using(configuration);
                log.info("Connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    //============================================================================
    //                      Connection
    //============================================================================

    private static final String mySqldriver = "com.mysql.cj.jdbc.Driver";
    private static StringBuilder mySQLUrl;
    private static final String user = "root";
    private static final String password = "sesame";

    public static Connection connect(String dbName) {
        mySQLUrl = new StringBuilder(
                "jdbc:mysql://localhost:3306/")
                .append(dbName)
                .append("?allowMultiQueries=true");

        connection = null;
        try {
            Class.forName(mySqldriver);
            connection = DriverManager.getConnection(mySQLUrl.toString(), user, password
            );

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static Connection defaultConnection() {
        return connect("sakila");
    }

    //============================================================================
    //                      Settings
    //============================================================================
    public Settings settingsWithParser(String schemaName) {
        settings = new Settings()
                .withParseDialect(SQLDialect.MYSQL)
                .withParseWithMetaLookups(THROW_ON_FAILURE)
                .withParseSearchPath(
                        new ParseSearchSchema().withSchema(schemaName),
                        new ParseSearchSchema().withCatalog(""))
                .withParseUnsupportedSyntax(FAIL)
                .withRenderFormatted(true)
                .withParseUnknownFunctions(IGNORE)
                .withParseIgnoreComments(true)
                .withParseIgnoreCommentStart("<ignore>")
                .withParseIgnoreCommentStop("</ignore>");
        return settings;

    }

    private static Settings defaultListenerSettings() {
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

    //============================================================================
    //                      Configuration
    //============================================================================

    private Configuration config() {
        ExecuteListener listener = getCallBackExecutionListener();

        Settings settings =
                defaultListenerSettings()
                        .withParamType(ParamType.NAMED);


        return new DefaultConfiguration()
                .set(connect("sakila"))
                .set(SQLDialect.MYSQL)
                .set(settings)
                .set(listener);
    }

    private Configuration config(Connection connection, String schemaName) {
        configuration = getConfigurationWithVisitListener(schemaName);
        return configuration
                .set(connection)
                .set(SQLDialect.MYSQL);
    }

    private Configuration parseConfig() {

        ExecuteListener listener = new LoggerListener();

        return new DefaultConfiguration()
                .set(connection)
                .set(MYSQL)
                .set(settings)
                .set(listener);
    }

    private static Configuration getParserConfiguration() {
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

    public static Configuration getConfigurationWithVisitListener(String schemaName) {
        Connection connection = connect(schemaName);
        Configuration configuration =
                new DefaultConfiguration()
                        .set(connection)
                        .set(SQLDialect.MYSQL)
                        .set(new DefaultVisitListenerProvider(new CustomVisitListener()));


        return configuration;
    }

    //============================================================================
    //                      Parsers
    //============================================================================
    public Parser getParserWithIgnoreComments() {
        settings =
                settingsWithParser("sakila")
                .withRenderFormatted(true);

        configuration = config().set(settings);

        return DSL.using(configuration).parser();
    }

    //============================================================================
    //                      Listeners
    //============================================================================
    public CallbackExecuteListener getCallBackExecutionListener() {
        return new CallbackExecuteListener()
                .onStart(ctx -> {

                    ctx.data("time", System.nanoTime());
                })
                .onEnd(ctx -> {
                    Long time = (Long) ctx.data("time");

                    out.println("Execution time : " + ((System.nanoTime() - time) / 1000 / 1000.0) + "ms. Query : " + ctx.sql());
                });

    }

}


