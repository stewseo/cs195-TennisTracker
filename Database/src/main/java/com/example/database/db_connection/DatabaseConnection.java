package com.example.database.db_connection;

import com.example.database.Listeners.CustomVisitListener;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.ParseSearchSchema;
import org.jooq.conf.Settings;
import org.jooq.impl.CallbackExecuteListener;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultVisitListenerProvider;

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

public abstract class DatabaseConnection {

    //============================================================================
    //                          Properties
    //============================================================================
    private static Connection connection;
    private static final SQLDialect MYSQL = SQLDialect.MYSQL;


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
    public DSLContext ctx() {
        configuration = null;
        configuration = getParserConfiguration();


        return DSL.using(configuration);
    }

    public static DSLContext create() {

        Connection connection = connect("sakila");

        Settings settings = defaultListenerSettings();

        Configuration configuration = new DefaultConfiguration()
                .set(connection)
                .set(settings);

        return DSL.using(connection,SQLDialect.MYSQL);
    }

    DSLContext create(Settings settings) {

        Connection connection =
                connect("sakila");

        Configuration configuration =
                config(connection)
                        .set(settings)
                        .set(connection
                        );

        return DSL.using(configuration);
    }

     DSLContext create(Configuration configuration) {
        connect("sakila");

        configuration =
                config(connection)
                        .set(settings)
                        .set(connection)
        ;

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
                        .withParamType(ParamType.NAMED)
                ;


        return new DefaultConfiguration()
                .set(connect("sakila"))
                .set(SQLDialect.MYSQL)
                .set(settings)
                .set(listener);
    }

    private Configuration config(Connection connection) {
        CustomVisitListener listener = new CustomVisitListener();
        configuration = getConfigurationWithVisitListener();
        return configuration
                .set(connection)
                .set(SQLDialect.MYSQL)
                .set(listener);
    }

    private Configuration parseConfig() {
        ExecuteListener listener = getCallBackExecutionListener();

        return new DefaultConfiguration()
                .set(connection)
                .set(MYSQL)
                .set(settings);
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

    public static Configuration getConfigurationWithVisitListener() {

        Configuration configuration = new DefaultConfiguration()
                .set(new DefaultVisitListenerProvider(new CustomVisitListener())
                )
                .set(connect("sakila"))
                .set(
                        new Settings()
                                .withRenderFormatted(true)
                                .withExecuteLogging(true))
                ;
        return configuration;
    }

    //============================================================================
    //                      Parsers
    //============================================================================
    public Parser getParserWithIgnoreComments() {
        Settings parseSettings = settingsWithParser("sakila");
        Configuration conn = config()
                .set(parseSettings.withRenderFormatted(true)
                );
        return DSL.using(conn).parser();
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


