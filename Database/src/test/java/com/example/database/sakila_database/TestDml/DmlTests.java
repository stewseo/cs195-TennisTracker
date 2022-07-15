package com.example.database.sakila_database.TestDml;

import com.example.database.TestDatabase;
import com.example.database.sakila_database.model.Table.Record.ActorRecord;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.example.database.sakila_database.model.Tables.ACTOR;

public class DmlTests extends TestDatabase {

    @Test
    public void updatableRecords() {
        ActorRecord a1 = ctx.newRecord(ACTOR);
        a1.setActorId(201L);
        a1.setFirstName("Jane");
        a1.setLastName("Doe");
        a1.insert();

        ActorRecord a2 =
                ctx.selectFrom(ACTOR)
                        .where(ACTOR.ACTOR_ID.eq(201L))
                        .fetchSingle();

        a2.setLastName("Smith");
        a2.store();
    }

    @Test
    public void dml() {
        title("All sorts of classic bulk DML statements are available");

        ctx.insertInto(ACTOR)
                .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .values(201L, "Jon", "Doe")
                .values(202L, "Jane", "Smith")
                .execute();

        ctx.update(ACTOR)
                .set(ACTOR.LAST_NAME, "X")
                .where(ACTOR.ACTOR_ID.gt(200L))
                .execute();
    }

    @Test
    public void batchUpdatableRecords() {
        title("A set of updatable records can be conveniently batch stored, inserted, updated");
        ActorRecord a1 = ctx.newRecord(ACTOR);
        ActorRecord a2 = ctx.newRecord(ACTOR);

        a1.setActorId(201L);
        a1.setFirstName("John");
        a1.setLastName("Doe");

        a2.setActorId(202L);
        a2.setFirstName("Jane");
        a2.setLastName("Smith");

        ctx.batchStore(a1, a2).execute();
    }

    @Test
    public void batchManually() {
        ctx.batch(
                        ctx.insertInto(ACTOR)
                                .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)


                                .values((Long) null, null, null)
                )
                .bind(201L, "Jon", "Doe")
                .bind(202L, "Jane", "Smith")
                .execute();
    }

    @Test
    public void teardown() throws SQLException {
        cleanup(ACTOR, ACTOR.ACTOR_ID);
        super.teardown();
    }
}
