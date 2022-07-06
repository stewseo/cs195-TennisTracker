package resoures;

import Database.Database;
import org.jooq.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.jooq.impl.DSL.*;

public abstract class TestUtils {
    protected static DSLContext ctx;
    protected static Configuration configuration;


    @BeforeAll
    public static void beforeClass() throws SQLException {

        ctx = using(Database.connection("sakila"));

        ctx.selectOne().toString();


    }

//    @AfterClass
//    public static void afterClass() {
//        jdbc.close();
//    }

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

