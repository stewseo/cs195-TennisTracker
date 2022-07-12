package com.example.module8;

import com.example.database.connection.Database;
import com.example.database.sakila_database.verifyData.VerifySakilaDB;
import org.jooq.Query;
import org.jooq.impl.DSL;

import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.schema;

public class module8_subquery extends Database {
    static Query query;
    static String description;

    public static void main(String[] args) throws Exception {
        int verification = 4;
        VerifySakilaDB verifySakila = new VerifySakilaDB();

        connect("my_guitar_shop");

        switch (verification) {
            case 1 -> {
                description = """
                        Write a SELECT statement that returns the same result set as this SELECT statement, 
                       but don’t use a join. Instead, use a subquery in a WHERE clause that uses the IN keyword.
                        """;

                query = DSL.query("""
                                SELECT DISTINCT
                                    category_name
                                FROM
                                    categories
                                WHERE
                                    category_id IN (SELECT DISTINCT
                                            category_id
                                        FROM
                                            products)
                                ORDER BY category_name;
                        """
                );

                verifySakila.queryInfoToTxt(query,description,"module8/module8_q1a", List.of("categories","products"),"my_guitar_shop");

            }
            case 2 -> {
                description = """
                        Write a SELECT statement that answers this question: 
                        Which products have a list price that’s greater than the average list price for all products?
                        Return the product_name and list_price columns for each product.
                        Sort the results by the list_price column in descending sequence.
                        """;
                query = DSL.query("""
                        SELECT
                            p.product_name, p.list_price
                        FROM
                            products p
                        WHERE
                            list_price > (SELECT
                                    AVG(list_price)
                                FROM
                                    products)
                        ORDER BY list_price DESC;
                        """);
                verifySakila.queryInfoToTxt(query,description,"module8/module8_q1b", List.of("products"),"my_guitar_shop");
            }
            case 3 -> {

            }
            case 4 -> {
                description = """
                        3. Write a SELECT statement that returns three columns:
                        email_address, order_id, and the order total for each customer.
                        To do this, you can group the result set by the email_address and order_id columns.
                         In addition, you must calculate the order total from the columns in the Order_Items table.
                                                
                         Write a second SELECT statement that uses the first SELECT statement in its FROM clause.
                        The main query should return two columns: the customer’s email address and the largest order for that customer.
                        To do this, you can group the result set by the email_address.
                        """;

                query = verifySakila.queryInfoToTxt(
                        DSL.query("""
                                SELECT
                                    ot.email_address, o.order_id, max_order_total
                                FROM
                                    (SELECT
                                        email_address,
                                            o.order_id,
                                            SUM((item_price - discount_amount) * quantity) AS order_total
                                    FROM
                                        customers c
                                        JOIN orders o 
                                            ON c.customer_id = o.customer_id
                                        JOIN order_items oi 
                                            ON o.order_id = oi.order_id
                                    GROUP BY email_address , order_id) ot
                                        JOIN
                                    (SELECT
                                        email_address, MAX(order_total) AS max_order_total
                                    FROM
                                        (SELECT
                                        email_address,
                                            o.order_id,
                                            SUM((item_price - discount_amount) * quantity) AS order_total
                                    FROM
                                        customers c
                                    JOIN orders o ON c.customer_id = o.customer_id
                                    JOIN order_items oi ON o.order_id = oi.order_id
                                    GROUP BY email_address , order_id) OrdTotCopy
                                    GROUP BY email_address) MaxOrdTot ON ot.email_address = MaxOrdTot.email_address
                                        AND ot.order_total = MaxOrdTot.max_order_total;
                                """),
                        description,
                        "module8/module8_q4",
                        List.of("customers", "orders", "order_items"),
                        "my_guitar_shop");

            }
            case 5 -> {

            }
            case 6 -> {

            }
            case 7 -> {

            }
        }

    }
}
