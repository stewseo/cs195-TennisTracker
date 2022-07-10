package com.example.database;

import org.jooq.DSLContext;
import org.jooq.Parser;
import org.jooq.Schema;
import org.jooq.tools.JooqLogger;

public abstract class TestDatabase {

    private static Parser parser;
    public static DSLContext ctx;
    private static Schema schema;
    protected static final JooqLogger log = JooqLogger.getLogger(TestDatabase.class);


}