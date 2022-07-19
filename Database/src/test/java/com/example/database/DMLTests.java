package com.example.database;

import org.jooq.Query;
import org.jooq.Result;
import org.jooq.Table;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.List;

import static com.example.database.AbstractDatabaseConnection.ctx;
import static org.jooq.impl.DSL.query;
import static org.jooq.impl.DSL.table;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DMLTests extends AbstractDatabaseConnection {
    static String description, fileName;
    static Query query;

    @Test
    void insertTest() throws IOException {

        fileName = "m6_dml_statements_1";

        description = """
                Write an INSERT statement that adds this row to the Categories table:
                category_name: Brass

                Code the INSERT statement so MySQL automatically generates the category_id column.""";

        query = query("""
                insert into categories\s
                (category_id, category_name)
                values\s
                (default, 'Brass');
                """);

        var result =
                writeSqlStatementToTxt(

                        query,
                        fileName,
                        description,
                        List.of(table("categories"))
                );
        Result<org.jooq.Record> res  = ctx.select().from(table("products")).fetch();
        println(res);
    }

    @Test
    void updateTest() throws IOException {

        fileName = "m6_dml_statements_2";

        description = """
                2. Write an UPDATE statement that modifies the row you just added to the Categories table. 
                This statement should change the product_name column to Woodwinds, 
                and it should use the category_id column to identify the row.
                """;

        query = query("""
                UPDATE categories
                SET
                    category_name = 'Woodwinds'
                WHERE
                    category_id = (select category_id from categories where category_name='Brass');
                """);


        var result =
                writeSqlStatementToTxt(

                        query,
                        fileName,
                        description,
                        List.of(table("categories"))
                );
        Result<org.jooq.Record> res  = ctx.select().from(table("products")).fetch();
        println(res);
    }

    @Test
    void updateTest2() throws IOException {

        fileName = "m6_dml_statements_3";

        description = """
                Write a DELETE statement that deletes the row you added to the Categories table in exercise 1. 
                This statement should use the category_id column to identify the row.
                """;

        query = query("""
                UPDATE categories
                SET
                    category_name = 'Woodwinds'
                WHERE
                    category_id = (select category_id from categories where category_name='Brass');
                """);


        var result =
                writeSqlStatementToTxt(

                        query,
                        fileName,
                        description,
                        List.of(table("categories"))
                );
        Result<org.jooq.Record> res  = ctx.select().from(table("products")).fetch();
        println(res);
    }
    @Test
    void insertTest3() throws IOException {
        fileName = "m6_dml_statements_4";
        description = """
                Write an INSERT statement that adds this row to the Products table:

                product_id:                  The next automatically generated ID
                category_id:                 4
                product_code:              dgx_640
                product_name:             Yamaha DGX 640 88-Key Digital Piano
                description:                  Long description to come.
                list_price:                     799.99
                discount_percent:         0
                date_added:                  Today’s date/time.""";

        query = query("""
                            INSERT INTO products
                                (product_id, category_id, product_code, product_name, description,\s
                            list_price, discount_percent, date_added)
                                values
                            (default, 4, 'dgx_640', 'Yamaha DGX 640 88-Key Digital Piano', 'Long description to come',\s
                            '799.99', '0', NOW());
                """);

        var result =
                writeSqlStatementToTxt(
                        query,
                        fileName,
                        description,
                        List.of(table("products"))
                );

        Result<org.jooq.Record> res  = ctx.select().from(table("products")).fetch();
        println(res);
    }
    @Test
    void insertProductsTest() throws IOException {
        fileName = "m6_dml_statements_5";
        query = query("""
                UPDATE products\s
                SET\s
                    discount_percent = '35'
                WHERE
                    product_id = 11;""");

        var result =
                writeSqlStatementToTxt(
                        query,
                        fileName,
                        description,
                        List.of(table("products"))
                );
        Result<org.jooq.Record> res  = ctx.select().from(table("products")).fetch();
        println(res);
    }
    @Test
    void deleteTest2() throws IOException {
        fileName = "m6_dml_statements_6";
        description = """
               Write a DELETE statement that deletes the row that you added to the Categories table in exercise 1. 
               When you execute this statement, it will produce an error since the category has related rows in the Products table. 
               To fix that, precede the DELETE statement with another DELETE statement that deletes all products in this category. 
               (Remember that to code two or more statements in a script, you must end each statement with a semicolon.)""";

        query = query("""
                delete from Products
                where CategoryID = 4;
                delete from Categories
                where CategoryID = 4""");

        var result =
                writeSqlStatementToTxt(
                        query,
                        fileName,
                        description,
                        List.of(table("products"))
                );
    }

    @Test
    void testContainersTest() throws IOException {
        try (MySQLContainer<?> database = new MySQLContainer<>(DockerImageName.parse("mysql:latest"))
                .withDatabaseName("test_db")
                .withUsername("user")
                .withPassword("pwd")
                .withEnv("MYSQL_ROOT_HOST", "%")
                .withInitScript("Database/Sql/mysql-sakila-schema.sql")
        ) {
            database.start();
            println(database.getHost());
            println(database.getDockerClient());
            println(database.getJdbcUrl());
            println(database.getHost());
        }

    }

}

