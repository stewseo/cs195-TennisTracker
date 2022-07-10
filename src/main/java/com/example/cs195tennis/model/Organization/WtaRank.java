//package com.example.cs195tennis.model.Organization;
//
//
//import com.example.database.schema.Keys;
//import com.example.database.schema.Public;
//import com.example.cs195tennis.model.Record.WtaRankRecord;
//import org.jooq.Record;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.TableImpl;
//
//import java.util.List;
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class WtaRank extends TableImpl<WtaRankRecord> {
//    private static final long serialVersionUID = -1401275800;
//
//    public static final WtaRank WTA_RANK = new WtaRank();
//
//    @Override
//    public Class<WtaRankRecord> getRecordType() {
//        return WtaRankRecord.class;
//    }
//
//    public final TableField<WtaRankRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), WTA_RANK, "");
//
//    public final TableField<WtaRankRecord, Integer> RANKING_DATE = createField(DSL.name("ranking_date"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    public final TableField<WtaRankRecord, Integer> PLAYER_ID = createField(DSL.name("player"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    public final TableField<WtaRankRecord, Integer> RANKING = createField(DSL.name("rank"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    public final TableField<WtaRankRecord, Integer> POINTS = createField(DSL.name("points"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    final static String intTable = "WtaRank";
//    final static String stringTable = "WtaRanks2000_2022";
//    public WtaRank() {
//        this(DSL.name(intTable), null);
//    }
//
//    public WtaRank(Name alias) {
//        this(alias, WTA_RANK);
//    }
//
//    private WtaRank(Name alias, Table<WtaRankRecord> aliased) {
//        this(alias, aliased, null);
//    }
//
//    private WtaRank(Name alias, Table<WtaRankRecord> aliased, Field<?>[] parameters) {
//        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
//    }
//
//    public <O extends Record> WtaRank(Table<O> child, ForeignKey<O, WtaRankRecord> key) {
//        super(child, key, WTA_RANK);
//    }
//
//    @Override
//    public Schema getSchema() {
//        return Public.SCHEMA;
//    }
//
//    @Override
//    public UniqueKey<WtaRankRecord> getPrimaryKey() {
//        return Keys.WTARANK_PKEY;
//    }
//
//    @Override
//    public List<UniqueKey<WtaRankRecord>> getKeys() {
//        return List.<UniqueKey<WtaRankRecord>>of(Keys.WTARANK_PKEY);
//    }
//
//    @Override
//    public List<ForeignKey<WtaRankRecord, ?>> getReferences() {
//        return List.<ForeignKey<WtaRankRecord, ?>>of(Keys.WTARANK__ATP_RANK_PLAYER_ID_FKEY);
//    }
//
//    public WtaPlayer player() {return new WtaPlayer(this, Keys.WTARANK__ATP_RANK_PLAYER_ID_FKEY);
//    }
//
//    @Override
//    public WtaRank as(String alias) {
//        return new WtaRank(DSL.name(alias), this);
//    }
//
//    @Override
//    public WtaRank as(Name alias) {
//        return new WtaRank(alias, this);
//    }
//
//    /**
//     * Rename this table
//     */
//    @Override
//    public WtaRank rename(String name) {
//        return new WtaRank(DSL.name(name), null);
//    }
//
//    /**
//     * Rename this table
//     */
//    @Override
//    public WtaRank rename(Name name) {
//        return new WtaRank(name, null);
//    }
//
//    // -------------------------------------------------------------------------
//    // Row4 type methods
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row5<Integer, Integer, Integer, Integer, Integer> fieldsRow() {
//        return (Row5) super.fieldsRow();
//    }
//
//}
