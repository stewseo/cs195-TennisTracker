//package com.example.database.jdbc;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.junit.AfterClass;
//import org.junit.jupiter.api.Test;
//import org.junit.runners.Parameterized;
//import org.testcontainers.jdbc.ContainerDatabaseDriver;
//import org.testcontainers.shaded.com.trilead.ssh2.Connection;
//import org.testcontainers.shaded.org.apache.commons.lang3.SystemUtils;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.EnumSet;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assume.assumeFalse;
//
//public class AbstractJDBCDriverTest {
//
//    protected enum Options {
//        ScriptedSchema,
//        CharacterSet,
//        CustomIniFile,
//        JDBCParams,
//        PmdKnownBroken,
//    }
//
//    @Parameterized.Parameter
//    public String jdbcUrl;
//
//    @Parameterized.Parameter(1)
//    public EnumSet<Options> options;
//
//
//
//    @AfterClass
//    public static void testCleanup() {
//        ContainerDatabaseDriver.killContainers();
//    }
//
//
//    private void performTestForCustomIniFile(HikariDataSource dataSource) throws SQLException {
//        assumeFalse(SystemUtils.IS_OS_WINDOWS);
//
//        Statement statement = dataSource.getConnection().createStatement();
//        statement.execute("SELECT @@GLOBAL.innodb_file_format");
//        ResultSet resultSet = statement.getResultSet();
//
//        assertTrue("The query returns a result", resultSet.next());
//        String result = resultSet.getString(1);
//
//        assertEquals("The InnoDB file format has been set by the ini file content", "Barracuda", result);
//    }
//
//    private HikariDataSource getDataSource(String jdbcUrl, int poolSize) {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(jdbcUrl);
//        hikariConfig.setConnectionTestQuery("SELECT 1");
//        hikariConfig.setMinimumIdle(1);
//        hikariConfig.setMaximumPoolSize(poolSize);
//
//        return new HikariDataSource(hikariConfig);
//    }
//}