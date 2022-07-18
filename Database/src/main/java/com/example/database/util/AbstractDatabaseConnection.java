package com.example.database.util;


import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.exception.IOException;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.meta.Database;
import org.jooq.tools.JooqLogger;

import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static java.lang.System.out;
import static org.jooq.impl.DSL.using;

// from: org.jooq.demo.AbstractDemo and org.testcontainers.containers.JdbcDatabaseContainer
public class AbstractDatabaseConnection {
    protected static DataSource jdbc;
    public static DSLContext ctx;
    protected static final JooqLogger log = JooqLogger.getLogger(AbstractDatabaseConnection.class);
    private static Connection connection;
    protected static Configuration configuration;

    @BeforeAll
    public static void beforeClass() throws SQLException {
        log.info("Before class Connecting");
        String mySQLUrl = "jdbc:mysql://localhost:3309/";
        String mySqldriver = "com.mysql.cj.jdbc.Driver";
        Connection connection;
        String user = "root";
        String password = "sesame";
        String dbName = "docker_db";

        connection = DriverManager.getConnection(mySQLUrl, user, password);

        ctx = using(configuration = new DefaultConfiguration()
                .set(connection)
                .set(SQLDialect.MYSQL)
                .set(new Settings()
                        .withRenderFormatted(true)
                )
        );

        log.info(ctx.selectOne().toString());

        log.info("Finished setup");
    }

    @BeforeEach
    public void setup() throws SQLException {
        log.info("Before each");
    }

    @AfterEach
    public void teardown() throws SQLException {

    }
    public void cleanup(Table<?> actor, Field<Long> actorId) {
        log.info("clean up");

        ctx.delete(actor)
                .where(actorId.gt(200L))
                .execute();
    }
    public static void title(Object title) {
        println("");
        println(title);
        println("-".repeat(("" + title).length()));
    }

    public <T> T writeSqlStatementToTxt(T query, String fileName, String description, List<Table<?>> listOfTables) throws java.io.IOException {

        StringBuilder output = null;
        File file = null;

        if(isValid(fileName)){
            output = new StringBuilder(
                    fileName)
                    .append("\n")
                    .append(description)
                    .append("\n");
        }

        if(isValid(query)){}

        log.info("Creating file" + fileName);

        return query;
    }


    public <T> boolean isValid(T t) {
        if(t instanceof Query query) {
            log.info("checking query");
            return query != null || !query.getSQL().isEmpty() || !query.getSQL().isBlank();
        }
        if(t instanceof String str) {
            log.info("checking string");
            return str != null && !str.isEmpty() && !str.isBlank();
        }
        if(t instanceof Field field) {
            log.info("checking field");
            return field != null && !field.getName().isEmpty() && !field.getName().isBlank();
        }
        return t != null;
    }
    public static <T> T println(T t) {
        if(t instanceof Object[]){
            out.println(List.of(t));
        }
        out.println(t);
        return t;
    }

}
