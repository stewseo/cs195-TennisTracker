//package com.example.cs195tennis.model.Record;
//
//
//import com.example.cs195tennis.model.Organization.Match;
//import org.jooq.*;
//import org.jooq.impl.UpdatableRecordImpl;
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class MatchRecord extends UpdatableRecordImpl<MatchRecord> implements Record6<Integer,Integer, String, String, Integer, Integer> {
//
//
//    public void setMatchId(Integer value) {
//        set(0, value);
//    }
//
//    public Integer getMatchId() {
//        return (Integer) get(0);
//    }
//
//    public void setPlayerId(Integer value) {
//        set(1, value);
//    }
//
//    public Integer getPlayerId() {
//        return (Integer) get(1);
//    }
//
//    public void setPlayer1(String value) {
//        set(2, value);
//    }
//
//    public String getPlayer1() {
//        return (String) get(2);
//    }
//
//    public void setPlayer2(String value) {set(3, value);}
//
//    public String getPlayer2() {
//        return (String) get(3);
//    }
//
//    public void setMatchNum(Integer value) {
//        set(4, value);
//    }
//
//    public Integer getMatchNum() {return (Integer) get(4);}
//
//    public void setTournamentId(Integer value) {set(5, value);}
//
//    public Integer getTournamentId() {
//        return (Integer) get(5);
//    }
//
//
//    // -------------------------------------------------------------------------
//    // Primary key information
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Record1<Integer> key() {
//        return (Record1) super.key();
//    }
//
//    // -------------------------------------------------------------------------
//    // Record4 type implementation
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row6<Integer,Integer, String, String, Integer, Integer>  fieldsRow() {
//        return (Row6) super.fieldsRow();
//    }
//
//    @Override
//    public Row6<Integer,Integer, String, String, Integer, Integer>  valuesRow() {
//        return (Row6) super.valuesRow();
//    }
//
//    @Override
//    public Field<Integer> field1() {
//        return Match.MATCH.MATCH_ID;
//    }
//
//    @Override
//    public Field<Integer> field2() {
//        return Match.MATCH.PLAYER_ID;
//    }
//
//    @Override
//    public Field<String> field3() {
//        return Match.MATCH.PLAYER_1;
//    }
//
//    @Override
//    public Field<String> field4() {return Match.MATCH.PLAYER_2;}
//
//
//    @Override
//    public Field<Integer> field5() {
//        return Match.MATCH.MATCH_NUM;
//    }
//
//    @Override
//    public Field<Integer> field6() {
//        return Match.MATCH.TOURNAMENT_ID;
//    }
//
//    @Override
//    public Integer component1() {
//        return getMatchId();
//    }
//
//    @Override
//    public Integer component2() {
//        return getPlayerId();
//    }
//
//    @Override
//    public String component3() {
//        return getPlayer1();
//    }
//
//    @Override
//    public String component4() {
//        return getPlayer2();
//    }
//
//    @Override
//    public Integer component5() {
//        return getMatchNum();
//    }
//
//    @Override
//    public Integer component6() {
//        return getTournamentId();
//    }
//
//    @Override
//    public Integer value1() {
//        return getMatchId();
//    }
//
//    @Override
//    public Integer value2() {
//        return getPlayerId();
//    }
//
//    @Override
//    public String value3() {
//        return getPlayer1();
//    }
//
//    @Override
//    public String value4() {
//        return getPlayer2();
//    }
//
//    @Override
//    public Integer value5() {
//        return getMatchNum();
//    }
//    @Override
//    public Integer value6() {
//        return getTournamentId();
//    }
//
//
//
//    @Override
//    public MatchRecord value1(Integer value) {
//        setMatchId(value);
//        return this;
//    }
//
//    @Override
//    public MatchRecord value2(Integer value) {
//        setPlayerId(value);
//        return this;
//    }
//
//    @Override
//    public MatchRecord value3(String value) {
//        setPlayer1(value);
//        return this;
//    }
//
//    @Override
//    public MatchRecord value4(String value) {
//        setPlayer2(value);
//        return this;
//    }
//
//    @Override
//    public MatchRecord value5(Integer value) {
//        setMatchNum(value);
//        return this;
//    }
//    @Override
//    public MatchRecord value6(Integer value) {
//        setTournamentId(value);
//        return this;
//    }
//
//
//    @Override
//    public MatchRecord values(Integer value1, Integer value2, String value3, String value4,Integer value5, Integer value6) {
//        value1(value1);
//        value2(value2);
//        value3(value3);
//        value4(value4);
//        value5(value5);
//        value6(value6);
//        return this;
//    }
//
//    // -------------------------------------------------------------------------
//    // Constructors
//    // -------------------------------------------------------------------------
//
//    /**
//     * Create a detached MatchRecord
//     */
//    public MatchRecord() {
//        super(Match.MATCH);
//    }
//
//    /**
//     * Create a detached, initialised MatchRecord
//     */
//    public MatchRecord(Integer match_id, Integer player_id , String player1, String player2, Integer match_num, Integer tournament_id) {
//        super(Match.MATCH);
//        set(0, match_id);
//        set(1, player_id);
//        set(2, player1);
//        set(3, player2);
//        set(4, match_num);
//        set(5, tournament_id);
//    }
//}
//
