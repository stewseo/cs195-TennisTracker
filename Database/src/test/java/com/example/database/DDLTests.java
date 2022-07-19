package com.example.database;


import org.jooq.impl.SQLDataType;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;

public class DDLTests extends AbstractDatabaseConnection {

    @Test
    void testCreate() {

        ctx.createTable("test_table").column("col1", SQLDataType.BIGINT).execute();
    }

    @Test
    void tableConstraintsTest() {
        ctx.meta().getSchemas().forEach(out::println);
    }
}
