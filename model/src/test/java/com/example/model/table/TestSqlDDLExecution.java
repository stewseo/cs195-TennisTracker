package com.example.model.table;



import com.example.model.AbstractTestModel;
import org.jooq.impl.SQLDataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;


public class TestSqlDDLExecution extends AbstractTestModel {

    @Test
    void createTableTest() {
        ctx.createTable("categories").column("col1", SQLDataType.BIGINT).execute();

    }

    @Test
    void dropTableTest() {
        ctx.dropTable("categories").execute();
        assertNull(ctx.meta().getTables("categories"));
    }
}
