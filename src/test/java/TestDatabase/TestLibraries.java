package TestDatabase;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.impl.DSL.using;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestLibraries {



    @Test
    public void testJooq() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DSLContext ctx = using(conn, SQLDialect.SQLITE);

        assertNotNull(ctx);

    }

    @Test
    public void testDriverVersion() throws Exception {
        int major = DriverManager.getDriver("jdbc:sqlite:").getMajorVersion();
        int minor = DriverManager.getDriver("jdbc:sqlite:").getMinorVersion();
    }

}

