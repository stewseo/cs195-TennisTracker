package com.example.database.sakila_database;

import com.example.database.TestDatabase;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.IOException;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockFileDatabase;
import org.jooq.tools.jdbc.MockResult;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.database.sakila_database.model.Tables.ACTOR;
import static org.jooq.impl.DSL.using;

public class TestDatabaseConnection extends TestDatabase {
    @Test
    public void mockingJDBCWithTheFileDatabase() throws IOException, java.io.IOException {

        MockFileDatabase p = new MockFileDatabase(
                """
                # All lines with a leading hash are ignored. This is the MockFileDatabase comment syntax
                select 'A';
                > A
                > -
                > A
                @ rows: 1
                            
                select 'A', 'B' union all select 'C', 'D';
                > A B
                > - -
                > A B
                > C D
                @ rows: 2
                            
                # Statements without result sets just leave that section empty
                update t set x = 1;
                @ rows: 3
                            
                # Statements producing specific exceptions can indicate them as such
                select * from t;
                @ exception: ACCESS TO TABLE T FORBIDDEN
                """
        );

        DSLContext c = using(new MockConnection(p), ctx.dialect());

        title("This statement is matched by the MockFileDatabase");
        println(c.fetch("select 'A'"));

        try {
            title("This statement cannot be matched by the MockFileDatabase, so it fails");
            c.fetch("select 'B'");
        }
        catch (DataAccessException e) {
            e.printStackTrace();
        }

        title("These statements can be matched again");
        println(c.fetch("select 'A', 'B' union all select 'C', 'D'"));
        println(c.execute("update t set x = 1"));

        try {
            title("This statement is matched, but throws an exception");
            c.fetch("select * from t");
        }
        catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mockingJDBCProgrammatically() throws SQLException {

        // More details here: https://www.jooq.org/doc/latest/manual/sql-execution/mocking-connection/
        // A MockDataProvider is a database simulation that can intercept all database calls at the JDBC level
        MockDataProvider p = c -> new MockResult[] {
                c.sql().contains("select")
                        ? new MockResult(ctx.newRecord(ACTOR).values(1L, "A", "A", null))
                        : new MockResult(0)
        };

        // It can be used with any JDBC based application. Just replace the actual connection by this MockConnection
        try (Connection c = new MockConnection(p)) {

            // These statements aren't actually executed. They all just run against the above MockDataProvider
            try (Statement s = c.createStatement();
                 ResultSet rs = s.executeQuery("select dummy from random_table")) {

                title("Using jOOQ only to fetch and pretty print the JDBC ResultSet");
                println(ctx.fetch(rs));
            }
        }

        title("Of course, jOOQ being a JDBC based API, you can equally mock jOOQ");
        println(using(new MockConnection(p), ctx.dialect())
                .selectFrom(ACTOR)
                .fetch()
        );
    }
}
