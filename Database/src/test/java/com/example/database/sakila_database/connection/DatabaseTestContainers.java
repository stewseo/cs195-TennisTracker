package com.example.database.sakila_database.connection;

import com.example.database.TestDatabase;
import com.example.database.sakila_database.model.Table.Payments.Payment;
import org.jooq.*;
import org.jooq.tools.JooqLogger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.junit.BeforeClass;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.JooqLogger;
import org.testcontainers.jdbc.ContainerDatabaseDriver;

import com.example.database.TestDatabase;
import static com.example.database.sakila_database.model.Table.Actor.ACTOR;
import static com.example.database.sakila_database.model.Table.Category.CATEGORY;
import static com.example.database.sakila_database.model.Table.Customer.CUSTOMER;
import static com.example.database.sakila_database.model.Table.Film.FILM;
import static com.example.database.sakila_database.model.Table.FilmActor.FILM_ACTOR;
import static com.example.database.sakila_database.model.Table.FilmCategory.FILM_CATEGORY;
import static com.example.database.sakila_database.model.Table.Inventory.INVENTORY;
import static com.example.database.sakila_database.model.Table.Rental.RENTAL;
import static com.example.database.sakila_database.model.Tables.PAYMENT;
import static org.apache.commons.collections4.ListUtils.select;
import static org.jooq.JSONFormat.RecordFormat.OBJECT;
import static org.jooq.Records.mapping;
import static org.jooq.XMLFormat.RecordFormat.COLUMN_NAME_ELEMENTS;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.table;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseContainersTest {
    static JooqLogger log = JooqLogger.getLogger(DatabaseContainersTest.class);
    static Connection connection;
    static DSLContext ctx;

    @BeforeClass
    public static void init() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("username", "root");
        properties.setProperty("password", "sesame");
        log.info("Connecting");

        connection = new ContainerDatabaseDriver().connect(
                "jdbc:mysql://localhost:3306/sakila?allowMultiQueries=true",
                properties
        );

        ctx = DSL.using(connection, SQLDialect.MYSQL);ls
                


        try (Statement s = connection.createStatement()) {
            log.info("Setting up database");
            s.execute(Source.of(DatabaseContainersTest.class.getResourceAsStream("/sakila/mysql-sakila-schema.sql")).readString());

            log.info("Inserting data to database");
            s.execute(Source.of(DatabaseContainersTest.class.getResourceAsStream("/sakila/mysql-sakila-insert-data.sql")).readString());

            log.info("Finished setup");
        }
    }

    record Film(String title) {}
    record Actor(String firstName, String lastName) {}
    record Category(String name) {}

    @Test
    public void testMultisetMappingIntoJavaRecords() {

        Result<?> res = println(ctx
                .select(
                        FILM.TITLE,
                        multiset(
                                DSL.select(
                                                ACTOR.FIRST_NAME,
                                                ACTOR.LAST_NAME
                                        )
                                        .from(ACTOR).join(FILM_ACTOR)
                                        .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                        ).convertFrom(r -> r.map(mapping(Actor::new))),
                        multiset(
                                DSL.select(CATEGORY.NAME)
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

    }

    @Test
    public void testMultisetFormattingAsXMLorJSON() {

        String fileName = "output_txt/schema_info";
        String description = "Testing Connection and inserting data to Test Containers from Sakila database";
        // Get films by title, and their actors and categories as nested collections,
        // and all the customers that have rented the film, and their payments
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
                TestDatabase.LOG_TO_TXT.multiSetToTxt(
                        ctx.select(
                                        FILM.TITLE,
                                        multiset(
                                                DSL.select(
                                                                ACTOR.FIRST_NAME,
                                                                ACTOR.LAST_NAME
                                                        )
                                                        .from(FILM_ACTOR)
                                                        .innerJoin(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                        ).as("actors"),

                                        multiset(
                                                DSL.select(CATEGORY.NAME)
                                                        .from(FILM_CATEGORY)
                                                        .join(CATEGORY)
                                                        .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                        ).as("categories"),

                                        multiset(
                                                DSL.select(
                                                                CUSTOMER.FIRST_NAME,
                                                                CUSTOMER.LAST_NAME,
                                                                multisetAgg(
                                                                        Payment.PAYMENT.PAYMENT_DATE,
                                                                        Payment.PAYMENT.AMOUNT
                                                                ).as("payments"),
                                                                sum(Payment.PAYMENT.AMOUNT).as("total"))
                                                        .from(Payment.PAYMENT)
                                                        .join(CUSTOMER).on(Payment.PAYMENT.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                                                        .join(RENTAL).on(Payment.PAYMENT.RENTAL_ID.eq(RENTAL.RENTAL_ID))
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

        System.out.println(result.format(new TXTFormat()));
        System.out.println(result.formatXML(new XMLFormat().xmlns(false).format(true).header(false).recordFormat(COLUMN_NAME_ELEMENTS)));
        System.out.println(result.formatJSON(new JSONFormat().format(true).header(false).recordFormat(OBJECT)));
    }

    public static final <T> T println(T t) {
        if (t instanceof Object[])
            System.out.println(List.of(t));
        else
            System.out.println(t);

        return t;
    }
}