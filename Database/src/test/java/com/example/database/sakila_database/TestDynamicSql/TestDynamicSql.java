package com.example.database.sakila_database.TestDynamicSql;

import com.example.database.TestDatabase;
import org.jooq.Record2;
import org.jooq.ResultQuery;
import org.junit.jupiter.api.Test;

import static com.example.database.sakila_database.model.Table.Actor.ACTOR;

public class TestDynamicSql extends TestDatabase {

    @Test
    void testDynamicSql() {
        ResultQuery<Record2<String, String>> res =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.ACTOR_ID.in(1L, 2L, 3L))
                        .orderBy(ACTOR.FIRST_NAME)
                        .limit(5);

        String sql = """
                SELECT 
                    a.first_name, 
                    a.last_name
                FROM actor a
                WHERE a.actor_id 
                    IN ('1L', '2L', '3L')
                ORDER BY first_name
                LIMIT 5
                """;

    }
}
