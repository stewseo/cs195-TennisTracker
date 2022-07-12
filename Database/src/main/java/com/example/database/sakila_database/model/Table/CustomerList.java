package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.schema.Public;
import com.example.database.sakila_database.model.Table.Record.CustomerListRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.lang.Record;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerList extends TableImpl<CustomerListRecord> {

    public static final CustomerList CUSTOMER_LIST = new CustomerList();

    private static final long serialVersionUID = 1L;

    /**
     * The class holding records for this type
     * @return
     */
    @Override
    public Class getRecordType() {
        return CustomerListRecord.class;
    }

    public final TableField<CustomerListRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT, this, "");

    public final TableField<CustomerListRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.customer_list.address</code>.
     */
    public final TableField<CustomerListRecord, String> ADDRESS = createField(DSL.name("address"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.customer_list.zip code</code>.
     */
    public final TableField<CustomerListRecord, String> ZIP_CODE = createField(DSL.name("zip code"), SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>public.customer_list.phone</code>.
     */
    public final TableField<CustomerListRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>public.customer_list.city</code>.
     */
    public final TableField<CustomerListRecord, String> CITY = createField(DSL.name("city"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.customer_list.country</code>.
     */
    public final TableField<CustomerListRecord, String> COUNTRY = createField(DSL.name("country"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.customer_list.notes</code>.
     */
    public final TableField<CustomerListRecord, String> NOTES = createField(DSL.name("notes"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.customer_list.sid</code>.
     */
    public final TableField<CustomerListRecord, Long> SID = createField(DSL.name("sid"), SQLDataType.BIGINT, this, "");

    private CustomerList(Name alias, Table<CustomerListRecord> aliased) {
        this(alias, aliased, null);
    }

    private CustomerList(Name alias, Table<CustomerListRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
        create view "customer_list" as  SELECT cu.customer_id AS id,
         (((cu.first_name)::text || ' '::text) || (cu.last_name)::text) AS name,
         a.address,
         a.postal_code AS "zip code",
         a.phone,
         city.city,
         country.country,
             CASE
                 WHEN cu.activebool THEN 'active'::text
                 ELSE ''::text
             END AS notes,
         cu.store_id AS sid
        FROM (((customer cu
          JOIN address a ON ((cu.address_id = a.address_id)))
          JOIN city ON ((a.city_id = city.city_id)))
          JOIN country ON ((city.country_id = country.country_id)));
        """));
    }

    /**
     * Create an aliased <code>public.customer_list</code> table reference
     */
    public CustomerList(String alias) {
        this(DSL.name(alias), CUSTOMER_LIST);
    }

    /**
     * Create an aliased <code>public.customer_list</code> table reference
     */
    public CustomerList(Name alias) {
        this(alias, CUSTOMER_LIST);
    }

    /**
     * Create a <code>public.customer_list</code> table reference
     */
    public CustomerList() {
        this(DSL.name("customer_list"), null);
    }

    public <O extends Record & org.jooq.Record> CustomerList(Table<O> child, ForeignKey<O, CustomerListRecord> key) {
        super(child, key, CUSTOMER_LIST);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }


    @Override
    public Table as(String alias) {
        return new CustomerList(DSL.name(alias), this);
    }

    @Override
    public Table as(Name alias) {
        return new CustomerList(alias, this);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------
    @Override
    public Row9<Long, String, String, String, String, String, String, String, Long> fieldsRow() {
        return (Row9) super.fieldsRow();
    }



}