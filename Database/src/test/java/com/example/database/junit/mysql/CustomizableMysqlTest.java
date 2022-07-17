//package com.example.database.junit.mysql;
//
//import com.example.database.MySQLTestImages;
//import com.example.database.db.AbstractContainerDatabaseTest;
//import org.junit.Test;
//import com.example.database.MySQLTestImages;
//import org.testcontainers.containers.MySQLContainer;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.testcontainers.containers.JdbcDatabaseContainer;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class CustomizableMysqlTest extends AbstractContainerDatabaseTest {
//
//    private static final String DB_NAME = "sakila";
//
//    private static final String USER = "root";
//
//    private static final String PWD = "sesame";
//
//    @Test
//    public void testSimple() throws SQLException {
//        // Add MYSQL_ROOT_HOST environment so that we can root login from anywhere for testing purposes
//        try (
//            MySQLContainer<?> mysql = new MySQLContainer<>(MySQLTestImages.MYSQL_80_IMAGE)
//                .withDatabaseName(DB_NAME)
//                .withUsername(USER)
//                .withPassword(PWD)
//                .withEnv("MYSQL_ROOT_HOST", "%")
//        ) {
//            mysql.start();
//
//            ResultSet resultSet = performQuery(mysql, "SELECT 1");
//
//            int resultSetInt = resultSet.getInt(1);
//            assertEquals("A basic SELECT query succeeds", 1, resultSetInt);
//        }
////    }
//}
