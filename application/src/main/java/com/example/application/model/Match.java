//package com.example.cs195tennis.model.Organization;
//
//import com.example.database.schema.Keys;
//import com.example.database.schema.Public;
//import com.example.cs195tennis.model.Record.MatchRecord;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.SQLDataType;
//import org.jooq.impl.TableImpl;
//
//import java.lang.Record;
//import java.util.Arrays;
//import java.util.List;
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class Match extends TableImpl<MatchRecord> {
//
//
//    private static final long serialVersionUID = -1401275800;
//
//    public static final Match MATCH = new Match();
//
//    @Override
//    public Class<MatchRecord> getRecordType() {
//        return MatchRecord.class;
//    }
//
//    public final TableField<MatchRecord, Integer> MATCH_ID = createField(DSL.name("match_id"), SQLDataType.INTEGER.identity(true).nullable(false), MATCH, "");
//    public final TableField<MatchRecord, String> PLAYER_1 = createField(DSL.name("player1"), SQLDataType.VARCHAR(64).nullable(false), this, "");
//    public final TableField<MatchRecord, String> PLAYER_2 = createField(DSL.name("player2"), SQLDataType.VARCHAR(64).nullable(false), this, "");
//    public final TableField<MatchRecord, Integer> MATCH_NUM = createField(DSL.name("match_num"), SQLDataType.INTEGER.nullable(false), this, "");
//    public final TableField<MatchRecord, Integer> PLAYER_ID = createField(DSL.name("player_id"), SQLDataType.INTEGER.nullable(false), this, "");
//    public final TableField<MatchRecord, Integer> TOURNAMENT_ID = createField(DSL.name("tournament_id"), SQLDataType.INTEGER.nullable(false), this, "");
//
//    public Match() {
//        this(DSL.name("Match"), null);
//    }
//
//    public Match(Name alias) {
//        this(alias, MATCH);
//    }
//
//    public Match(String alias) {this(DSL.name(alias), MATCH);}
//    private Match(Name alias, Table<MatchRecord> aliased) {
//        this(alias, aliased, null);
//    }
//
//
//    private Match(Name alias, Table<MatchRecord> aliased, Field<?>[] parameters) {
//        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());}
//
//    public <O extends Record & org.jooq.Record> Match(Table<O> child, ForeignKey<O, MatchRecord> key) {
//        super(child, key, MATCH);}
//
//    @Override
//    public Schema getSchema() {
//        return Public.SCHEMA;
//    }
//
//    @Override
//    public UniqueKey<MatchRecord> getPrimaryKey() {
//        return Keys.MATCH_PKEY;
//    }
//
//    @Override
//    public List<UniqueKey<MatchRecord>> getKeys() {
//        return Arrays.<UniqueKey<MatchRecord>>asList(Keys.MATCH_PKEY);
//    }
//
//    @Override
//    public List<ForeignKey<MatchRecord, ?>> getReferences() {
//        System.out.println("get references in match");
//        return Arrays.<ForeignKey<MatchRecord, ?>>asList(Keys.MATCH__MATCH_ATP_PLAYER_ID_FKEY, Keys.MATCH__MATCH_GRAND_SLAM_TOURNAMENT_ID_FKEY);
//    }
//
//    private transient Player _atpPlayer;
//    private transient Tournament _grandSlamTournament;
//
//    public Player atpPlayer() {
//        if (_atpPlayer == null)
//            _atpPlayer = new Player(this, Keys.MATCH__MATCH_ATP_PLAYER_ID_FKEY);
//
//        return _atpPlayer;
//    }
//
//
//    public Tournament grandSlamTournament() {
//        if (_grandSlamTournament == null)
//            _grandSlamTournament = new Tournament(this, Keys.MATCH__MATCH_GRAND_SLAM_TOURNAMENT_ID_FKEY);
//
//        return _grandSlamTournament;
//    }
//
//    @Override
//    public Match as(String alias) {
//        return new Match(DSL.name(alias), this);
//    }
//
//    @Override
//    public Match as(Name alias) {
//        return new Match(alias, this);
//    }
//
//
//    @Override
//    public Match rename(String name) {
//        return new Match(DSL.name(name), null);
//    }
//
//
//    @Override
//    public Match rename(Name name) {
//        return new Match(name, null);
//    }
//
//
//    @Override
//    public Row6<Integer,Integer, String, String, Integer, Integer> fieldsRow() {
//        return (Row6) super.fieldsRow();
//    }
//
////
////    public <U> SelectField<U> mapping(Function5<? super String, ? super String, ? super String,? super Integer,? super String, ? extends U> from) {
////        return  convert(Records.mapping(from));
////    }
////
////    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super Integer, ? super Integer, ? super Integer,? super String,? super String,? super String, ? extends U> from) {
////        return convertFrom(toType, Records.mapping(from));
////    }
//}
