package com.example.database.sakila_database.TestSchema.TestQuery.TestNestedRecords;

import com.example.database.TestDatabase;
import org.jooq.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import static com.example.database.sakila_database.model.Table.Actor.ACTOR;
import static com.example.database.sakila_database.model.Table.Category.CATEGORY;
import static com.example.database.sakila_database.model.Table.Film.FILM;
import static com.example.database.sakila_database.model.Table.FilmActor.FILM_ACTOR;
import static com.example.database.sakila_database.model.Table.FilmCategory.FILM_CATEGORY;
import static org.jooq.impl.DSL.*;

public class NestingToManyRelationshipsTest extends TestDatabase {
    static String description;

    @Test
    void nestingToManyRelationShips(String currentSchemaName) throws SQLException, FileNotFoundException {

        ResultQuery<Record4<String, String, String, String>> resultQuery = ctx.select(
                        FILM.TITLE,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME,
                        CATEGORY.NAME
                )
                .from(ACTOR)
                .join(FILM_ACTOR)
                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                .join(FILM)
                .on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                .join(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID))
                .join(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.eq(CATEGORY.CATEGORY_ID))
                .orderBy(1, 2, 3, 4);

        description = "Actors not grouped, cartesian product as result";

        queryInfoToTxt(resultQuery,
                description,
                "nested_records/nesting_to_many_relationships/film_title_actor_name_category_name",
                List.of("actor", "film_actor", "film","film_category", "category"),
                "sakila"
        );

        ResultQuery<Record3<String, Result<Record2<String, String>>, Result<Record1<String>>>> rq =
                ctx.
                        select(
                                FILM.TITLE,
                                multiset(
                                        select(
                                                ACTOR.FIRST_NAME,
                                                ACTOR.LAST_NAME)
                                                .from(ACTOR)
                                                .join(FILM_ACTOR)
                                                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ).as("actors"),       multiset(
                                        select(CATEGORY.NAME)
                                                .from(CATEGORY)
                                                .innerJoin(FILM_CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                ).as("categories")
                        )
                        .from(FILM);

        description = """
                MultiSet, Actor's grouped as Result
                 film_actor.actor_id = actor.actor_id,
                 film_actor.film_id = film.film_id,
                 film_category.film_id = film.film_id
                """;

        queryInfoToTxt(rq,
                description,
                "nested_records/nesting_to_many_relationships/multiset_actor_name__category_name",
                List.of("actor", "film_actor", "film","film_category", "category"),
                "sakila"
        );


        String q = """
                select(
                      FILM.TITLE,
                      multiset(
                        select(
                         ACTOR.FIRST_NAME,
                          ACTOR.actor().LAST_NAME)
                        .from(FILM_ACTOR)
                        .join actor as a
                          on film_actor.actor_id = a.actor_id
                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                      ).as("actors")
                """;

        Query multi = query("""
                select
                  film.title,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object(
                            'first_name', t.first_name,
                            'last_name', t.last_name
                          ) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select a.first_name, a.last_name
                      from film_actor
                        join actor as a
                          on film_actor.actor_id = a.actor_id
                      where film_actor.film_id = film.film_id
                    ) as t
                  ) as actors,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object('name', t.name) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select c.name
                      from film_category
                        join category as c
                          on film_category.category_id = c.category_id
                      where film_category.film_id = film.film_id
                    ) as t
                  ) as categories
                from film
                order by film.title
                """);

        description = "MySQL syntax for multiset when off";

        queryInfoToTxt(multi, //Query
                description,
                "nested_records/nesting_to_many_relationships/my_sql_syntax",  //file_name
                List.of("film_actor", "actor", "film","film_category", "category"),
                "sakila" //schema in use
        );


        Result<Record3<String, Result<Record2<String, String>>, Result<Record1<String>>>> r =
                ctx.select(
                                FILM.TITLE,
                                multiset(
                                        select(
                                                FILM_ACTOR.actor().FIRST_NAME,
                                                FILM_ACTOR.actor().LAST_NAME)
                                                .from(FILM_ACTOR)
                                                .innerJoin(ACTOR)
                                                .on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ),
                                multiset(
                                        select(CATEGORY.NAME)
                                                .from(FILM_CATEGORY)
                                                .join(CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                )
                        )
                        .from(FILM)
                        .orderBy(FILM.TITLE)
                        .limit(5)
                        .fetch();


        println(r.formatJSON(JSONFormat.DEFAULT_FOR_RESULTS.format(true).header(false)));

        println(r.formatXML(XMLFormat.DEFAULT_FOR_RESULTS.format(true).header(false)));


    }
}
