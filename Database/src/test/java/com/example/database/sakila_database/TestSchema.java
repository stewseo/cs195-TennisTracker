package com.example.database.sakila_database;

import com.example.database.TestDatabase;
import org.jooq.Field;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockResult;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.database.sakila_database.model.Table.Actor.ACTOR;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.using;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSchema extends TestDatabase {

    @Test
    public void mockDatabaseDriverConnectionTest() throws SQLException {
        MockDataProvider p = c -> new MockResult[] {
                c.sql().contains("select")
                        ? new MockResult(ctx.newRecord(ACTOR).values(1L, "A", "A", null))
                        : new MockResult(0)
        };

        try (Connection c = new MockConnection(p)) {
            //run against the above MockDataProvider
            try (Statement s = c.createStatement();
                 ResultSet rs = s.executeQuery("select dummy from random_table")) {

                title("Using jOOQ only to fetch and pretty print the JDBC ResultSet");
                println(ctx.fetch(rs));
            }
        }

        title("Mock Jooq");
        println(using(new MockConnection(p), ctx.dialect())
                .selectFrom(ACTOR)
                .fetch()
        );
    }
    @Test
    void testSyntaxJooqQueryPartsMySql() {
        title("Verify aggregate function count(*)");
        Field<?> COUNT1 = field("count(*) x");

        Query query = ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME, COUNT1)
                .from(ACTOR)
                .where("Actor.LAST_NAME like 'A%' ")
                .groupBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .orderBy(ACTOR.FIRST_NAME.asc());

        String sqlString = """
                SELECT Actor.first_name, Actor.last_name, count(*)
                FROM Sakila.Actor
                WHERE Actor.last_name LIKE 'a%'
                GROUP BY Actor.first_name, Actor.last_name
                ORDER BY Actor.first_name asc
                """;

        Result<Record> resultJooqQuery = ctx.fetch(query.getSQL());
        Result<Record> resultSqlString = ctx.fetch(sqlString);

        assertEquals(resultJooqQuery, resultSqlString);
    }

}
