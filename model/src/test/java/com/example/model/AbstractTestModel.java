package com.example.model;
import com.example.database.DatabaseConnection;
import org.jooq.*;
import org.jooq.tools.JooqLogger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.list;

public abstract class AbstractTestModel  {
    public static DSLContext ctx;
    protected static final JooqLogger log = JooqLogger.getLogger(AbstractTestModel.class);

    @BeforeAll
    public static void beforeClass() throws SQLException {
        ctx = DatabaseConnection.ctx();
    }

    @AfterAll
    public static void afterClass() throws SQLException {

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
            output.append(DatabaseConnection.ctx().select()
                    .from(listOfTable)
                    .fetch());
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            log.info("writing output to file");
            writer.write(output.toString());

        } catch (org.jooq.exception.IOException e) {
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

            var res = DatabaseConnection.ctx().execute(query.getSQL());

            QueryPart queryPart = list(select);
            sb
                    .append("\n\nQuery Parts:\n")
                    .append(queryPart)
                    .append("\nSql String\n")
                    .append(query.getSQL())
                    .append(res);
        }
        else if (t instanceof Query query) {
            log.info("checking sql string from query");
            String sql = query.getSQL();

            QueryPart queryPart = list(query);

            var res = DatabaseConnection.ctx().execute(query.getSQL());

            log.info("appending explain info");
            sb
                    .append("\n\nQuery Parts:\n")
                    .append(queryPart)
                    .append("\nSql String")
                    .append(sql)
                    .append(res);
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
