package com.example.cs195tennis.VerifyDataIntegrity;

import Database.Model.SakilaModel.Record.ActorRecord;
import Database.Model.SakilaModel.Table.ActorTable;
import Database.Model.SakilaModel.Table.FilmActorTable;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.exception.NoDataFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static Database.Model.SakilaModel.Table.ActorTable.ACTOR;
import static Database.Model.SakilaModel.Table.CustomerTable.CUSTOMER;
import static Database.Model.SakilaModel.Table.FilmActorTable.FILM_ACTOR;
import static Database.Model.SakilaModel.Table.FilmTable.FILM;
import static org.jooq.impl.DSL.*;

public class VerifySakilaDB extends VerifyDatabase {

    //=================================================================
    //Create instance with DSLContext using "Sakila" as db
    //================================================================
    public VerifySakilaDB() {
        try {
            create("sakila");

        } catch (NoDataFoundException e) {
            e.printStackTrace();
        }
        log.info("Use Sakila");
    }

    //=================================================================
    //Verify Sakila DB is used. Write table info to text
    //================================================================
    public void verifyCurrentSchema() throws IOException {

        Cursor<Record1<String>> currentSchemaReference
                = ctx.select(currentSchema()).fetchLazy();

        logSchemaToTextFile("sakila");

        log.info("Schema Verified");

    }

    //================================================================
    //Verify there are tables in Sakila DB. Query to sqlString and actual String sql.
    //================================================================
    protected void verifyTablesInDatabase() throws FileNotFoundException {
        List<Table<?>> listOfTables = ctx
                .meta()
                .getSchemas("sakila")
                .get(0)
                .getTables();

        log.trace("Debug ");

    }

    //=================================================================
    //    Verify Records of a simple query
    //    write analysis of query, Query String, and actual String txt file
    //================================================================
    public void verifyAggregateAfterGroupBy() throws FileNotFoundException {

        Field<?> COUNT1 = field("count(*) x");

        Query query =          ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME, COUNT1)
                .from(ACTOR)
                .where("Actor.LAST_NAME like 'A%' ")
                .orderBy(ACTOR.FIRST_NAME.asc())
                ;

        String sqlString = """
                SELECT Actor.first_name, Actor.last_name, count(*)
                FROM Sakila.Actor
                WHERE Actor.last_name LIKE 'a%'
                GROUP BY Actor.first_name, Actor.last_name
                ORDER BY Actor.first_name asc
                """;

        verifyQuerySyntax(query, sqlString);

        ResultQuery<Record> resultQuery = ctx.resultQuery(query.getSQL());
        verifyQueryResults(resultQuery);

        log.info(query);

        Cursor<Record> cursor = ctx.fetchLazy(query.getSQL());

        getSteps(cursor);


        log.info("Next record: " + cursor.fetchNext());

        log.trace("trace ");
        for (var r : ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.lessThan(5L))
        ) {
            println("Actor: %s %s".formatted(r.value1(), r.value2()));
        }

        ctx.select(FILM.FILM_ID, FILM.TITLE)
                .from("FILM ")
                .limit(5)
                .forEach(r -> println("Film %s: %s".formatted(r.value1(), r.value2()
                                )
                        )
                );

    }

    protected void verifyConsumingLargeRecords() {
        title("Imperative consumption of large results using Cursor, keeping an open ResultSet behind the scenes");
        try (Cursor<Record2<String, String>> c = ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.lt(5L))
                .fetchLazy()
        ) {
            for (Record2<String, String> r : c)
                println("Actor: %s %s".formatted(r.value1(), r.value2()));
        }
        title("Functional consumption of large results using Stream, keeping an open ResultSet behind the scenes");

        try (Stream<Record2<String, String>> s = ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.lt(5L))
                .fetchStream()
        ) {
            s.forEach(r -> println("Actor: %s %s".formatted(r.value1(), r.value2())));
        }
    }

    public void verifyTypeSafeActiveRecord() throws FileNotFoundException {
        title("Verify Type safe custom Record");

        Query query = ctx
                .selectFrom(ACTOR)
                .where(ACTOR.ACTOR_ID.eq(1L));

        ActorRecord actor = ctx
                .selectFrom(ACTOR)
                .where(ACTOR.ACTOR_ID.eq(1L))
                .fetchSingle();


        String sqlString = query.getSQL();
        analyzeQuery(query, sqlString);

        println("Resulting actor: %s %s".formatted(actor.get(ACTOR.FIRST_NAME), actor.get(ACTOR.LAST_NAME)));

    }

    public void verifyTypeSafeUnion() throws FileNotFoundException {
        title("Verify UNION / INTERSECT / EXCEPT ");

        Query query =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where("Actor.first_name like 'A%' ")
                        .union(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                                .from(CUSTOMER)
                                .where("Customer.first_name LIKE 'A%'"));

        String sqlString = query.getSQL();
        analyzeQuery(query, sqlString);
        var result = ctx.fetch(query.getSQL());
    }

    public void verifyTypeSafetyWithInPredicate() throws FileNotFoundException {

        title("In Predicates\n" +
                "Verification for Models: ActorTable, FilmActorTable. Verify Record Data Type safety: ActorRecord, FilmActorRecord");
        Query query1 =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.FIRST_NAME.like("A%"))
                        .and(ACTOR.ACTOR_ID.in(select(FILM_ACTOR.ACTOR_ID).from(FILM_ACTOR)))
                        ;
        var v1 = query1;
        String sqlString = query1.getSQL();
        analyzeQuery(query1, sqlString);

        title("Row Value expressions\n" +
                "Verify Models: ActorTable, FilmActorTable. Verify Record Data Type safety: ActorRecord, FilmActorRecord");
        Query query2 =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.FIRST_NAME.like("A%"))
                        .and(row(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                                .in(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                                        .from(CUSTOMER)))
                        ;
        var v2 = query2;

        sqlString = query1.getSQL();
        analyzeQuery(query2, sqlString);
    }

    public void verifyStandardisationLimit() {
        title("Verify LIMIT, OFFSET select Fields Actor.first_name and Actor.last_name");
        var r1 =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.FIRST_NAME.like("A%"))
                        .orderBy(ACTOR.ACTOR_ID)
                        .limit(10)
                        .offset(10)
                        .fetch();
    }

    public void verifySyntax() throws FileNotFoundException {
        title("Verify MySQL JOIN syntax using JOOQ methods and actual sql String");
        var result =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .join(FILM_ACTOR)
                        .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                        .where(ACTOR.FIRST_NAME.like("A%"))
                        .fetch();

        Query query =
        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .join(FILM_ACTOR)
                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                .where(ACTOR.FIRST_NAME.like("A%"));

        analyzeQuery(query, result.toString());
    }

    public void typeSafetyAliasing() {
        title("Verify MySql Table alias syntax using Jooq Methods and actual sql String");

        ActorTable a = (ActorTable) ACTOR.as("a");
        FilmActorTable fa = (FilmActorTable) FILM_ACTOR.as("fa");

        var result =
                ctx.select(a.FIRST_NAME, a.LAST_NAME)
                        .from(a)
                        .join(fa)
                        .on(a.ACTOR_ID.eq(fa.ACTOR_ID))
                        .where(a.FIRST_NAME.like("A%"))
                        .fetch();

    }

}
