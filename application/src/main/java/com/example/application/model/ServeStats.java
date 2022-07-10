//package com.example.cs195tennis.model.Statistics;
//
//import com.example.database.schema.Public;
//import com.example.cs195tennis.model.Record.ServeStatsRecord;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.SQLDataType;
//import org.jooq.impl.TableImpl;
//
//import java.lang.Record;
//
//public class ServeStats extends TableImpl<ServeStatsRecord> {
//
//    private static final long serialVersionUID = 1L;
//
//    public static final ServeStats SERVESTATS = new ServeStats();
//
//    @Override
//    public Class<ServeStatsRecord> getRecordType() {
//        return ServeStatsRecord.class;
//    }
//
//    //Double> SERVE_NUMBER_ID , Integer> SPEED_KMH, Integer> POINT_NUMBER, Integer> SERVE_WIDTH ,
//    // Integer> SERVE_DEPTH , Integer> PLAYER1_ACE, Organization> MATCH_ID ,  Integer> PLAYER2_ACE
//    public final TableField<ServeStatsRecord, Integer> SERVE_ID = createField(DSL.name("ServeNumber"), SQLDataType.INTEGER, SERVESTATS, "");
//
//
//    public final TableField<ServeStatsRecord, Integer> SPEED_KMH = createField(DSL.name("Speed_KMH"), SQLDataType.INTEGER, this, "");
//
//
//    public final TableField<ServeStatsRecord, Integer> POINT_NUMBER = createField(DSL.name("PointNumber"), SQLDataType.INTEGER, this, "");
//
//
//    public final TableField<ServeStatsRecord, Integer> SERVE_WIDTH = createField(DSL.name("ServeWidth"), SQLDataType.INTEGER, this, "");
//
//
//    public final TableField<ServeStatsRecord, Integer> SERVE_DEPTH = createField(DSL.name("ServeDepth"), SQLDataType.INTEGER, this, "");
//
//
//    public final TableField<ServeStatsRecord, Integer> PLAYER1_ACE = createField(DSL.name("P1Ace"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<ServeStatsRecord, Integer> GAME_ID = createField(DSL.name("game_id"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<ServeStatsRecord, Integer> PLAYER2_ACE = createField(DSL.name("P2Ace"), SQLDataType.INTEGER, this, "");
//
//    private ServeStats(Name alias, Table<ServeStatsRecord> aliased) {
//        this(alias, aliased, null);
//    }
//
//    private ServeStats(Name alias, Table<ServeStatsRecord> aliased, Field<?>[] parameters) {
//        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view());
//    }
//
//
//    public ServeStats(String alias) {
//        this(DSL.name(alias), SERVESTATS);
//    }
//
//
//    public ServeStats(Name alias) {
//        this(alias, SERVESTATS);
//    }
//
//    public ServeStats() {
//        this(DSL.name("ServeStats"), null);
//    }
//
//    public <O extends Record & org.jooq.Record> ServeStats(Table<O> child, ForeignKey<O, ServeStatsRecord> key) {
//        super(child, key, SERVESTATS);
//    }
//
//    @Override
//    public Schema getSchema() {
//        return aliased() ? null : Public.SCHEMA;
//    }
//
//    @Override
//    public ServeStats as(String alias) {
//        return new ServeStats(DSL.name(alias), this);
//    }
//
//    @Override
//    public ServeStats as(Name alias) {
//        return new ServeStats(alias, this);
//    }
//
//
//    @Override
//    public ServeStats rename(String name) {
//        return new ServeStats(DSL.name(name), null);
//    }
//
//    @Override
//    public ServeStats rename(Name name) {
//        return new ServeStats(name, null);
//    }
//
//    // -------------------------------------------------------------------------
//    // Row8 type methods
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, String> fieldsRow() {
//        return (Row8) super.fieldsRow();
//    }
//
//
//
////    public <U> SelectField<U> mapping(Function8<? super Long, ? super String, ? super String, ? super String, ? super BigDecimal, ? super Short, ? super Organization, ? super String, ? extends U> from) {
////        return convertFrom(Records.mapping(from));
////    }
////
////
////    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Long, ? super String, ? super String, ? super String, ? super BigDecimal, ? super Short, ? super Organization, ? super String, ? extends U> from) {
////        return convertFrom(toType, Records.mapping(from));
////    }
//}