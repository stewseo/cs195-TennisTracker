package com.example.database.sakila_database.TestSchema.TestQuery.TestAggregateFunctions;

import org.jooq.ResultQuery;
import org.junit.jupiter.api.Test;

import static org.jooq.impl.DSL.resultQuery;

public class TestMultiplicationAggregateFunction {

    @Test
    void multiplicationAggregateFunctionTest() {
        ResultQuery<org.jooq.Record> resultQuery = resultQuery("""
                WITH p AS (
                  SELECT
                    CAST (payment_date AS DATE) AS date,
                    amount
                  FROM payment
                )
                SELECT
                  date,
                  SUM (amount) AS daily_revenue,
                  SUM (SUM (amount)) OVER (ORDER BY date) AS cumulative_revenue
                FROM p
                GROUP BY date
                ORDER BY date""");
    }
}
