package com.example.database;


import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.meta.mysql.MySQLDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.jdbc.ContainerDatabaseDriver;
import org.testcontainers.utility.DockerImageName;


class DatabaseConnectionTest extends AbstractDatabaseConnection {
    static Connection connection;

    @Test
    void jdbcConnectionToMySqlDockerImageTest() {
        String mySQLUrl = "jdbc:mysql://localhost:3309/";
        String mySqldriver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "sesame";
        String dbName = "my_guitar_shop";

        connection = null;
        try {
            Class.forName(mySqldriver);

            connection = DriverManager.getConnection(mySQLUrl + dbName, user, password);
            DSLContext create = DSL.using(connection, SQLDialect.MYSQL);

            List<Schema> schemas = create.meta().getSchemas();
            //sakila 6, my_guitar_shop 1
            assertEquals(schemas.size(), 7);

            List<Table<?>> tablesInSakila = create.meta().getTables();
            //sakila 350, my_guitar_shop 7
            assertEquals(tablesInSakila.size(), 357);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void numberOfRecordsInTableTest(){
        Schema schema = ctx.meta().getSchemas("my_guitar_shop").get(0);
        assertNotNull(schema);
    }

//    protected MySQLContainer<?> mySqlTestContainer;
//    protected DockerImageName MYSQL_TEST_IMAGE = DockerImageName.parse("mysql:8.24");
//
//    void mySqlContainerTest() throws SQLException {
//
//        try (
//                MySQLContainer<?> mysqlCustomConfig = new MySQLContainer<>(MYSQL_TEST_IMAGE)
//                        .withConfigurationOverride("mysql_conf_override")
//        ) {
//            mysqlCustomConfig.start();
//
//
//        }
//    }
//
//    void postgresContainerTest() {
//        DockerImageName POSTGRES_TEST_IMAGE = DockerImageName.parse("postgres:9.6.12");
//        Properties properties = new Properties();
//
//        mySqlTestContainer = new MySQLContainer("postgres:latest")
//                .withUsername("postgres")
//                .withDatabaseName("postgres")
//                .withPassword("postgres");
//
//        ctx = DSL.using(connection, SQLDialect.POSTGRES);
//
//    }
}