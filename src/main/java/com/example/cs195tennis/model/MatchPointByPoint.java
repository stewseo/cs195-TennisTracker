package com.example.cs195tennis.model;

import com.example.cs195tennis.Dao.Record.MatchPointRecord;
import com.example.cs195tennis.Dao.Table.Keys;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.lang.Record;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MatchPointByPoint extends TableImpl<MatchPointRecord> {


    private static final long serialVersionUID = -1401275800;

    public static final MatchPointByPoint MATCHPOINT = new MatchPointByPoint();

    @Override
    public Class<MatchPointRecord> getRecordType() {
        return MatchPointRecord.class;
    }

    public final TableField<MatchPointRecord, Integer> ID = createField(DSL.name("match_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    public final TableField<MatchPointRecord, Integer> GAME_NO = createField(DSL.name("GameNo"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    public final TableField<MatchPointRecord, Integer> SET_NO = createField(DSL.name("SetNo"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    public final TableField<MatchPointRecord, Integer> P1_SCORE = createField(DSL.name("P1Score"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    public MatchPointByPoint() {
        this(DSL.name("GrandSlamPointByPoint"), null);
    }

    public MatchPointByPoint(Name alias) {
        this(alias, MATCHPOINT);
    }

    private MatchPointByPoint(Name alias, Table<MatchPointRecord> aliased) {
        this(alias, aliased, null);
    }

    private MatchPointByPoint(Name alias, Table<MatchPointRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record & org.jooq.Record> MatchPointByPoint(Table<O> child, ForeignKey<O, MatchPointRecord> key) {
        super(child, key, MATCHPOINT);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<MatchPointRecord> getPrimaryKey() {
        return Keys.MATCH_PKEY;
    }

    @Override
    public List<UniqueKey<MatchPointRecord>> getKeys() {
        return Arrays.<UniqueKey<MatchPointRecord>>asList(Keys.MATCH_PKEY);
    }

    @Override
    public List<ForeignKey<MatchPointRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MatchPointRecord, ?>>asList(Keys.MATCH__XXX);
    }


    public GrandSlam tournament() {
        return new GrandSlam(this, Keys.MATCH__XXX);
    }

    @Override
    public MatchPointByPoint as(String alias) {
        return new MatchPointByPoint(DSL.name(alias), this);
    }

    @Override
    public MatchPointByPoint as(Name alias) {
        return new MatchPointByPoint(alias, this);
    }


    @Override
    public MatchPointByPoint rename(String name) {
        return new MatchPointByPoint(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MatchPointByPoint rename(Name name) {
        return new MatchPointByPoint(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
