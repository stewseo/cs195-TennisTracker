package com.example.database.sakila_database.TestNestedRecords;

import com.example.database.TestDatabase;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.database.sakila_database.model.Table.Address.ADDRESS;
import static com.example.database.sakila_database.model.Table.City.CITY;
import static com.example.database.sakila_database.model.Table.Country.COUNTRY;
import static com.example.database.sakila_database.model.Table.Customer.CUSTOMER;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.resultQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNestedRecords extends TestDatabase {

    @Test
    void nestedRecordsWithAdHocConverterTest(){
        record Country(String name) {}
        record Customer(String firstName, String lastName, Country country) {}

        String sql = """
                SELECT cu.first_name, cu.last_name, co.country
                FROM customer cu
                INNER JOIN address a
                    ON cu.address_id = a.address_id
                INNER JOIN city c
                    ON a.city_id = c.city_id
                INNER JOIN country co
                    ON c.country_id = co.country_id
                ORDER BY 1, 2
                LIMIT 5
                """;

        List<Customer> r =
                ctx.select(
                                CUSTOMER.FIRST_NAME,
                                CUSTOMER.LAST_NAME,
                                COUNTRY.COUNTRY_.convertFrom(Country::new))
                        .from(CUSTOMER)
                        .join(ADDRESS).on(CUSTOMER.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID))
                        .join(CITY).on(ADDRESS.CITY_ID.eq(CITY.CITY_ID))
                        .join(COUNTRY).on(CITY.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID))
                        .orderBy(1, 2)
                        .limit(5)
                        .fetch(mapping(Customer::new));

        r.forEach(TestNestedRecords::println);

        Customer customer1 = new Customer("AARON","SELBY", new Country("Congo, The Democratic Republic of the"));
        assertEquals(r.get(0),customer1);

        Customer customer5 = new Customer("ALAN","KAHN", new Country("China"));
        assertEquals(r.get(4),customer5);
    }

    @Test
    void nestedRecordsTest() {
        ResultQuery<Record> resultQuery = null;
        String description = null;

        title("Produce all films and their actors");

        resultQuery = resultQuery("""
                SELECT cu.first_name, a.*, c.*, co.*
                FROM customer cu
                    JOIN address a
                        ON cu.address_id = a.address_id
                    JOIN city c
                        ON a.city_id = c.city_id
                    JOIN country co 
                        ON c.country_id = co.country_id
                WHERE cu.first_name = 'Mario'
                """
        );
        description = "return all columns: customer, address, city, country\n" +
                "customer_first_name = 'Mario' ";

        Result<Record> result = ctx.fetch(resultQuery);

        assertEquals(result.size(), 1);

        resultQuery = resultQuery("""   
                SELECT 
                       cu.first_name, cu.last_name, 
                       a.address, a.postal_code, 
                       c.city,
                       co.country
                FROM customer cu
                    JOIN address a
                        ON cu.address_id = a.address_id
                    JOIN city c
                        ON a.city_id = c.city_id
                    JOIN country co 
                        ON c.country_id = co.country_id
                WHERE co.country = 'Algeria'
                """
        );

        description = "return customer first name, last name, address, postalcode, city, country\n" +
                "country = 'Algeria' ";

        result = ctx.fetch(resultQuery);

        assertEquals(result.fieldsRow().size(), 6);

        assertEquals(result.size(), 3);

        resultQuery = resultQuery("""
                     SELECT
                       cu.first_name,
                       cu.last_name,
                       (
                         SELECT count(*)
                         FROM customer as cu2
                           JOIN address
                             USING (address_id)
                           JOIN city as ci2
                             USING (city_id)
                         WHERE ci2.country_id = ci.country_id
                       ) as customers_from_same_country
                     FROM customer as cu
                       JOIN address
                         USING (address_id)
                       JOIN city as ci
                         USING (city_id)
                """
        );
        description = "return customers in same country";

        result = ctx.fetch(resultQuery);


        resultQuery = resultQuery("""
                SELECT
                  co.country,
                  COUNT(*),
                  COUNT(DISTINCT city.city)
                FROM customer AS cu
                JOIN address USING (address_id)
                JOIN city USING (city_id)
                JOIN country AS co USING (country_id)
                GROUP BY co.country
                ORDER BY co.country
                """
        );
        description = "return all columns from tables customer, address, city, country\n" +
                "group by country, order by country";

        result = ctx.fetch(resultQuery);

        assertEquals(result.size(), 108);

    }
}
