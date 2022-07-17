//package com.example.database.db;
//
//import io.r2dbc.spi.*;
//import org.jooq.exception.IOException;
//import org.jooq.meta.Database;
//import org.junit.jupiter.api.*;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.containers.MySQLContainer;
//
//import java.io.Closeable;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public abstract class AbstractDatabaseTest<T extends GenericContainer<?>> {
//
//        protected static MySQLContainer<?> container;
//
//        @BeforeAll
//        public static void dbSetup() {
//            container = new MySQLContainer<>("MySql:8.024")
//                    .withDatabaseName("sakila")
//                    .withUsername("root")
//                    .withPassword("sesame");
//            container.start();
//        }
//
//        @AfterAll
//        public static void dbDown() {
//            container.close();
//        }
//
//        protected Database database;
//
//        @BeforeEach
//        public void setup() throws Exception {
//            database = getDatabase();
//        }
//
//        @AfterEach
//        void tearDown() throws Exception {
//            database.close();
//        }
//    public abstract Database getDatabase() throws IOException;
//
//
//}