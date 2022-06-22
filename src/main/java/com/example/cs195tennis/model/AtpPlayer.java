package com.example.cs195tennis.model;

import com.example.cs195tennis.Dao.Record.AtpPlayerRecord;

import com.example.cs195tennis.Dao.Table.Keys;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.Record;

import java.util.List;



public class AtpPlayer extends TableImpl<AtpPlayerRecord> {
    /**
     * The reference instance of public.player
     */
    public static final AtpPlayer ATP_PLAYER = new AtpPlayer();


    private static final long serialVersionUID = -798376522;

    @Override
    public Class<AtpPlayerRecord> getRecordType() {
        return AtpPlayerRecord.class;
    }
    /**
     * The column public.player.id
     */
    public final TableField<AtpPlayerRecord, Integer> ID = createField(DSL.name("player_id"), SQLDataType.INTEGER.nullable(false), ATP_PLAYER, "");

    /**
     * The column public.player.first_name
     */
    public final TableField<AtpPlayerRecord, String> FIRST_NAME = createField(DSL.name("name_first"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column public.player.last_name
     */
    public final TableField<AtpPlayerRecord, String> LAST_NAME = createField(DSL.name("name_last"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a public.player table reference
     */
    public AtpPlayer() {
        this(DSL.name("AtpPlayer"), null);
    }


    /**
     * Create an aliased public.player table reference
     */
    public AtpPlayer(String alias) {
        this(DSL.name(alias), ATP_PLAYER);
    }

    /**
     * Create an aliased public.player table reference
     */
    public AtpPlayer(Name alias) {
        this(alias, ATP_PLAYER);
    }

    private AtpPlayer(Name alias, Table<AtpPlayerRecord> aliased) {
        this(alias, aliased, null);
    }

    private AtpPlayer(Name alias, Table<AtpPlayerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> AtpPlayer(Table<O> child, ForeignKey<O, AtpPlayerRecord> key) {
        super(child, key, ATP_PLAYER);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<AtpPlayerRecord> getPrimaryKey() {
        return Keys.ATPPLAYER_PKEY;
    }

    @Override
    public List<UniqueKey<AtpPlayerRecord>> getKeys() {
        return List.<UniqueKey<AtpPlayerRecord>>of(Keys.ATPPLAYER_PKEY);
    }

    @Override
    public AtpPlayer as(String alias) {
        return new AtpPlayer(DSL.name(alias), this);
    }

    @Override
    public AtpPlayer as(Name alias) {
        return new AtpPlayer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AtpPlayer rename(String name) {
        return new AtpPlayer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AtpPlayer rename(Name name) {
        return new AtpPlayer(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

}

