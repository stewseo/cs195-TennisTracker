package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.model.Table.Record.CustomerRecord;
import com.example.database.sakila_database.schema.Indexes;
import com.example.database.sakila_database.schema.Keys;
import com.example.database.sakila_database.schema.Public;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.jooq.impl.DSL.name;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Customer extends TableImpl<CustomerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.customer</code>
     */
    public static final Customer CUSTOMER = new Customer();


    /**
     * The class holding records for this type
     */
    @Override
    public Class<CustomerRecord> getRecordType() {
        return CustomerRecord.class;
    }

    /**
     * The column <code>public.customer.customer_id</code>.
     */
    public final TableField<CustomerRecord, Long> CUSTOMER_ID = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.customer.store_id</code>.
     */
    public final TableField<CustomerRecord, Long> STORE_ID = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.customer.first_name</code>.
     */
    public final TableField<CustomerRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>public.customer.last_name</code>.
     */
    public final TableField<CustomerRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>public.customer.email</code>.
     */
    public final TableField<CustomerRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.customer.address_id</code>.
     */
    public final TableField<CustomerRecord, Long> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.customer.activebool</code>.
     */
    public final TableField<CustomerRecord, Boolean> ACTIVEBOOL = createField(DSL.name("activebool"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.customer.create_date</code>.
     */
    public final TableField<CustomerRecord, LocalDate> CREATE_DATE = createField(DSL.name("create_date"), SQLDataType.LOCALDATE.nullable(false).defaultValue(DSL.field("('now'::text)::date", SQLDataType.LOCALDATE)), this, "");

    /**
     * The column <code>public.customer.last_update</code>.
     */
    public final TableField<CustomerRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.customer.active</code>.
     */
    public final TableField<CustomerRecord, Integer> ACTIVE = createField(DSL.name("active"), SQLDataType.INTEGER, this, "");


    private Customer(Name alias, Table<CustomerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Customer(Name alias, Table<CustomerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.customer</code> table reference
     */
    public Customer(String alias) {
        this(DSL.name(alias), CUSTOMER);
    }

    /**
     * Create an aliased <code>public.customer</code> table reference
     */
    public Customer(Name alias) {
        this(alias, CUSTOMER);
    }

    public Customer() {
        this(DSL.name("customer"), null);
    }

    public <O extends Record> Customer(Table<O> child, ForeignKey<O, CustomerRecord> key) {
        super(child, key, CUSTOMER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_FK_ADDRESS_ID, Indexes.IDX_FK_STORE_ID, Indexes.IDX_LAST_NAME);
    }

    @Override
    public Identity<CustomerRecord, Long> getIdentity() {
        return (Identity<CustomerRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CustomerRecord> getPrimaryKey() {
        return Keys.CUSTOMER_PKEY;
    }

    @Override
    public List<ForeignKey<CustomerRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CUSTOMER__CUSTOMER_STORE_ID_FKEY, Keys.CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY);
    }

    private transient Store _store;
    private transient Address _address;

    /**
     * Get the implicit join path to the <code>public.address</code> table.
     */
    public Address address() {
        if (_address == null)
            _address = new Address(this, Keys.CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY);

        return _address;
    }

    @Override
    public Customer as(String alias) {
        return new Customer(DSL.name(alias), this);
    }

    @Override
    public Customer as(Name alias) {
        return new Customer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Customer rename(String name) {
        return new Customer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Customer rename(Name name) {
        return new Customer(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------
    @Override
    public Row12<Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}