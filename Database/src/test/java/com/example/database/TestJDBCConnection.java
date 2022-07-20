package com.example.database;


import org.jooq.*;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TestJDBCConnection {
    static Connection connection;


    @Test
    void startMySqlTestContainersTest() {

        String mysqlImage = ":mysql:latest:";
        String dbName = "///mysql_testcontainers_db";
        String tc_iniscript = "?TC_INITSCRIPT=file:";
        String sqlInitScript = "resources/init_mysql.sql";

        StringBuilder testcontainers =
                new StringBuilder("jdbc:tc")
                        .append(mysqlImage)
                        .append(dbName)
                        .append(tc_iniscript)
                        .append(sqlInitScript);

        String validInitScript = "jdbc:tc:mysql:latest:///mysql_testcontainers_db?TC_INITSCRIPT=file:resources/init_mysql.sql";

        assertEquals(testcontainers.toString(), validInitScript);

        DockerImageName mySqlContainerImage = DockerImageName.parse("jdbc:tc:mysql:latest:///mysql_testcontainers_db?TC_INITSCRIPT=file:resources/init_mysql.sql").asCompatibleSubstituteFor("mysql");

        MySQLContainer<?> mysqlTestContainer =  new MySQLContainer<>(mySqlContainerImage)
                .withUsername("test")
                .withPassword("test")
                .withEnv("MYSQL_ROOT_PASSWORD", "sesame");

        assertNotNull(mysqlTestContainer);
        mysqlTestContainer.start();
    }

    @Test
    void jdbcConnectionToMySqlDockerImageTest () {

            String mySQLUrl = "jdbc:mysql://localhost:3309/";
            String mySqldriver = "com.mysql.cj.jdbc.Driver";
            String user = "root";
            String password = "sesame";
            String schemaName = "my_guitar_shop";

            connection = null;
            try {
                Class.forName(mySqldriver);

                connection = DriverManager.getConnection(mySQLUrl + schemaName, user, password);
                DSLContext create = DSL.using(connection, SQLDialect.MYSQL);

                List<Schema> schemas = create.meta().getSchemas();
                //sakila 6, my_guitar_shop 1
                assertEquals(schemas.size(), 7);

                List<Table<?>> tablesInSakila = create.meta().getTables();
                //sakila 350, my_guitar_shop 7
                assertEquals(tablesInSakila.size(), 358);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


}