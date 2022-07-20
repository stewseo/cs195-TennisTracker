package com.example.database.postgres;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.IOException;
import java.sql.SQLException;

import org.flywaydb.core.internal.database.base.Database;
import org.jooq.meta.Databases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

class PostgresUtilsTest {
    private static final String DATABASE_NAME = "testcontainers_postgres";
    private static final String TABLE_NAME = "test_table";
    private static final String INIT_SCHEMA =
            "jdbc:tc:postgresql:9.6.8:///databasename?TC_INITSCRIPT" +
                    "=postgres/sakila";

    private static PostgreSQLContainer<?> container;

    @BeforeAll
    public static void dbSetup() {
        container = new PostgreSQLContainer<>("postgres:13-alpine")
                .withDatabaseName(DATABASE_NAME)
                .withUsername("postgres")
                .withPassword("postgres")
         .withInitScript(INIT_SCHEMA);
        container.start();
    }



    @Test
    void connection() {
        out.println("test");
    }

}