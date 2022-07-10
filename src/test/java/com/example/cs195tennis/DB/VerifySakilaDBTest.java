package com.example.cs195tennis.DB;

import org.jooq.*;
import org.jooq.Record;
import org.junit.jupiter.api.Test;

import static Actor.ACTOR;
import static org.jooq.impl.DSL.field;
import static org.junit.jupiter.api.Assertions.*;

class VerifySakilaDBTest extends VerifyDatabase {


    @Test
    void testSyntaxJooqQueryPartsMySql() {
        title("Verify aggregate function count(*)");
        Field<?> COUNT1 = field("count(*) x");
        create("sakila");
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

    @Test
    void testVerifyNestedRecords() {
    }

}