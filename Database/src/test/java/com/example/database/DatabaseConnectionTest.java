package com.example.database;

import com.example.database.util.AbstractDatabaseConnection;
import org.jooq.impl.SQLDataType;
import org.jooq.meta.mysql.MySQLDatabase;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.testcontainers.containers.MySQLContainer;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest extends AbstractDatabaseConnection {

    @Test
    void jdbcConnectionToMySqlDockerImageTest() {
        String mySQLUrl = "jdbc:mysql://localhost:3309/";
        String mySqldriver = "com.mysql.cj.jdbc.Driver";
        Connection connection;
        String user = "root";
        String password = "sesame";
        String dbName = "docker_db";

        connection = null;
        try {
            Class.forName(mySqldriver);

            connection = DriverManager.getConnection(mySQLUrl+dbName,user,password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreate() {
        ctx.meta();
        ctx.createTable("test_table").column("col1", SQLDataType.BIGINT).execute();
        ctx.alterTable("test_table").drop("test_table").execute();
    }

    @Test
    void testContainers(){
        MySQLContainer<?> db;
        log.info("Setting up testcontainers");
        db = new MySQLContainer("mysql:latest")
                .withUsername("root")
                .withDatabaseName("mysql-docker")
                .withPassword("sesame");

        db.start();

        log.info("Connecting");
    }
}