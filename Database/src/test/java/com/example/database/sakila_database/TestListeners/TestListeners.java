package com.example.database.sakila_database.TestListeners;

import com.example.database.TestDatabase;
import org.jooq.DSLContext;
import org.jooq.ExecuteListener;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class TestListeners extends TestDatabase {

    @Test
    public void executeListener() {

        DSLContext c = ctx.configuration().derive(
                ExecuteListener
                        .onRenderEnd(e -> e.sql("/* some telemetry comment */ " + e.sql()))
                        .onExecuteStart(e -> out.println("Executing: " + e.sql()))
                        .onRecordEnd(e -> out.println("Fetched record: " + e.record().formatJSON()))
        ).dsl();

        c.select(
                field("Actor.first_name").as("a.first_name"),
                        field("Actor.last_name").as("a.last_name")
                )
                .from(table("actor").as("a")
                )
                .where("a.ACTOR_ID < 4L")
                .fetch();
    }
}
