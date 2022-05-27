package TestDatabase;

import org.jooq.DSLContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectSqliteDB {
    private DSLContext ctx;
    private Connection conn;

    public Connection testSqlLiteConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:Database/wta-tournaments.sqlite");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testSqlLiteConnection();
    }
}
