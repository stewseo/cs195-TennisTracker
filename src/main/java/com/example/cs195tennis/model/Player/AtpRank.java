//package com.example.cs195tennis.model.Player;
//
//import com.example.database.schema.Keys;
//import com.example.database.schema.Public;
//import com.example.cs195tennis.model.Record.AtpRankRecord;
//import com.example.cs195tennis.model.Organization.Player;
//import org.jooq.Record;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.TableImpl;
//
//import java.util.List;
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class AtpRank extends TableImpl<AtpRankRecord> {
//    private static final long serialVersionUID = -1401275800;
//
//    public static final AtpRank ATP_RANK = new AtpRank();
//
//    @Override
//    public Class<AtpRankRecord> getRecordType() {
//        return AtpRankRecord.class;
//    }
//
//    public final TableField<AtpRankRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), ATP_RANK, "");
//
//    public final TableField<AtpRankRecord, Integer> RANKING_DATE = createField(DSL.name("ranking_date"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    public final TableField<AtpRankRecord, Integer> PLAYER_ID = createField(DSL.name("player_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    public final TableField<AtpRankRecord, Integer> RANKING = createField(DSL.name("rank"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    public final TableField<AtpRankRecord, Integer> POINTS = createField(DSL.name("points"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
//
//    public AtpRank() {
//        this(DSL.name("AtpRank"), null);
//    }
//
//    public AtpRank(Name alias) {
//        this(alias, ATP_RANK);
//    }
//
//    private AtpRank(Name alias, Table<AtpRankRecord> aliased) {
//        this(alias, aliased, null);
//    }
//
//    private AtpRank(Name alias, Table<AtpRankRecord> aliased, Field<?>[] parameters) {
//        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
//    }
//
//    public <O extends Record> AtpRank(Table<O> child, ForeignKey<O, AtpRankRecord> key) {
//        super(child, key, ATP_RANK);
//    }
//
//    @Override
//    public Schema getSchema() {
//        return Public.SCHEMA;
//    }
//
//    @Override
//    public UniqueKey<AtpRankRecord> getPrimaryKey() {
//        return Keys.ATPRANK_PKEY;
//    }
//
//    @Override
//    public List<UniqueKey<AtpRankRecord>> getKeys() {
//        return List.<UniqueKey<AtpRankRecord>>of(Keys.ATPRANK_PKEY);
//    }
//
//    @Override
//    public List<ForeignKey<AtpRankRecord, ?>> getReferences() {
//        return List.<ForeignKey<AtpRankRecord, ?>>of(Keys.ATPRANK__ATP_RANK_PLAYER_ID_FKEY);
//    }
//
//
//    public Player player() {
//        return new Player(this, Keys.ATPRANK__ATP_RANK_PLAYER_ID_FKEY);
//    }
//
//    @Override
//    public AtpRank as(String alias) {
//        return new AtpRank(DSL.name(alias), this);
//    }
//
//    @Override
//    public AtpRank as(Name alias) {
//        return new AtpRank(alias, this);
//    }
//
//    @Override
//    public AtpRank rename(String name) {
//        return new AtpRank(DSL.name(name), null);
//    }
//
//    @Override
//    public AtpRank rename(Name name) {
//        return new AtpRank(name, null);
//    }
//
//    // -------------------------------------------------------------------------
//    // Row5 type methods
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row5<Integer, Integer, Integer, Integer, Integer> fieldsRow() {
//        return (Row5) super.fieldsRow();
//    }
//
//}
//
//
