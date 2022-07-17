//package com.example.database;
//
//import org.jooq.DSLContext;
//import org.jooq.exception.DataAccessException;
//import org.jooq.meta.Database;
//import org.jooq.meta.mysql.MySQLDatabase;
//import org.jooq.tools.JooqLogger;
//import org.jooq.tools.jdbc.JDBCUtils;
//
//import java.io.File;
//import java.sql.Connection;
//import java.util.Objects;
//import java.util.logging.Logger;
//
//@SuppressWarnings("rawtypes")
//public class MySqlLiquibaseDatabase extends MySQLDatabase {
//
//    private static final JooqLogger log = JooqLogger.getLogger(MySqlLiquibaseDatabase.class);
//
//    private MySQLDatabase mySQLContainer;
//
//    private Connection connection;
//
//    private DSLContext ctx;
//
//    @Override
//    protected DSLContext create0() {
//        if (connection == null) {
//            String scripts = Objects.requireNonNull(getProperties().getProperty("scripts"));
//            String mySqlVersion = getProperties().getProperty("mySqlVersion", "5.7.11");
//
//            try {
//                mySQLContainer = new MySQLContainer(MySQLContainer.IMAGE + ":" + mySqlVersion);
//                mySQLContainer.start();
//
//                connection = mySQLContainer.createConnection("");
//
//                ctx = DSL.using(connection);
//
//                File scriptFile = new File(scripts);
//                Database database = DatabaseFactory
//                        .getInstance()
//                        .findCorrectDatabaseImplementation(new JdbcConnection(connection));
//                Liquibase liquibase = new Liquibase(
//                        scriptFile.getName(),
//                        new FileSystemResourceAccessor(scriptFile.getParent()),
//                        database);
//                liquibase.update(new Contexts(), new LabelExpression());
//
//            } catch (Exception e) {
//                log.error("Error while preparing schema for code generation", e);
//                throw new DataAccessException("Error while preparing schema for code generation", e);
//            }
//        }
//        return ctx;
//    }
//
//    @Override
//    public void close() {
//
//        JDBCUtils.safeClose(connection);
//        connection = null;
//        if (mySQLContainer != null) {
//            mySQLContainer.close();
//        }
//        ctx = null;
//        super.close();
//    }
//}