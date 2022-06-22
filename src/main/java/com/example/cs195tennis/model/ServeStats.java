package com.example.cs195tennis.model;
import com.example.cs195tennis.Dao.Record.ServeStatsRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.lang.Record;
import java.math.BigDecimal;

public class ServeStats extends TableImpl<ServeStatsRecord> {

    private static final long serialVersionUID = 1L;

    public static final ServeStats SERVESTATS = new ServeStats();

    @Override
    public Class<ServeStatsRecord> getRecordType() {
        return ServeStatsRecord.class;
    }

    //Double> SERVE_NUMBER_ID , Integer> SPEED_KMH, Integer> POINT_NUMBER, Integer> SERVE_WIDTH ,
    // Integer> SERVE_DEPTH , Integer> PLAYER1_ACE, Organization> MATCH_ID ,  Integer> PLAYER2_ACE
    public final TableField<ServeStatsRecord, Double> SERVE_NUMBER_ID = createField(DSL.name("ServeNumber"), SQLDataType.DOUBLE, this, "");


    public final TableField<ServeStatsRecord, Integer> SPEED_KMH = createField(DSL.name("Speed_KMH"), SQLDataType.INTEGER, this, "");


    public final TableField<ServeStatsRecord, Integer> POINT_NUMBER = createField(DSL.name("PointNumber"), SQLDataType.INTEGER, this, "");


    public final TableField<ServeStatsRecord, Integer> SERVE_WIDTH = createField(DSL.name("ServeWidth"), SQLDataType.INTEGER, this, "");


    public final TableField<ServeStatsRecord, Integer> SERVE_DEPTH = createField(DSL.name("ServeDepth"), SQLDataType.INTEGER, this, "");


    public final TableField<ServeStatsRecord, Integer> PLAYER1_ACE = createField(DSL.name("P1Ace"), SQLDataType.INTEGER, this, "");


    public final TableField<ServeStatsRecord, Organization> MATCH_ID = createField(DSL.name("match_id"), SQLDataType.VARCHAR.asEnumDataType(Organization.class), this, "");


    public final TableField<ServeStatsRecord, Integer> PLAYER2_ACE = createField(DSL.name("P2Ace"), SQLDataType.INTEGER, this, "");

    private ServeStats(Name alias, Table<ServeStatsRecord> aliased) {
        this(alias, aliased, null);
    }

    private ServeStats(Name alias, Table<ServeStatsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
        create view "film_list" as  SELECT film.film_id AS fid,
          film.title,
          film.description,
          category.name AS category,
          film.rental_rate AS price,
          film.length,
          film.rating,
          group_concat((((actor.first_name)::text || ' '::text) || (actor.last_name)::text)) AS actors
         FROM ((((category
           LEFT JOIN film_category ON ((category.category_id = film_category.category_id)))
           LEFT JOIN film ON ((film_category.film_id = film.film_id)))
           JOIN film_actor ON ((film.film_id = film_actor.film_id)))
           JOIN actor ON ((film_actor.actor_id = actor.actor_id)))
        GROUP BY film.film_id, film.title, film.description, category.name, film.rental_rate, film.length, film.rating;
        """));
    }


    public ServeStats(String alias) {
        this(DSL.name(alias), SERVESTATS);
    }


    public ServeStats(Name alias) {
        this(alias, SERVESTATS);
    }

    public ServeStats() {
        this(DSL.name("film_list"), null);
    }

    public <O extends Record & org.jooq.Record> ServeStats(Table<O> child, ForeignKey<O, ServeStatsRecord> key) {
        super(child, key, SERVESTATS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public ServeStats as(String alias) {
        return new ServeStats(DSL.name(alias), this);
    }

    @Override
    public ServeStats as(Name alias) {
        return new ServeStats(alias, this);
    }


    @Override
    public ServeStats rename(String name) {
        return new ServeStats(DSL.name(name), null);
    }

    @Override
    public ServeStats rename(Name name) {
        return new ServeStats(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Double, Integer, Integer, Integer, Integer, Integer, Organization, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }


    //Double> SERVE_NUMBER_ID , Integer> SPEED_KMH, Integer> POINT_NUMBER, Integer> SERVE_WIDTH ,
    // Integer> SERVE_DEPTH , Integer> PLAYER1_ACE, Organization> MATCH_ID ,  Integer> PLAYER2_ACE

//    public <U> SelectField<U> mapping(Function8<? super Long, ? super String, ? super String, ? super String, ? super BigDecimal, ? super Short, ? super Organization, ? super String, ? extends U> from) {
//        return convertFrom(Records.mapping(from));
//    }
//
//
//    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Long, ? super String, ? super String, ? super String, ? super BigDecimal, ? super Short, ? super Organization, ? super String, ? extends U> from) {
//        return convertFrom(toType, Records.mapping(from));
//    }
}