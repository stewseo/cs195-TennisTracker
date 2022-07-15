package com.example.module8;

import com.example.database.sakila_database.MyCatalog;
import com.example.database.sakila_database.verifyData.VerifySakilaDB;
import org.jooq.Query;

import java.util.List;

import static org.jooq.impl.DSL.query;

public class m5 {
    static String SELECT = """
                        SELECT
                        """;

    static String FROM = """
                        FROM
                        """;

    static String WHERE = """
                        WHERE
                        """;

    static String HAVING = """
                        HAVING
                        """;

    static String GROUP_BY = """
                        GROUP BY
                        """;
    static String ORDER_BY = orderBy();

    static String SUB_QUERY = subQuery();

    public static void main(String[] args) throws Exception {
        VerifySakilaDB sakilaTests = new VerifySakilaDB("my_guitar_shop");

        String schema = MyCatalog.CATALOG.getSchema("my_guitar_shop").getName();

        sakilaTests.verifySchema(schema);

        int verification = 15;

        switch (verification) {
            case 1 -> {

                String m5Q1 = """
                        Write a SELECT statement that joins the Categories table to the Products table
                        and returns these columns: 
                        CategoryName, 
                        ProductName, ListPrice.
                        """;

                String sqlString = """
                        SELECT c.category_name, p.product_name, p.list_price
                        FROM products p
                        INNER JOIN categories c
                        """;

                SELECT = """
                        SELECT
                            c.category_name, 
                            p.product_name, 
                            p.list_price
                        """;

                FROM = """
                        FROM products p
                        INNER JOIN categories c
                        ON p.category_id = c.category_id
                        """;

                Query query = query(
                        SELECT +
                                FROM
                );

                sakilaTests.queryInfoToTxt(
                        query, //query
                        m5Q1, //title
                        "module5/module5_question1",  //file name
                        List.of("products", "categories"), //table names
                        "my_guitar_shop"); //schema in use
            }
            case 2 -> {

                String m5Q2 = """  
                        Write a SELECT statement that joins the Customers table to the Addresses table
                        and returns these columns: 
                        FirstName, LastName, 
                        Line1, City, State, ZipCode.
                        Return one row for each address 
                        for the customer with an email address of allan.sherwood@yahoo.com"
                        """;

                SELECT = """
                        SELECT 
                        c.first_name, c.last_name, 
                        a.line1, a.city, a.state, a.zip_code 
                        """;

                FROM = """
                        FROM addresses a
                        INNER JOIN customers c
                        ON a.customer_id = c.customer_id
                        """;

                WHERE = """
                        WHERE c.email_address = 'allan.sherwood@yahoo.com'
                        """;

                Query query =
                        query(SELECT+
                                FROM +
                                WHERE
                        );

                FROM = """
                        FROM addresses a
                        LEFT OUTER JOIN customers c
                        ON a.customer_id = c.customer_id
                        """;

                Query queryLeftOuterJoin =  query(
                        SELECT+
                        FROM +
                        WHERE
                );

                sakilaTests.queryInfoToTxt(query, m5Q2, "module5/module5_question2", List.of("addresses", "customers"),"my_guitar_shop");

            }
            case 3 -> {

                String m5Q3 = """
                        Write a SELECT statement that joins the Customers table to the Addresses table 
                        and returns these columns: 
                        FirstName, LastName, 
                        Line1, City, State, ZipCode.
                        Return one row for each customer, 
                        but only return addresses that are the shipping address for a customer
                """;

                 SELECT = """
                        SELECT 
                        c.first_name, c.last_name,
                        a.line1, a.city, a.state, a.zip_code
                        """;

                 FROM = """
                        FROM addresses a
                        INNER JOIN customers c
                        ON a.address_id = c.shipping_address_id 
                        """;


                Query query = query(
                        SELECT +
                        FROM
                );
                Query queryToCompare = query(query.getSQL());
                sakilaTests.queryInfoToTxt(query, m5Q3,"module5/module5_question3", List.of("customers", "addresses"),"my_guitar_shop");

            }
            case 4 -> {

                String m5Q4 = """
                        
                        Write a SELECT statement that joins the Categories table to the Products table 
                        and returns these columns: 
                        CategoryName, 
                        ProductName, ListPrice.
                        Sort the result set by CategoryName and then by ProductName in ascending order
                        """;

                FROM = """
                        FROM products p 
                        INNER JOIN categories c
                        ON p.category_id = c.category_id
                        """;

                SELECT = """
                        SELECT p.product_name, c.category_name, p.list_price 
                        """;

                ORDER_BY = """
                        ORDER BY c.category_name asc, p.product_name asc;
                        """;


                Query query = query(
                        SELECT +
                                FROM +
                                ORDER_BY
                );

                Query queryToCompare = query(query.getSQL());

                sakilaTests.queryInfoToTxt(query, m5Q4 ,"module5/module5_question4", List.of("categories", "products"),"my_guitar_shop");

            }
            case 5 -> {


                String m5Q5 = """
                        Write a SELECT statement that returns the ProductName and ListPrice columns from the Products table.
                        Return one row for each product that has the same list price as another product. 
                        Hint: Use a self-join to check that the ProductID columns aren’t equal but the ListPrice column is equal.
                        Sort the result set by ProductName.
                        """;

                String sql = """
                        SELECT i.product_name, i.list_price
                        FROM products i
                        INNER JOIN (
                         SELECT list_price
                            FROM products
                            GROUP BY list_price
                            HAVING COUNT(list_price) > 1
                        ) j ON i.list_price = j.list_price
                        """;

                SUB_QUERY = """
                        ( SELECT list_price
                        FROM products
                        GROUP BY list_price
                        HAVING COUNT(list_price) > 1 )
                        """;

                FROM = " FROM products i INNER JOIN "+
                        SUB_QUERY +
                        " j ON i.list_price = j.list_price";

                SELECT = """
                        SELECT i.product_name, i.list_price
                        """;

                Query query = query(SELECT + FROM);

                sakilaTests.queryInfoToTxt(query, m5Q5, "module5/module5_question5",List.of("products"),"my_guitar_shop");

            }
            case 6 -> {

                String m5Q6 = """
            Write a SELECT statement that returns these two columns:
            CategoryName: The CategoryName column from the Categories table
            ProductID: The ProductID column from the Products table
            Return one row for each category that has never been used. 
            Hint: Use an outer join and only return rows where the ProductID column contains a null value.
            """;

                String sql = """
                        SELECT
                            c.category_name,
                            p.product_id
                        FROM products p
                            LEFT OUTER JOIN categories c 
                                ON p.category_id = c.category_id
                        WHERE p.category_id IS NULL
                        UNION ALL
                        SELECT 
                            c.category_name, 
                            p.product_id
                        FROM products p
                            RIGHT OUTER JOIN categories c 
                                ON p.category_id = c.category_id
                        WHERE p.category_id IS NULL
                        """;

                 SELECT = """
                        """;

                 FROM = """
                         """;
                 WHERE = """
                        """;

                Query query = query(sql);


                sakilaTests.queryInfoToTxt(query, m5Q6, "module5/module5_question6", List.of("products", "categories"),"my_guitar_shop");
            }
            case 7 -> {
                String m5Q7 = """
            Use the UNION operator to generate a result set consisting of three columns 
            from the Orders table:
            ShipStatus: A calculated column that contains a value of SHIPPED or NOT SHIPPED
            OrderID: The OrderID column
            OrderDate: The OrderDate column
            If the order has a value in the ShipDate column, the ShipStatus column should contain a value of SHIPPED. Otherwise, it should contain a value of NOT SHIPPED.
            Sort the final result set by OrderDate.""";

                String sql = """
                        SELECT 
                            i.order_id, 
                            i.order_date, 
                            'not_shipped' AS 'ship_status'
                        FROM 
                            orders i 
                        WHERE 
                            i.ship_date IS NULL
                        UNION ALL 
                        SELECT 
                            i.order_id, 
                            i.order_date, 
                            'shipped' AS 'ship_status'
                        FROM 
                            orders i
                        WHERE 
                            i.ship_date IS NOT NULL
                        ORDER BY 
                            order_date asc
                        """;


                String SELECT = """
                        SELECT i.order_id, i.order_date, ship_status
                        """;

                String FROM = """
                        FROM orders i
                        """;

                WHERE = """
                        WHERE i.ship_date is not null
                        """;
                ORDER_BY = """
                        ORDER BY i.order_date asc
                        """;

                Query query = query(sql);


                sakilaTests.queryInfoToTxt(query, m5Q7, "module5/module5_question7",List.of("orders"),"my_guitar_shop");
            }
            case 15 -> {

                String sql = """
                        select j.customer_id, i.order_id
                        from orders i
                        LEFT JOIN customers j
                            on i.customer_id = j.customer_id
                        """;
                Query query = query(sql);

                String sameSyntax = """
                                   select a.*, b.*  
                                   from orders a, customers b  
                                   where a.customer_id = b.customer_id
                        """;
                Query queryJoinSyntax = query(sameSyntax);

                sakilaTests.queryInfoToTxt(query, sql, "joins/inner_join", List.of("orders", "customers"),"my_guitar_shop");

                sql = """
                        SELECT 
                            j.customer_id, i.order_id
                        FROM orders i
                            LEFT JOIN customers j
                                ON i.customer_id = j.customer_id
                                        """;

                query = query(sql);

                sameSyntax = """
                  SELECT 
                            j.customer_name, i.order_id
                        FROM orders i
                            LEFT JOIN customers j
                                ON i.customer_id = j.customer_id
                        """;

                sakilaTests.queryInfoToTxt(query, sql, "joins/left_outer_join", List.of("orders", "customers"),"my_guitar_shop");

                sql = """
                        select * 
                        from orders 
                        RIGHT OUTER JOIN customers 
                            on orders.customer_id = customers.customer_id
                        """;
                query = query(sql);

                sameSyntax = """
                                   select a.*, b.*  
                                   from orders a, customers b  
                                   where a.customer_id = b.customer_id
                        """;

                sakilaTests.queryInfoToTxt(query, sql, "joins/right_outer_join",List.of("orders", "customers"),"my_guitar_shop");
            }

        }
    }

    private static String OUTER_QUERY(String select, String from) {
        return select + from;
    }
    private static String INNER_QUERY(String select, String from){
        return "";
    }
    private static String orderBy() {
        return """
                ORDER BY
                """;
    }
    private static String subQuery() {
        return """
                SELECT 
                    list_price
                FROM products
                GROUP BY list_price
                HAVING COUNT( list_price) > 1
                """;
    }
}
