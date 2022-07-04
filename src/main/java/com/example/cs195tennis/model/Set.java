package com.example.cs195tennis.model;

import com.example.cs195tennis.Data.Record.SetRecord;
import Data.Schema.Keys;
import Data.Schema.Public;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.impl.SQLDataType;
import java.lang.Record;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Set extends TableImpl<SetRecord> {


    private static final long serialVersionUID = -1401275800;

    public static final Set SET = new Set();

    @Override
    public Class<SetRecord> getRecordType() {
        return SetRecord.class;
    }

    public final TableField<SetRecord, Integer> SET_ID = createField(DSL.name("set_id"), SQLDataType.INTEGER.nullable(false).identity(true), SET, "");

    public final TableField<SetRecord, Integer> SET_NUM = createField(DSL.name("set_num"), SQLDataType.INTEGER.nullable(false), this, "");

    public final TableField<SetRecord, Integer> POINT_NUM = createField(DSL.name("point_num"), SQLDataType.INTEGER.nullable(false), this, "");

    public final TableField<SetRecord, Integer> MATCH_ID = createField(DSL.name("match_id"), SQLDataType.INTEGER.nullable(false), this, "");

    public Set() {
        this(DSL.name("Set"), null);
    }

    public Set(Name alias) {
        this(alias, SET);
    }

    private Set(Name alias, Table<SetRecord> aliased) {
        this(alias, aliased, null);
    }

    private Set(Name alias, Table<SetRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record & org.jooq.Record> Set(Table<O> child, ForeignKey<O, SetRecord> key) {
        super(child, key, SET);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.SCHEMA;
    }

    @Override
    public UniqueKey<SetRecord> getPrimaryKey() {
        return Keys.SET_PKEY;
    }

    @Override
    public List<UniqueKey<SetRecord>> getKeys() {
        return Arrays.<UniqueKey<SetRecord>>asList(Keys.SET_PKEY);
    }

    @Override
    public List<ForeignKey<SetRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SetRecord, ?>>asList(Keys.SET__SET_MATCH_ID_FKEY);
    }

    @Override
    public Set as(String alias) {
        return new Set(DSL.name(alias), this);
    }

    @Override
    public Set as(Name alias) {
        return new Set(alias, this);
    }


    @Override
    public Set rename(String name) {
        return new Set(DSL.name(name), null);
    }


    @Override
    public Set rename(Name name) {
        return new Set(name, null);
    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
