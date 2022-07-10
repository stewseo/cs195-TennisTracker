package com.example.database.db_connection;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.io.ObjectInputFilter;
import java.sql.Connection;

public class Connect extends Database {
    public Connect(){}
    public DSLContext create(String schemaName){
        Connection connection = Database.connect(schemaName);
        Configuration configuration = Database
                .getConfigurationWithVisitListener(schemaName)
                .set(connection);

        return DSL.using(configuration);
    }
    public Connect(String dbName){

    }
}
