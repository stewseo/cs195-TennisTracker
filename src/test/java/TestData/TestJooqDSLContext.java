package TestData;

import com.example.cs195tennis.database.Database;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.impl.DSL.using;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestJooqDSLContext {

    static DSLContext create() throws SQLException {
        Connection conn = DriverManager.getConnection("");
        assertNotNull(conn);

        DSLContext ctx = using(conn, SQLDialect.SQLITE);
        assertNotNull(ctx);
        return ctx;
    }
}
