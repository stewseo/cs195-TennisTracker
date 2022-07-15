package com.example.database;

import com.example.database.sakila_database.Listeners.CustomExecuteListener;
import com.example.database.sakila_database.connection.Database;
import com.example.database.sakila_database.verifyData.VerifyDatabase;
import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.tools.JooqLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;
import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;
import static org.jooq.impl.DSL.using;
import static org.jooq.impl.DSL.field;

public abstract class TestDatabase extends VerifyDatabase {
    protected static DataSource jdbc;
    public static DSLContext ctx;
    protected static final JooqLogger log = JooqLogger.getLogger(TestDatabase.class);
    public static TestDatabase LOG_TO_TXT = new TestDatabase(){};

    protected static Configuration configuration;

    @BeforeAll
    public static void beforeClass() throws SQLException {
        log.info("Before class Connecting");
        Connection connection = Database.connect("sakila");
        ctx = using(configuration = new DefaultConfiguration()
                .set(connection)
                .set(SQLDialect.MYSQL)
                .set(new Settings()
                        .withRenderFormatted(true)
                )
//                .set(new DefaultVisitListenerProvider(new CustomVisitListener()))
                .set(new DefaultExecuteListenerProvider(new CustomExecuteListener()))
        );
        log.trace(ctx.selectOne().toString());
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
    public <T> T multiSetToTxt(T query, String fileName, String description, List<Table<?>> listOfTables){

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

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            log.info("writing output to file");
            writer.write(output.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
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