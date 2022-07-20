package com.example.database.postgres;

import com.example.database.AbstractTestDatabase;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.flywaydb.core.Flyway;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.tools.JooqLogger;
import org.jooq.tools.jdbc.SingleConnectionDataSource;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.impl.DSL.using;

public class TestTestcontainers {

    protected MySQLContainer<?> mySqlTestContainer;
    protected DockerImageName MYSQL_TEST_IMAGE = DockerImageName.parse("mysql:8.24");

    @Test
    void mySqlContainerTest() throws SQLException {

        try (
                MySQLContainer<?> mysqlCustomConfig = new MySQLContainer<>(MYSQL_TEST_IMAGE)
                        .withConfigurationOverride("mysql_conf_override")
        ) {
            mysqlCustomConfig.start();


        }
    }


    protected static PostgreSQLContainer<?> db;
    protected static DataSource jdbc;
    protected static ConnectionFactory r2dbc;
    protected static DSLContext ctx;
    protected static Configuration configuration;
    protected static final JooqLogger log = JooqLogger.getLogger(AbstractTestDatabase.class);

    @Test
    void postgresContainerTest() throws SQLException {
        DockerImageName POSTGRES_TEST_IMAGE = DockerImageName.parse("postgres:9.6.12");
        Properties properties = new Properties();
        log.info("Setting up testcontainers");

        db = new PostgreSQLContainer<>("postgres:latest")
                .withUsername("postgres")
                .withDatabaseName("postgres")
                .withPassword("postgres");

        db.start();

        log.info("Connecting");


        jdbc = new SingleConnectionDataSource(DriverManager.getConnection(
                db.getJdbcUrl(),
                db.getUsername(),
                db.getPassword()
        ));


        r2dbc = ConnectionFactories.get(ConnectionFactoryOptions
                .parse(db.getJdbcUrl().replace("jdbc:", "r2dbc:"))
                .mutate()
                .option(ConnectionFactoryOptions.USER, db.getUsername())
                .option(ConnectionFactoryOptions.PASSWORD, db.getPassword())
                .build()
        );

        ctx = using(configuration = new DefaultConfiguration()

                        .set(jdbc)
                        .set(r2dbc)
                        .set(POSTGRES)
                        .set(new Settings()
                                .withRenderFormatted(true)
                        )
        );

        // Initialise classes
        ctx.selectOne().toString();
        log.info("Finished setup");

        log.info("Flyway migration");

        Flyway.configure()
                .locations("resources/postgres/sakila")
                .dataSource(db.getJdbcUrl(), db.getUsername(), db.getPassword())
                .load()
                .migrate();

    }

    @AfterClass
    public static void afterClass() {
        db.close();
    }
}


