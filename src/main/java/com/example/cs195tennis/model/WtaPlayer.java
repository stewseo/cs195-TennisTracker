package com.example.cs195tennis.model;

import com.example.cs195tennis.Data.Record.WtaPlayerRecord;

import Data.Schema.Keys;
import Data.Schema.Public;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.Record;

import java.util.List;



public class WtaPlayer extends TableImpl<WtaPlayerRecord> {

    public static final WtaPlayer WTA_PLAYER = new WtaPlayer();

    private static final long serialVersionUID = -798376522;

    @Override
    public Class<WtaPlayerRecord> getRecordType() {
        return WtaPlayerRecord.class;
    }

    public final TableField<WtaPlayerRecord, Integer> PLAYER_ID = createField(DSL.name("player_id"), SQLDataType.INTEGER.nullable(false), WTA_PLAYER, "");

    public final TableField<WtaPlayerRecord, String> FIRST_NAME = createField(DSL.name("name_first"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    public final TableField<WtaPlayerRecord, String> LAST_NAME = createField(DSL.name("name_last"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    public WtaPlayer() {
        this(DSL.name("WtaPlayer"), null);
    }

    public WtaPlayer(String alias) {
        this(DSL.name(alias), WTA_PLAYER);
    }

    public WtaPlayer(Name alias) {
        this(alias, WTA_PLAYER);
    }

    private WtaPlayer(Name alias, Table<WtaPlayerRecord> aliased) {
        this(alias, aliased, null);
    }

    private WtaPlayer(Name alias, Table<WtaPlayerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> WtaPlayer(Table<O> child, ForeignKey<O, WtaPlayerRecord> key) {
        super(child, key, WTA_PLAYER);
    }

    @Override
    public Schema getSchema() {
        return Public.SCHEMA;
    }

    @Override
    public UniqueKey<WtaPlayerRecord> getPrimaryKey() {
        return Keys.WTAPLAYER_PKEY;
    }

    @Override
    public List<UniqueKey<WtaPlayerRecord>> getKeys() {
        return List.<UniqueKey<WtaPlayerRecord>>of(Keys.WTAPLAYER_PKEY);
    }

    @Override
    public WtaPlayer as(String alias) {
        return new WtaPlayer(DSL.name(alias), this);
    }

    @Override
    public WtaPlayer as(Name alias) {
        return new WtaPlayer(alias, this);
    }

    @Override
    public WtaPlayer rename(String name) {
        return new WtaPlayer(DSL.name(name), null);
    }

    @Override
    public WtaPlayer rename(Name name) {
        return new WtaPlayer(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

}

