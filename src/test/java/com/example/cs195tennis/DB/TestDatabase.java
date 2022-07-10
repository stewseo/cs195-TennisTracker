package com.example.cs195tennis.DB;

import org.jooq.*;
import org.jooq.tools.JooqLogger;

import static com.example.cs195tennis.Util.Tools.print;
import static org.jooq.impl.DSL.resultQuery;

public abstract class TestDatabase {

    private static Parser parser;
    public static DSLContext ctx;
    private static Schema schema;
    protected static final JooqLogger log = JooqLogger.getLogger(com.example.cs195tennis.DB.VerifyDatabase.class);


}