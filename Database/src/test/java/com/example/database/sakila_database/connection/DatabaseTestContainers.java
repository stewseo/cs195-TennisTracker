package com.example.database.sakila_database.connection;

import com.example.database.TestDatabase;
import com.example.database.sakila_database.model.Table.Payments.Payment;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.tools.JooqLogger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.JooqLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.jdbc.ContainerDatabaseDriver;

import com.example.database.TestDatabase;

import javax.sql.DataSource;

import static com.example.database.sakila_database.model.Table.Actor.ACTOR;
import static com.example.database.sakila_database.model.Table.Category.CATEGORY;
import static com.example.database.sakila_database.model.Table.Customer.CUSTOMER;
import static com.example.database.sakila_database.model.Table.Film.FILM;
import static com.example.database.sakila_database.model.Table.FilmActor.FILM_ACTOR;
import static com.example.database.sakila_database.model.Table.FilmCategory.FILM_CATEGORY;
import static com.example.database.sakila_database.model.Table.Inventory.INVENTORY;
import static com.example.database.sakila_database.model.Table.Rental.RENTAL;
import static com.example.database.sakila_database.model.Tables.PAYMENT;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.apache.commons.collections4.ListUtils.select;
import static org.jooq.JSONFormat.RecordFormat.OBJECT;
import static org.jooq.Records.mapping;
import static org.jooq.XMLFormat.RecordFormat.COLUMN_NAME_ELEMENTS;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.table;
import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = DatabaseContainersTest.class
        , properties = "spring.profiles.active=enabled"
)
@SuppressWarnings("ALL")
class DatabaseContainersTest {
//    static JooqLogger log = JooqLogger.getLogger(DatabaseContainersTest.class);
    static Connection connection;
    static DSLContext ctx;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseContainersTest.class);

    @Autowired
    ConfigurableListableBeanFactory beanFactory;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldConnectToMySQL() throws Exception {
        assertThat(jdbcTemplate.queryForObject("select version()", String.class)).startsWith("8.0.");
    }

    @Test
    public void shouldSetupDependsOnForAllDataSources() throws Exception {
        String[] beanNamesForType = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, DataSource.class);
        assertThat(beanNamesForType)
                .as("Auto-configured datasource should be present")
                .hasSize(1)
                .contains("dataSource");
        asList(beanNamesForType).forEach(this::hasDependsOn);
    }

    private void hasDependsOn(String beanName) {
        assertThat(beanFactory.getBeanDefinition(beanName).getDependsOn())
                .isNotNull()
                .isNotEmpty()
                .contains("BEAN_NAME_EMBEDDED_MYSQL");
    }

    @Test
    public void TestConn() {
        Properties properties = new Properties();
        properties.setProperty("username", "root");
        properties.setProperty("password", "sesame");
        logger.info("Connecting");
        ctx = null;


        try (
                MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.29")
                        .withConfigurationOverride("")
                        .withLogConsumer(new Slf4jLogConsumer(logger))
        ) {
            mysql.start();
            logger.info("Connecting");
            System.setProperty("TEST_DB_URL", mysql.getJdbcUrl());
            System.setProperty("TEST_DB_USERNAME", mysql.getUsername());
            System.setProperty("TEST_DB_PASSWORD", mysql.getPassword());
            logger.debug("");

            logger.info("started MySql container TEST_DB_URL [{}] TEST_DB_USERNAME [{}] TEST_DB_PASSWORD [{}]");

            connection = new ContainerDatabaseDriver().connect(
                    "jdbc:mysql://localhost:3306///sakila?TC_TMPFS=/testtmpfs:rw",
                    properties
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("username", "root");
        properties.setProperty("password", "sesame");
        logger.info("Connecting");
        ctx = null;


        try (
                MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
                        .withConfigurationOverride("")
                        .withLogConsumer(new Slf4jLogConsumer(logger))
        ) {
            mysql.start();
            logger.info("Connecting");
            System.setProperty("TEST_DB_URL", mysql.getJdbcUrl());
            System.setProperty("TEST_DB_USERNAME", mysql.getUsername());
            System.setProperty("TEST_DB_PASSWORD", mysql.getPassword());

            logger.info("started MySql container TEST_DB_URL [{}] TEST_DB_USERNAME [{}] TEST_DB_PASSWORD [{}]");


            connection = new ContainerDatabaseDriver().connect(
                    "jdbc:mysql://localhost:3306///sakila?TC_TMPFS=/testtmpfs:rw",
                    properties
            );

            ctx = DSL.using(connection, SQLDialect.MYSQL);

           ctx.meta().getSchemas().stream().collect(Collectors.toList()).forEach(out::print);
            try (Statement s = connection.createStatement()) {
//                log.info("Setting up database");
                s.execute(Source.of(DatabaseContainersTest.class.getResourceAsStream("/sakila/mysql-sakila-schema.sql")).readString());
//
//                log.info("Inserting data to database");
                s.execute(Source.of(DatabaseContainersTest.class.getResourceAsStream("/sakila/mysql-sakila-insert-data.sql")).readString());

                logger.info("Finished setup");
            }
        }
    }

    record Film(String title) {}
    record Actor(String firstName, String lastName) {}
    record Category(String name) {}

    @Test
    public void testMultisetMappingIntoJavaRecords() {
        logger.info("Finished setup");

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

        out.println(result.format(new TXTFormat()));
        out.println(result.formatXML(new XMLFormat().xmlns(false).format(true).header(false).recordFormat(COLUMN_NAME_ELEMENTS)));
        out.println(result.formatJSON(new JSONFormat().format(true).header(false).recordFormat(OBJECT)));
    }

    public static final <T> T println(T t) {
        if (t instanceof Object[])
            out.println(List.of(t));
        else
            out.println(t);

        return t;
    }
}