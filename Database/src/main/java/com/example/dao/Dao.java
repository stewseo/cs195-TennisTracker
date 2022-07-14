package com.example.dao;

import com.example.dao.pojos.Actor;
import com.example.database.connection.Database;
import com.example.database.sakila_database.DefaultCatalog;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DSL.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.stream.Stream;

public class Dao  {
    public static DSLContext ctx;


    public void connectToAndQueryDatabase(
            String username, String password) {

        Connection connection = Database.connect("sakila");
        ctx = DSL.using(connection,SQLDialect.MYSQL);

//        try (Stream<Record> stream : ctx.fetchStream("SELECT a, b, c FROM Table1")) {
//
//            stream.forEach(record -> {
//                int x = record.get("a", int.class);
//                String s = record.get("b", String.class);
//                float f = record.get("c", float.class);
//            });
//        }
    }

    public void daos() {
        ActorDao dao = new ActorDao(ctx.configuration());
        dao.insert(
                new Actor(201L, "John", "Doe", null),
                new Actor(202L, "Jane", "Smith", null)
        );
        dao.fetchByActorId(201L, 202L).forEach(System.out::println);
    }

}
