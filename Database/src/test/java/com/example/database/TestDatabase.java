package com.example.database;

import com.example.database.Listeners.CustomVisitListener;
import com.example.database.db_connection.Database;
import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultVisitListenerProvider;
import org.jooq.tools.JooqLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;

import static org.jooq.impl.DSL.using;

public abstract class TestDatabase {

    private static Parser parser;
    public static DSLContext ctx;
    private static Schema schema;
    protected static final JooqLogger log = JooqLogger.getLogger(TestDatabase.class);

    protected static Configuration configuration;


    @BeforeAll
    public static void beforeClass() throws SQLException {
        log.info("Connecting");
        Connection connection = Database.connect("sakila");
        ctx = using(configuration = new DefaultConfiguration()
                .set(connection)
                .set(SQLDialect.MYSQL)
                .set(new Settings()
                        .withRenderFormatted(true)
                )
                .set(new DefaultVisitListenerProvider(new CustomVisitListener()))
        );

        ctx.selectOne().toString();

        log.info("Finished setup");

    }


    @BeforeEach
    public void setup() throws SQLException {
    }

    @AfterEach
    public void teardown() throws SQLException {
    }

    public void cleanup(Table<?> actor, Field<Long> actorId) {
        ctx.delete(actor)
                .where(actorId.gt(200L))
                .execute();
    }

    public static void title(Object title) {
        println("");
        println(title);
        println("-".repeat(("" + title).length()));
    }

    public static <T> T println(T t) {
        System.out.println(t);
        return t;
    }
}