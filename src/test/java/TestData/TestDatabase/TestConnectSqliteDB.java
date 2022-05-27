package TestData.TestDatabase;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.impl.DSL.using;

public class TestConnectSqliteDB {
    private DSLContext ctx;
    private Connection conn;

    public Connection testSqlLiteConnection() {
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:Database/wta-tournaments.sqlite");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testSqlLiteConnection();
    }
}
