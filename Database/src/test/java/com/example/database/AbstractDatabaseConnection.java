package com.example.database;


import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.exception.IOException;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.tools.JooqLogger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

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
import java.io.File;

import static java.lang.System.out;
import static org.jooq.impl.DSL.*;

// org.jooq.demo.AbstractDemo and org.testcontainers.containers.JdbcDatabaseContainer
public abstract class AbstractDatabaseConnection {
    protected static DataSource jdbc;
    public static DSLContext ctx;
    protected static final JooqLogger log = JooqLogger.getLogger(AbstractDatabaseConnection.class);
    public static Connection connection;

    @BeforeAll
    public static void beforeClass() throws SQLException {
        var mySQLUrl = "jdbc:mysql://localhost:3309/";
        var mySqldriver = "com.mysql.cj.jdbc.Driver";
        var user = "root";
        var password = "sesame";
        var dbName = "my_guitar_shop";
        log.info("set up");
        connection = null;

        try {
            connection = DriverManager.getConnection(mySQLUrl + dbName, user, password);

            ctx = using(connection, SQLDialect.MYSQL);

            log.info(ctx.selectOne().toString());

            log.info("Finished setup");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void afterClass() throws SQLException {
        connection.close();
    }

    @Before
    public void setup() throws SQLException {

    }

    @After
    public void teardown() throws SQLException {

    }

    public void cleanup(Table<?> actor, Field<Long> actorId) {
//        log.info("clean up");

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

        if(isValid(query)){
            output.append(queryInfo(query));
        }

        log.info("Creating file" + fileName);
        file = createTxtFile(fileName.concat(".txt"));

        for (Table<?> listOfTable : listOfTables) {
            output.append(ctx.select()
                    .from(listOfTable)
                    .fetch());
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            log.info("writing output to file");
            writer.write(output.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;

    }

    protected File createTxtFile(String fileName) {
        log.info("Creating file" + fileName);
        return new File("output_txt/".concat(fileName));

    }
    protected <T> String queryInfo(T t) {

        StringBuilder sb = new StringBuilder();

        if (t instanceof Select<?> select) {
            log.info("select: "+select);
            Query query = query(select.getSQL());

            var result = ctx.fetch(query.getSQL());

            QueryPart queryPart = list(select);
            sb
                    .append("\n\nQuery Parts:\n")
                    .append(queryPart)
                    .append("\nSql String\n")
                    .append(query.getSQL());
        }
        else if (t instanceof Query query) {
                log.info("checking sql string from query");
                String sql = query.getSQL();

                QueryPart queryPart = list(query);


                log.info("appending explain info");
                sb
                        .append("\n\nQuery Parts:\n")
                        .append(queryPart)
                        .append("\nSql String")
                        .append(sql);
            }

            return sb.toString();
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
