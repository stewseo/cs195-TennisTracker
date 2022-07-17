//package com.example.database.jdbc.mysql;
//
//import com.example.database.jdbc.AbstractJDBCDriverTest;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//import java.util.Arrays;
//import java.util.EnumSet;
//
//@RunWith(Parameterized.class)
//public class MySQLJDBCDriverTest extends AbstractJDBCDriverTest {
//
//    @Parameterized.Parameters(name = "{index} - {0}")
//    public static Iterable<Object[]> data() {
//        return Arrays.asList(
//            new Object[][]{
//                { "jdbc:tc:mysql://hostname/databasename", EnumSet.noneOf(Options.class) },
//                {
//                    "jdbc:tc:mysql://hostname/databasename?user=someuser&TC_INITSCRIPT=somepath/init_mysql.sql",
//                    EnumSet.of(Options.ScriptedSchema, Options.JDBCParams),
//                },
//                {
//                    "jdbc:tc:mysql:5.7.34://hostname/databasename?user=someuser&TC_INITFUNCTION=org.testcontainers.jdbc.AbstractJDBCDriverTest::sampleInitFunction",
//                    EnumSet.of(Options.ScriptedSchema, Options.JDBCParams),
//                },
//                {
//                    "jdbc:tc:mysql:5.7.34://hostname/databasename?user=someuser&password=somepwd&TC_INITSCRIPT=somepath/init_mysql.sql",
//                    EnumSet.of(Options.ScriptedSchema, Options.JDBCParams),
//                },
//                {
//                    "jdbc:tc:mysql:5.7.34://hostname/databasename?user=someuser&password=somepwd&TC_INITSCRIPT=file:sql/init_mysql.sql",
//                    EnumSet.of(Options.ScriptedSchema, Options.JDBCParams),
//                },
//                {
//                    "jdbc:tc:mysql:5.7.34://hostname/databasename?user=someuser&password=somepwd&TC_INITFUNCTION=org.testcontainers.jdbc.AbstractJDBCDriverTest::sampleInitFunction",
//                    EnumSet.of(Options.ScriptedSchema, Options.JDBCParams),
//                },
//                {
//                    "jdbc:tc:mysql:5.7.34://hostname/databasename?TC_INITSCRIPT=somepath/init_unicode_mysql.sql&useUnicode=yes&characterEncoding=utf8",
//                    EnumSet.of(Options.CharacterSet),
//                },
//                { "jdbc:tc:mysql:5.7.34://hostname/databasename", EnumSet.noneOf(Options.class) },
//                { "jdbc:tc:mysql:5.7.34://hostname/databasename?useSSL=false", EnumSet.noneOf(Options.class) },
//                {
//                    "jdbc:tc:mysql:5.6.51://hostname/databasename?TC_MY_CNF=somepath/mysql_conf_override",
//                    EnumSet.of(Options.CustomIniFile),
//                },
//            }
//        );
//    }
//}
