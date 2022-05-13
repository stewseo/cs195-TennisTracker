package com.example.cs195tennis.controller.TableColumnListeners;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.ExecuteType;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TableListener extends DefaultExecuteListener {

    public static final Map<ExecuteType, Integer> STATISTICS  = new ConcurrentHashMap<>();


    Configuration configuration = new DefaultConfiguration().set(connection).set(dialect);

    configuration.set(new DefaultExecuteListenerProvider(new TableListener()));


    DSLContext create = DSL.using(configuration);


    TableListener() {

    }

}


