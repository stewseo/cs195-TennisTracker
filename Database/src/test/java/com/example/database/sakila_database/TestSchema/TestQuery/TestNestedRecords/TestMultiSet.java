package com.example.database.sakila_database.TestSchema.TestQuery.TestNestedRecords;

import com.example.database.TestDatabase;
import org.jetbrains.annotations.NotNull;
import org.jooq.*;
import org.jooq.Record;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.database.sakila_database.model.Table.Actor.ACTOR;
import static com.example.database.sakila_database.model.Table.Category.CATEGORY;
import static com.example.database.sakila_database.model.Table.Customer.CUSTOMER;
import static com.example.database.sakila_database.model.Table.Film.FILM;
import static com.example.database.sakila_database.model.Table.FilmActor.FILM_ACTOR;
import static com.example.database.sakila_database.model.Table.FilmCategory.FILM_CATEGORY;
import static com.example.database.sakila_database.model.Table.Inventory.INVENTORY;
import static com.example.database.sakila_database.model.Table.Payments.Payment.PAYMENT;
import static com.example.database.sakila_database.model.Table.Rental.RENTAL;
import static java.lang.System.out;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.sum;

class TestMultiSet extends TestDatabase {

    static String description;
    static String fileName;
    static List<Table<?>> listOfTables;

    @Test
    void multisetAsActorsTest() {

        var result =
                query("""
                        select
                          film.title,
                          (
                            select coalesce(
                              json_merge_preserve(
                                '[]',
                                concat(
                                  '[',
                                  group_concat(json_array(`v0`, `v1`) separator ','),
                                  ']'
                                )
                              ),
                              json_array()
                            )
                            from (
                              select
                                actor.first_name as `v0`,
                                actor.last_name as `v1`
                              from actor
                                join film_actor
                                  on actor.actor_id = film_actor.actor_id
                              where film_actor.film_id = film.film_id
                            ) as `t`
                          ) as `actors`
                        from film
                        where film.title like 'a%'
                        """);


        fileName = "test_multiset/all_actors_in_film_test";
        description = "SelectConditionStep<Record2<String, Result<Record2<String, String>>>>\n" +
                "column 1: Multiset actor.first_name, actor.last_name";

        var res = //  SelectConditionStep<Record2<String, Result<Record2<String, String>>>>
                ctx.fetch(
                        multiSetToTxt(
                                ctx.select(FILM.TITLE,
                                        multiset(
                                                select(
                                                        ACTOR.FIRST_NAME,
                                                        ACTOR.LAST_NAME)
                                                        .from(FILM_ACTOR)
                                                        .innerJoin(ACTOR)
                                                        .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                        ).as("actors"))
                                        .from(FILM)
                                        .where(FILM.TITLE.like("A%")),
                                fileName,
                                description,
                                List.of(table("actor"), table("film_actor"))
                        )
                );

        Field<Result<Record2<String, String>>> field =multiset(
                select(
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME)
                        .from(FILM_ACTOR)
                        .innerJoin(ACTOR)
                        .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
        );

        println(ctx.select(cardinality(array(field))).fetch());
    }

