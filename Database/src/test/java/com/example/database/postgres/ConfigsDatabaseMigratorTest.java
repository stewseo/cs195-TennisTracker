package com.example.database.postgres;


import java.io.IOException;

import org.flywaydb.core.internal.database.base.Database;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class ConfigsDatabaseMigratorTest  {

    protected static PostgreSQLContainer<?> container;

    @BeforeAll
    public static void dbSetup() {
        container = new PostgreSQLContainer<>("postgres:13-alpine")
                .withDatabaseName("airbyte")
                .withUsername("docker")
                .withPassword("docker");
        container.start();
    }

    @AfterAll
    public static void dbDown() {
        container.close();
    }

    protected Database<?> database;

    @BeforeEach
    public void setup() throws Exception {
        database = getDatabase();
    }

    @AfterEach
    void tearDown() throws Exception {
        database.close();
    }

    public abstract Database getDatabase() throws IOException;
}