package TestData;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.impl.DSL.using;
import static org.junit.jupiter.api.Assertions.*;

class TestJdbcDriver {

    @Test
    public void testConnectionToSQLite() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:Database/wta-tournaments.sqlite");

        assertTrue(conn.isValid(0));

        conn.close();

        assertFalse(conn.isValid(0));
    }

}