package com.example.database;

import com.example.database.sakila_database.verifyData.VerifyDatabase;
import org.jooq.Field;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.jupiter.api.Test;

import static com.example.database.sakila_database.SakilaModel.Table.Actor.ACTOR;
import static org.jooq.impl.DSL.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SakilaDBTest extends VerifyDatabase {
    @Test
    void verifySchema() {
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

    @Test
    void verifyConsumingLargeRecords() {
    }

    @Test
    void verifyActiveRecords() {
    }

    @Test
    void verifyUnions() {
    }

    @Test
    void verifyWithInPredicates() {
    }

    @Test
    void verifyStandardisationLimit() {
    }

    @Test
    void verifyJoin() {
    }

    @Test
    void verifyAliasing() {
    }

    @Test
    void verifyImplicitJoins() {
    }

    @Test
    void verifyNestedRecords() {
    }

    @Test
    void testDynamicSql() {
    }
}