    @Test
    void multisetAsCustomersTest() {
        fileName = "test_multiset/multiset_as_customers_test";
        description = "Result<Record2<String, Result<Record4<String, String, Result<Record2<LocalDateTime, BigDecimal>>, BigDecimal>>>> result\n" +
                "column 3 by film title: customer first, last name, payment ";

        Query query = query("""
                select
                  `sakila`.`film`.`title`,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_array(`v0`) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select `category`.`name` as `v0`
                      from `film_category`
                        join `category`
                          on `category`.`category_id` = `film_category`.`category_id`
                      where `film_category`.`film_id` = `sakila`.`film`.`film_id`
                    ) as `t`
                  ) as `categories`
                """);

        ctx.fetch(query.getSQL());
        var res = //  Result<Record2<String, Result<Record4<String, String, Result<Record2<LocalDateTime, BigDecimal>>, BigDecimal>>>>res
                ctx.fetch(
                        multiSetToTxt(
                                ctx.select(FILM.TITLE,
                                        multiset(
                                                select(
                                                        CUSTOMER.FIRST_NAME,
                                                        CUSTOMER.LAST_NAME,
                                                        multisetAgg(
                                                                PAYMENT.PAYMENT_DATE,
                                                                PAYMENT.AMOUNT
                                                        ).as("payments"),
                                                        sum(PAYMENT.AMOUNT).as("total"))
                                                        .from(PAYMENT)
                                                        .join(CUSTOMER).on(PAYMENT.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                                                        .join(RENTAL).on(PAYMENT.RENTAL_ID.eq(RENTAL.RENTAL_ID))
                                                        .join(INVENTORY).on(RENTAL.INVENTORY_ID.eq(INVENTORY.INVENTORY_ID))
                                                        .where(INVENTORY.FILM_ID.eq(FILM.FILM_ID))
                                                        .groupBy(
                                                                CUSTOMER.CUSTOMER_ID,
                                                                CUSTOMER.FIRST_NAME,
                                                                CUSTOMER.LAST_NAME)
                                        ).as("customers"))
                                        .from(FILM)
                                        .where(FILM.TITLE.like("A%")),
                                fileName,
                                description,
                                List.of(table("actor"), table("film_actor"))
                        )
                );
    }

    @Test
    void multisetAsCategoriesTest() {
        fileName = "test_multiset/multiset_as_categories_test";
        description = "// Result<Record2<String, Result<Record1<String>>>>\n" +
                "column 2: categories by film title";

        var res = // Result<Record2<String, Result<Record1<String>>>>
                ctx.fetch(
                        multiSetToTxt(
                                ctx.select(FILM.TITLE,
                                        multiset(
                                                select(CATEGORY.NAME)
                                                        .from(FILM_CATEGORY)
                                                        .join(CATEGORY)
                                                        .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                        )
                                                .as("categories"))
                                        .from(FILM)
                                        .where(FILM.TITLE.like("A%"))
                                        .orderBy(FILM.TITLE)
                                        .limit(5),
                                fileName,
                                description,
                                List.of(table("category"), table("film_category"))
                        )
                );
    }

    @Test
    void multisetAggAggregateFunctionTest() {
        fileName = "test_multiset/multiset_agg_aggregate_function_test";
        description = "return all films, " +
                "the actors that played in the film, " +
                "the categories that categorise the film, " +
                "the customers that have rented the film, " +
                "and all the payments per customer for that film";

        Result<Record4<
                String,                   // FILM.TITLE
                Result<Record2<
                        String,               // ACTOR.FIRST_NAME
                        String                // ACTOR.LAST_NAME
                        >>,                       // "actors"
                Result<Record1<String>>,  // CATEGORY.NAME
                Result<Record4<
                        String,               // CUSTOMER.FIRST_NAME
                        String,               // CUSTOMER.LAST_NAME
                        Result<Record2<
                                LocalDateTime,    // PAYMENT.PAYMENT_DATE
                                BigDecimal        // PAYMENT.AMOUNT
                                >>,
                        BigDecimal            // "total"
                        >>
                >> result =
                multiSetToTxt(
                ctx.select(
                                FILM.TITLE,
                                multiset(
                                        select(
                                                ACTOR.FIRST_NAME,
                                                ACTOR.LAST_NAME
                                        )
                                                .from(FILM_ACTOR)
                                                .innerJoin(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ).as("actors"),

                                //all categories that categorise the film
                                multiset(
                                        select(CATEGORY.NAME)
                                                .from(FILM_CATEGORY)
                                                .join(CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                ).as("categories"),

                                // all customers who rented the film, as well as their payments
                                multiset(
                                        select(
                                                CUSTOMER.FIRST_NAME,
                                                CUSTOMER.LAST_NAME,
                                                multisetAgg(
                                                        PAYMENT.PAYMENT_DATE,
                                                        PAYMENT.AMOUNT
                                                ).as("payments"),
                                                sum(PAYMENT.AMOUNT).as("total"))
                                                .from(PAYMENT)
                                                .join(CUSTOMER).on(PAYMENT.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                                                .join(RENTAL).on(PAYMENT.RENTAL_ID.eq(RENTAL.RENTAL_ID))
                                                .join(INVENTORY).on(RENTAL.INVENTORY_ID.eq(INVENTORY.INVENTORY_ID))
                                                .where(INVENTORY.FILM_ID.eq(FILM.FILM_ID))
                                                .groupBy(
                                                        CUSTOMER.CUSTOMER_ID,
                                                        CUSTOMER.FIRST_NAME,
                                                        CUSTOMER.LAST_NAME)
                                ).as("customers")
                        )
                        .from(FILM)
                        .where(FILM.TITLE.like("B%"))
                        .orderBy(FILM.FILM_ID)
                        .limit(10),
                        fileName,
                        description,
                        List.of(table("actor"), table("film_actor"), table("customer")
                        )
                )
                        .fetch();
    }

    @Test
    void deeplyNestedMultiSets() {

        fileName = "test_multiset/deeply_nested_multisets";
        description = """
                Record2<Integer, Result<Record2<Integer,\s
                                Result<Record2<Integer,\s
                                        Result<Record2<Integer,\s
                                                Result<Record1<Integer>>
                                                >>
                                        >>
                                >>> result""";

        Record2<Integer,
                Result<Record2<Integer,
                        Result<Record2<Integer,
                                Result<Record2<Integer,
                                        Result<Record1<Integer>>
                                >>
                        >>
                >>> result =
        multiSetToTxt(
                ctx.select(
                        val(1).as("a"),
                        multiset(select(
                                val(2).as("b"),
                                multiset(select(
                                        val(3).as("c"),
                                        multiset(select(
                                                val(4).as("d"),
                                                multiset(select(val(5).as("e")))
                                        ))
                                ))
                        ))
                ),
                fileName,
                description,
                listOfTables
        ).fetchOne();

        var res = ctx.fetch("""
                select
                  ? as `a`,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_array(
                            `v0`,
                            json_extract(`v1`, '$')
                          ) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select
                        ? as `v0`,
                        (
                          select coalesce(
                            json_merge_preserve(
                              '[]',
                              concat(
                                '[',
                                group_concat(json_array(
                                  `v0`,
                                  json_extract(`v1`, '$')
                                ) separator ','),
                                ']'
                              )
                            ),
                            json_array()
                          )
                          from (
                            select
                              ? as `v0`,
                              (
                                select coalesce(
                                  json_merge_preserve(
                                    '[]',
                                    concat(
                                      '[',
                                      group_concat(json_array(
                                        `v0`,
                                        json_extract(`v1`, '$')
                                      ) separator ','),
                                      ']'
                                    )
                                  ),
                                  json_array()
                                )
                                from (
                                  select
                                    ? as `v0`,
                                    (
                                      select coalesce(
                                        json_merge_preserve(
                                          '[]',
                                          concat(
                                            '[',
                                            group_concat(json_array(`v0`) separator ','),
                                            ']'
                                          )
                                        ),
                                        json_array()
                                      )
                                      from (
                                        select ? as `v0`
                                      ) as `t`
                                    ) as `v1`
                                ) as `t`
                              ) as `v1`
                          ) as `t`
                        ) as `v1`
                    ) as `t`
                  );
                """);
    }

    @Test
    void multisetBenchmark(){
        record MCCName (String firstName, String lastName) {}
        record MCCActor (long id, MCCName name) {}
        record MCCCategory (String name) {}
        record MCCFilm (
                long id,
                String title,
                List<MCCActor> actors,
                List<MCCCategory> categories
        ) {}


        ctx
                .select(
                        FILM.FILM_ID,
                        FILM.TITLE,

                        // Get all ACTORs for each FILM
                        multiset(
                                select(
                                        FILM_ACTOR.ACTOR_ID,
                                        row(
                                                FILM_ACTOR.actor().FIRST_NAME,
                                                FILM_ACTOR.actor().LAST_NAME
                                        ).mapping(MCCName::new)
                                )
                                        .from(FILM_ACTOR)
                                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                        ).convertFrom(r -> r.map(mapping(MCCActor::new))),

                        // Get all CATEGORY-s for each FILM
                        multiset(
                                select(FILM_CATEGORY.category().NAME)
                                        .from(FILM_CATEGORY)
                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                        ).convertFrom(r -> r.map(mapping(MCCCategory::new))))
                .from(FILM)
                .where(FILM.FILM_ID.eq(1L))
                .fetch(mapping(MCCFilm::new));
    }

}