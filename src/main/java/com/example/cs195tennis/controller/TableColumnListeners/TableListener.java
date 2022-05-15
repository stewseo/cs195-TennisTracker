package com.example.cs195tennis.controller.TableColumnListeners;
import com.example.cs195tennis.database.Database;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.ExecuteType;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TableListener extends DefaultExecuteListener {

    public static final Map<ExecuteType, Integer> STATISTICS  = new ConcurrentHashMap<>();

//    Configuration configuration = new DefaultConfiguration()
//            .set(Database.connect()).set(SQLDialect.SQLITE);
//
//    DSLContext create = DSL.using(configuration);

    TableListener() {}

}


