//package com.example.database.junit.mysql;
//
//import com.example.database.MySQLTestImages;
//import org.junit.jupiter.api.Test;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.containers.output.Slf4jLogConsumer;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static java.lang.System.out;
//
//public class TestCas {
//
//    @Test
//    public void testaa(){
//
//            try (
//                    MySQLContainer<?> mysql = new MySQLContainer<>(MySQLTestImages.MYSQL_80_IMAGE)
//                            .withConfigurationOverride("somepath/mysql_conf_override")
//            ) {
//                mysql.start();
//
//
//            }
//
//        out.println("sf");
//    }
//}
