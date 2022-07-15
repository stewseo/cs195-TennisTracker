package com.example.database.sakila_database.util;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.query;

public class TestSqlStrings {

    /**
     * @return List of Correct query strings used to Check the Lexical vs Logical SQL order
     */
    private List<String> getCorrectQueries() {
        List<String> list = new ArrayList<>();
        int i = 0;

        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name, count(*) " +
                "FROM products " +
                "GROUP BY product_name " +
                "HAVING count(*) > 0 "
        );

        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name, count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name\n" +
                "ORDER BY count(*) DESC"
        );

        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name, MAX(product_id), count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name"

        );

        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name || ' ' || MAX(product_id), count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name"
        );

        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT MAX(product_name || ' ' || product_id), count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name"
        );

        return list;
    }

    /**
     * @return List of Incorrect query strings used to Check the Lexical vs Logical SQL order
     */
    private List<String> getIncorrectQueries() {
        List<String> list = new ArrayList<>();

        //-- Wrong: Because aggregate functions are calculated
        //-- *after* GROUP BY, and WHERE is applied *before* GROUP BY
        //
        //-- logical order         -- available columns after operation
        //-------------------------------------------------------------
        //FROM products            -- products.*
        //WHERE ??? > 1            -- products.* (count not yet available!)
        //GROUP BY product_name      -- product_name (customer.* for aggs only)
        //<aggregate> count(*)     -- product_name, count
        //SELECT product_name, count -- product_name, count
        list.add(
                "SELECT product_name, count(*) " +
                        "FROM products " +
                        "WHERE count(*) > 0 " +
                        "GROUP BY product_name "
        );



        //         * Wrong:
//         * Because the GROUP BY clause creates groups of
//         * product names, and all the remaining products columns
//         * are aggregated into a list, which is only visiblbe to
//         * aggregate functions
//         *
//         * -- logical order         -- available columns after operation
//         * ----------------------------------------------------------------
//         * FROM products            -- products.*
//         * GROUP BY product_name      -- product_name (products.* for aggs only)
//         * <aggregate> count(*)     -- product_name, count
//         * -- product_name, count (product_id removed)
//         *  SELECT product_name, ???, count
//
        list.add(
                "SELECT product_name, product_id, count(*)\n" +
                        "        FROM products\n" +
                        "        GROUP BY product_name"
        );


        //-- Wrong: Because we still cannot access the last name column
        //-- which is in that list after the GROUP BY clause.
        //
        //-- logical order         -- available columns after operation
        //-----------------------------------------------------------------
        //FROM products            -- products.*
        //GROUP BY product_name    -- product_name (products.* for aggs only)
        //<aggregate> count(*)     -- product_name, count
        //                         -- product_name, count (last_name product_id)
        //SELECT product_name || ' ' || ???, count
        list.add("" +
                "SELECT product_name || ' ' || product_id, count(*) " +
                "FROM products " +
                "GROUP BY product_name"
        );

        return list;
    }

    /**
     * @return List of Query Sql Strings in MySql syntax
     */
    List<String> listOfSqlStrings(){
        return List.of(query("q1").getSQL(), query("q2").getSQL(), query("q3").getSQL());
    }
}
