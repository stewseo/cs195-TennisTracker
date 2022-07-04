package com.example.cs195tennis.model;

import com.example.cs195tennis.Data.Record.PlayerRecord;

import Data.Schema.Keys;
import Data.Schema.Public;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.Record;

import java.util.List;



public class Player extends TableImpl<PlayerRecord> {
    /**
     * The reference instance of public.player
     */
    public static final Player PLAYER = new Player();


    private static final long serialVersionUID = -798376522;

    @Override
    public Class<PlayerRecord> getRecordType() {
        return PlayerRecord.class;
    }

    public final TableField<PlayerRecord, Integer> PLAYER_ID = createField(DSL.name("player_id"), SQLDataType.INTEGER.nullable(false), PLAYER, "");

    public final TableField<PlayerRecord, String> FIRST_NAME = createField(DSL.name("name_first"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    public final TableField<PlayerRecord, String> LAST_NAME = createField(DSL.name("name_last"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    public final TableField<PlayerRecord, String> COUNTRY_ID = createField(DSL.name("country_id"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");


    /**
     * Create a public.player table reference
     */
    public Player() {
        this(DSL.name("AtpPlayer"), null);
    }


    /**
     * Create an aliased public.player table reference
     */
    public Player(String alias) {
        this(DSL.name(alias), PLAYER);
    }

    /**
     * Create an aliased public.player table reference
     */
    public Player(Name alias) {
        this(alias, PLAYER);
    }

    private Player(Name alias, Table<PlayerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Player(Name alias, Table<PlayerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Player(Table<O> child, ForeignKey<O, PlayerRecord> key) {
        super(child, key, PLAYER);
    }

    @Override
    public Schema getSchema() {
        return Public.SCHEMA;
    }

    @Override
    public UniqueKey<PlayerRecord> getPrimaryKey() {
        return Keys.PLAYER_PKEY;
    }

    @Override
    public List<UniqueKey<PlayerRecord>> getKeys() {
        return List.<UniqueKey<PlayerRecord>>of(Keys.PLAYER_PKEY);
    }

    @Override
    public Player as(String alias) {
        return new Player(DSL.name(alias), this);
    }

    @Override
    public Player as(Name alias) {
        return new Player(alias, this);
    }


    @Override
    public Player rename(String name) {
        return new Player(DSL.name(name), null);
    }


    @Override
    public Player rename(Name name) {
        return new Player(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

}

