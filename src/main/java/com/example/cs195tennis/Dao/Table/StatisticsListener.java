package com.example.cs195tennis.Dao.Table;

import org.jooq.ExecuteContext;
import org.jooq.ExecuteType;
import org.jooq.impl.DefaultExecuteListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Extending DefaultExecuteListener, which provides empty implementations for all methods...
public class StatisticsListener extends DefaultExecuteListener {


    private static final long                     serialVersionUID = 7399239846062763212L;

    public static final Map<ExecuteType, Integer> STATISTICS       = new ConcurrentHashMap<>();

    @Override
    public void start(ExecuteContext ctx) {
        STATISTICS.compute(ctx.type(), (k, v) -> v == null ? 1 : v + 1);
    }
}

