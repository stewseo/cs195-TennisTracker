package com.example.model.table;

import com.example.model.AbstractTestModel;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQL;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.jooq.impl.DSL.*;

public class TestSqlDmlExecution extends AbstractTestModel {
    static String description, fileName;

    // A plain SQL QueryPart.
    static SQL sqlDml;

    //reference to products table
    static Table<?> PRODUCTS = products();

    private static Table<?> products() {
        return Objects.requireNonNullElseGet(PRODUCTS, () -> table("products"));
    }

    //reference to categories table
    static Table<?> CATEGORIES = categories();

    private static Table<?> categories() {
        return Objects.requireNonNullElseGet(CATEGORIES, () -> table("categories"));
    }

    //===========================================================================
    //             Insert a new record with a category_name and category_id
    //==========================================================================
    @Test
    void insertNewRecordTestt() throws IOException {
        fileName = "m6_dml_statements_1";

        description = """
                insert into CATEGORIES (CATEGORY_ID, CATEGORY_NAME)
                values (
                  default,
                  'Brass'
                )
                """;

        var result =
                writeSqlStatementToTxt(
                        sqlDml = sql("""
                                insert into CATEGORIES (CATEGORY_ID, CATEGORY_NAME)
                                  values (
                                    default,
                                    'Brass'
                                  )
                                  """),
                        fileName,
                        description,
                        List.of(table("categories"))
                );
    }

    //=======================================================================
    //                   Update category_name using category_id
    //=======================================================================

    @Test
    void updateRecordTest() throws IOException {

        fileName = "m6_dml_statements_2";

        description = """
                Update category_name 'Brass' to 'Woodwinds'.
                """;

        var result =
                writeSqlStatementToTxt(
                        sqlDml = sql("""
                                UPDATE categories
                                set
                                  category_name = 'Woodwinds'
                                where categories.category_id = (
                                  SELECT *
                                  FROM (
                                    SELECT categories.category_id
                                    FROM categories
                                    WHERE categories.category_name = 'Brass'
                                  ) as `t`
                                  )
                                """),
                        fileName,
                        description,
                        List.of(CATEGORIES)
                );
    }

    //=======================================================================
    //                   Delete most recently created Record
    //=======================================================================
    @Test
    void deleteMostRecentRecordTest() throws IOException {

        fileName = "m6_dml_statements_3";

        description = """
                Delete most recently created Record
                """;
        var result =
                writeSqlStatementToTxt(
                        sqlDml = sql("""
                                DELETE  c.*
                                FROM categories c
                                LEFT JOIN
                                        (
                                        SELECT category_id
                                        FROM categories
                                        ORDER BY
                                                category_id DESC
                                        LIMIT 1
                                        ) q
                                ON c.category_id = q.category_id
                                WHERE q.category_id IS NULL
                                """), fileName,
                        description,
                        List.of(table("categories"))
                );
    }

    @Test
    void insertNewRecordWithFKReference() throws IOException {
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


        sqlDml = sql("""
                INSERT into products (
                  category_id,
                  product_code,
                  product_name,
                  description,
                  list_price,
                  discount_percent,
                  date_added
                )
                values (
                  4,
                  'dgx_640',
                  'Yamaha DGX 640 88-Key Digital Piano',
                  'Long description to come.',
                  799.99,
                  0,
                  current_timestamp()
                )
                """);

        var result =
                writeSqlStatementToTxt(
                        sqlDml,
                        fileName,
                        description,
                        List.of(table("products"))
                );

        Result<Record> res  = ctx.select().from(table("products")).fetch();
        println(res);
    }

    @Test
    void insertProductsTest() throws IOException {
        fileName = "m6_dml_statements_5";

        description = "Write an UPDATE statement that modifies the product you added in exercise 4." +
                " This statement should change the discount_percent column from 0% to 35%.";

        sqlDml = sql("""
                UPDATE products
                SET products.discount_percent = 35
                WHERE products.product_id = 11
                """);

        var result =
                writeSqlStatementToTxt(
                        sqlDml,
                        fileName,
                        description,
                        List.of(table("products"))
                );

    }

    @Test
    void deleteTest2() throws IOException {
        fileName = "m6_dml_statements_6";
        description = """
                Write a DELETE statement that deletes the row that you added to the Categories table in exercise 1. 
                When you execute this statement, it will produce an error since the category has related rows in the Products table. 
                To fix that, precede the DELETE statement with another DELETE statement that deletes all products in this category. 
                (Remember that to code two or more statements in a script, you must end each statement with a semicolon.)""";

        log.debug("categories table: ");

        PRODUCTS = DSL.table("products");

        ctx.alterTable(PRODUCTS).dropConstraint("'products_fk_categories'").execute();

        Table<?> categoriesTable = DSL.table("categories");

        var res = fetchResults(categoriesTable);

        log.info("categories table before executing delete statement: " + res);

        sqlDml = sql("""
                DELETE t1.*, t2
                FROM categories t1 
                    INNER JOIN products t2
                    ON t2.category_id = t1.category_id
                WHERE t1.category_id = 6
                                   """);

        res = fetchResults(categoriesTable);

        log.info("categories table after executing delete statement: " + res);

        var result =
                writeSqlStatementToTxt(
                        sqlDml,
                        fileName,
                        description,
                        List.of(table("categories"))
                );
    }
    //TODO: check for memory leaks
    private Result<? extends Record> fetchResults(Table<? extends Record> table) {
        return ctx.select().from(table).fetch();
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

