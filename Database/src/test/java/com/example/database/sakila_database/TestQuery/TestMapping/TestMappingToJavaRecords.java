package com.example.database.sakila_database.TestQuery.TestMapping;

import com.example.database.TestDatabase;
import org.jooq.*;
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
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.sum;

public class TestMappingToJavaRecords extends TestDatabase {

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
    void standardisationLimitTest() {
    }

    @Test
    void testMultiSetMapingIntoJavaRecords() {
        record Actor(String firstname, String lastname){}
        record Category(String name){}

        record Film(
                List<Film> films,
                String title,
                List<Actor> actors
        ) {}
        record Title(String title) {}

        Field<Title> title = FILM.TITLE.convertFrom(Title::new);


        Result<?> res = println(ctx
                .select(
                        FILM.TITLE,
                        multiset(
                                select(
                                        ACTOR.FIRST_NAME,
                                        ACTOR.LAST_NAME
                                )
                                        .from(ACTOR).join(FILM_ACTOR)
                                        .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                        ).convertFrom(r -> r.map(mapping(Actor::new))),
                        multiset(
                                select(CATEGORY.NAME)
                                        .from(CATEGORY)
                                        .innerJoin(FILM_CATEGORY)
                                        .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                        ).convertFrom(r -> r.map(mapping(Category::new)))
                )
                .from(FILM)
                .where(FILM.TITLE.like("A%"))
                .orderBy(FILM.TITLE)
                .limit(5))
                .fetch();

        System.out.println(res);

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
                        >>                        // "customers"
                >> result =
                ctx.select(

                                // Get the films
                                FILM.TITLE,

                                // and all actors that played in the film
                                multiset(
                                        select(
                                                ACTOR.FIRST_NAME,
                                                ACTOR.LAST_NAME
                                        )
                                                .from(FILM_ACTOR)
                                                .innerJoin(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ).as("actors"),

                                // ... and all categories that categorise the film
                                multiset(
                                        select(CATEGORY.NAME)
                                                .from(FILM_CATEGORY)
                                                .join(CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                ).as("categories"),

                                // ... and all customers who rented the film, as well
                                // as their payments
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
//                                                .join(FILM).on(INVENTORY.FILM_ID.eq(FILM.FILM_ID))
                                                .where(INVENTORY.FILM_ID.eq(FILM.FILM_ID))
                                                .groupBy(
                                                        CUSTOMER.CUSTOMER_ID,
                                                        CUSTOMER.FIRST_NAME,
                                                        CUSTOMER.LAST_NAME)
                                ).as("customers")
                        )
                        .from(FILM)
                        .where(FILM.TITLE.like("A%"))
                        .orderBy(FILM.TITLE)
                        .limit('5')
                        .fetch();
    }


}
