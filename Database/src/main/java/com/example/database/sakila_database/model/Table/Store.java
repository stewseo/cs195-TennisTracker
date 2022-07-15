package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.model.Table.Record.StoreRecord;
import com.example.database.sakila_database.schema.Indexes;
import com.example.database.sakila_database.schema.Keys;
import com.example.database.sakila_database.schema.Public;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.lang.Record;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.jooq.impl.DSL.name;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Store extends TableImpl<StoreRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.store</code>
     */
    public static final Store STORE = new Store();



    /**
     * The class holding records for this type
     */
    @Override
    public Class<StoreRecord> getRecordType() {
        return StoreRecord.class;
    }

    /**
     * The column <code>public.store.store_id</code>.
     */
    public final TableField<StoreRecord, Long> STORE_ID = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.store.manager_staff_id</code>.
     */
    public final TableField<StoreRecord, Long> MANAGER_STAFF_ID = createField(DSL.name("manager_staff_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.store.address_id</code>.
     */
    public final TableField<StoreRecord, Long> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.store.last_update</code>.
     */
    public final TableField<StoreRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");


    private Store(Name alias, Table<StoreRecord> aliased) {
        this(alias, aliased, null);
    }

    private Store(Name alias, Table<StoreRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.store</code> table reference
     */
    public Store(String alias) {
        this(DSL.name(alias), STORE);
    }

    /**
     * Create an aliased <code>public.store</code> table reference
     */
    public Store(Name alias) {
        this(alias, STORE);
    }

    /**
     * Create a <code>public.store</code> table reference
     */
    public Store() {
        this(DSL.name("store"), null);
    }

    public <O extends Record & org.jooq.Record> Store(Table<O> child, ForeignKey<O, StoreRecord> key) {
        super(child, key, STORE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_UNQ_MANAGER_STAFF_ID);
    }

    @Override
    public Identity<StoreRecord, Long> getIdentity() {
        return (Identity<StoreRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<StoreRecord> getPrimaryKey() {
        return Keys.STORE_PKEY;
    }

    @Override
    public List<ForeignKey<StoreRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STORE__STORE_ADDRESS_ID_FKEY);
    }

    private transient Address _address;



    @Override
    public Store as(String alias) {
        return new Store(DSL.name(alias), this);
    }

    @Override
    public Store as(Name alias) {
        return new Store(alias, this);
    }

//    @Override
//    public Store as(Table<?> alias) {
//        return new Store(alias.getQualifiedName(), this);
//    }

    /**
     * Rename this table
     */
    @Override
    public Store rename(String name) {
        return new Store(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Store rename(Name name) {
        return new Store(name, null);
    }

    /**
     * Rename this table
     */


    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Long, LocalDateTime, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

}