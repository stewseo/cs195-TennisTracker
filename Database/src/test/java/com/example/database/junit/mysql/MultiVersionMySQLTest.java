//package com.example.database.junit.mysql;
//
//import com.example.database.MySQLTestImages;
//import com.example.database.db.AbstractContainerDatabaseTest;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.utility.DockerImageName;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@RunWith(Parameterized.class)
//public class MultiVersionMySQLTest extends AbstractContainerDatabaseTest {
//
//    @Parameterized.Parameters(name = "{0}")
//    public static DockerImageName[] params() {
//        return new DockerImageName[] {
//            MySQLTestImages.MYSQL_56_IMAGE,
//            MySQLTestImages.MYSQL_57_IMAGE,
//            MySQLTestImages.MYSQL_80_IMAGE,
//        };
//    }
//
//    @Parameterized.Parameter
//    public DockerImageName dockerImageName;
//
//    @Test
//    public void versionCheckTest() throws SQLException {
//        try (MySQLContainer<?> mysql = new MySQLContainer<>(dockerImageName)) {
//            mysql.start();
//            final ResultSet resultSet = performQuery(mysql, "SELECT VERSION()");
//            final String resultSetString = resultSet.getString(1);
//
//            assertEquals(
//                "The database version can be set using a container rule parameter",
//                dockerImageName.getVersionPart(),
//                resultSetString
//            );
//        }
//    }
//}
