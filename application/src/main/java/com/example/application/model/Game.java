//package com.example.cs195tennis.model.Organization;
//
//import com.example.database.schema.Indexes;
//import com.example.database.schema.Keys;
//import com.example.database.schema.Public;
//import com.example.cs195tennis.model.Record.GameRecord;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.SQLDataType;
//import org.jooq.impl.TableImpl;
//
//import java.lang.Record;
//import java.util.Arrays;
//import java.util.List;
//
//
//public class Game extends TableImpl<GameRecord> {
//
//    private static final long serialVersionUID = 1L;
//
//    public static final Game GAME = new Game();
//
//    @Override
//    public Class<GameRecord> getRecordType() {
//        return GameRecord.class;
//    }
//
//    public final TableField<GameRecord, Integer> GAME_ID = createField(DSL.name("game_id"), SQLDataType.INTEGER, GAME, "");
//
//    public final TableField<GameRecord, Integer> GAME_NUM = createField(DSL.name("game_number"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<GameRecord, Integer> POINT_WINNER = createField(DSL.name("point_winner"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<GameRecord, Integer> P1_SCORE = createField(DSL.name("p1_score"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<GameRecord, Integer> P2_SCORE = createField(DSL.name("p2_score"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<GameRecord, Integer> P1_WINNER = createField(DSL.name("p1_winner"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<GameRecord, Integer> P2_WINNER = createField(DSL.name("p2_winner"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<GameRecord, Integer> POINT_NUMBER = createField(DSL.name("point_number"), SQLDataType.INTEGER, this, "");
//
//    public final TableField<GameRecord, String> BACKUP_KEY = createField(DSL.name("backup_key"), SQLDataType.VARCHAR(65).nullable(false), this, "");
//
//    public final TableField<GameRecord, Integer> SET_ID = createField(DSL.name("set_id"), SQLDataType.INTEGER.nullable(false), this, "");
//
//
//    private Game(Name alias, Table<GameRecord> aliased) {
//        this(alias, aliased, null);
//    }
//
//    private Game(Name alias, Table<GameRecord> aliased, Field<?>[] parameters) {
//        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
//    }
//
//
//    public Game(String alias) {
//        this(DSL.name(alias), GAME);
//    }
//
//
//    public Game(Name alias) {
//        this(alias, GAME);
//    }
//
//    public Game() {
//        this(DSL.name("Game"), null);
//    }
//
//    /**
//     *   public TableImpl(Table<?> child, ForeignKey<?, R> path, Table<R> parent) {
//     *   this(createPathAlias(child, path), null, child, path, parent, null, parent.getCommentPart());
//     *   traverse tree using child as pointer until instanceof TableImpl is not met
//     *     }
//     * @param child
//     * @param key
//     * @param <O> path used to create path alias
//     */
//    public <O extends Record & org.jooq.Record> Game(Table<O> child, ForeignKey<O, GameRecord> key) {
//        super(child, key, GAME);
//    }
//
//    @Override
//    public Schema getSchema() {
//        return aliased() ? null : Public.SCHEMA;
//    }
//
//    @Override
//    public Game as(String alias) {
//        return new Game(DSL.name(alias), this);
//    }
//
//    @Override
//    public Game as(Name alias) {
//        return new Game(alias, this);
//    }
//
//
//    @Override
//    public List<Index> getIndexes() {
//        return Arrays.asList(Indexes.IDX_FK_SET_ID);
//    }
//
//    @Override
//    public Identity<GameRecord, Long> getIdentity() {
//        return (Identity<GameRecord, Long>) super.getIdentity();
//    }
//
//    @Override
//    public UniqueKey<GameRecord> getPrimaryKey() {
//        return Keys.GAME_PKEY;
//    }
//
//    @Override
//    public List<ForeignKey<GameRecord, ?>> getReferences() {
//        return List.of(Keys.GAME__GAME_SET_ID_FKEY);
//    }
//
//    @Override
//    public Game rename(String name) {
//        return new Game(DSL.name(name), null);
//    }
//
//    @Override
//    public Game rename(Name name) {
//        return new Game(name, null);
//    }
//
//
//    // -------------------------------------------------------------------------
//    // Row8 type methods
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,Integer,Integer> fieldsRow() {
//        return (Row10) super.fieldsRow();
//    }
//
////    public <U> SelectField<U> mapping(Function10<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer,? super Integer,? super Integer, ? extends U> from) {
////        return org.jooq.impl.convertFrom(Records.mapping(from));
////    }
////
////
////    public <U> SelectField<U> mapping(Class<U> toType, Function10<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super String, ? super Integer,? super Integer,? super Integer, ? extends U> from) {
////        return convertFrom(toType, Records.mapping(from));
////    }
//
//
//}