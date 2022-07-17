package com.example.database;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;


public class TestQuery {


    @Test
    public void queryTest() {

        String userName = "root";
        String password = "sesame";
        String url = "jdbc:mysql://localhost:3306/sakila";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<org.jooq.Record> result = create.select().from(table("customers")).fetch();

            for (Record r : result) {
                var id = (Integer) r.getValue(field("customer.customer_id"));
                var firstName = (String) r.getValue(field("customer.FIRST_NAME"));
                var lastName = (String) r.getValue(field("customer.LAST_NAME"));

                System.out.println("ID: " + id.toString() + " first name: " + firstName.toString() + " last name: " + lastName.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

