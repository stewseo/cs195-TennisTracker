//package com.example.cs195tennis.model.Record;
//
//
//import com.example.cs195tennis.model.Organization.Game;
//import org.jooq.*;
//import org.jooq.impl.UpdatableRecordImpl;
//
//import static com.example.cs195tennis.model.Tables.GAME;
//
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class GameRecord extends UpdatableRecordImpl<GameRecord> implements
//        Record10<Integer, Integer, Integer,Integer, Integer, Integer,Integer, Integer, String,Integer> {
//
//    // -------------------------------------------------------------------------
//    // Setters/Getters for Table Game
//    // -------------------------------------------------------------------------
//    public void setGameId(Integer value) {
//        set(0, value);
//    }
//    public Integer getGameId() {
//        return (Integer) get(0);
//    }
//    public void setGAME_NUM(Integer value) {
//        set(1, value);
//    }
//    public Integer getGAME_NUM() {
//        return (Integer) get(1);
//    }
//    public void setPointWinner(Integer value) {
//        set(2, value);
//    }//oops
//    public Integer getPointWinner() {return (Integer) get(2);}
//    public void setP1Score(Integer value) {set(3, value);}
//    public Integer getP1Score() {return (Integer) get(3);}
//    public void setP2Score(Integer value) {set(4, value);}
//    public Integer getP2Score() {return (Integer) get(4);}
//    public void setP1Winner(Integer value) {
//        set(5, value);
//    }
//    public Integer getP1Winner() {return (Integer) get(5);}
//    public void setP2Winner(Integer value) {set(6, value);}
//    public Integer getP2Winner() {
//        return (Integer) get(6);
//    }
//    public void setPointNumber(Integer value) {
//        set(7, value);
//    }
//    public Integer getPointNumber() {
//        return (Integer) get(7);
//    }
//    public void setBackupKey(String value) {
//        set(8, value);
//    }
//    public String getBackupKey() {
//        return (String) get(8);
//    }
//    public void setSetId(Integer value) {
//        set(9, value);
//    }
//    public Integer getSetId() {
//        return (Integer) get(9);
//    }
//
//
//    // -------------------------------------------------------------------------
//    // Primary key information (Record1)
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Record1<Integer> key() {
//        return (Record1) super.key();
//    }
//
//    // -------------------------------------------------------------------------
//    // Record type implementation
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, String, Integer> fieldsRow() {
//        return (Row10) super.fieldsRow();
//    }
//
//    @Override
//    public Row10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, String, Integer> valuesRow() {
//        return (Row10) super.valuesRow();
//    }
//
//    // -------------------------------------------------------------------------
//    // Fields for Row10 fieldsRow and Row10 valuesRow
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Field<Integer> field1() {
//        return GAME.GAME_ID;
//    }
//    @Override
//    public Field<Integer> field2() {
//        return GAME.GAME_NUM;
//    }
//    @Override
//    public Field<Integer> field3() {
//        return GAME.POINT_WINNER;
//    }
//    @Override
//    public Field<Integer> field4() {
//        return GAME.P1_SCORE;
//    }
//    @Override
//    public Field<Integer> field5() {
//        return GAME.P2_SCORE;
//    }
//    @Override
//    public Field<Integer> field6() {
//        return GAME.P1_WINNER;
//    }
//    @Override
//    public Field<Integer> field7() {
//        return GAME.P2_WINNER;
//    }
//    @Override
//    public Field<Integer> field8() {
//        return GAME.POINT_NUMBER;
//    }
//    @Override
//    public Field<String> field9() {
//        return GAME.BACKUP_KEY;
//    }
//    @Override
//    public Field<Integer> field10() {
//        return GAME.SET_ID;
//    }
//
//    // -------------------------------------------------------------------------
//    // Components for Row10 fieldsRow and Row10 valuesRow
//    // -
//    @Override
//    public Integer component1() {
//        return getGameId();
//    }
//    @Override
//    public Integer component2() {
//        return getGAME_NUM();
//    }
//    @Override
//    public Integer component3() {
//        return getPointWinner();
//    }
//    @Override
//    public Integer component4() {
//        return getP1Score();
//    }
//    @Override
//    public Integer component5() {
//        return getP2Score();
//    }
//    @Override
//    public Integer component6() {
//        return getP1Winner();
//    }
//    @Override
//    public Integer component7() {
//        return getP2Winner();
//    }
//    @Override
//    public Integer component8() {
//        return getPointNumber();
//    }
//    @Override
//    public String component9() {
//        return getBackupKey();
//    }
//    @Override
//    public Integer component10() {
//        return getSetId();
//    }
//
//    @Override
//    public Integer value1() {
//        return getGameId();
//    }
//    @Override
//    public Integer value2() {
//        return getGAME_NUM();
//    }
//    @Override
//    public Integer value3() {
//        return getPointWinner();
//    }
//    @Override
//    public Integer value4() {
//        return getP1Score();
//    }
//    @Override
//    public Integer value5() {
//        return getP2Score();
//    }
//    @Override
//    public Integer value6() {
//        return getP1Winner();
//    }
//    @Override
//    public Integer value7() {
//        return getP2Winner();
//    }
//    @Override
//    public Integer value8() {
//        return getPointNumber();
//    }
//    @Override
//    public String value9() {
//        return getBackupKey();
//    }
//    @Override
//    public Integer value10() {
//        return getSetId();
//    }
//
//    @Override
//    public GameRecord value1(Integer value) {
//        setGameId(value);
//        return this;
//    }
//    @Override
//    public GameRecord value2(Integer value) {
//        setGAME_NUM(value);
//        return this;
//    }
//    @Override
//    public GameRecord value3(Integer value) {
//        setPointWinner(value);
//        return this;
//    }
//    @Override
//    public GameRecord value4(Integer value) {
//        setP1Score(value);
//        return this;
//    }
//    @Override
//    public GameRecord value5(Integer value) {
//        setP2Score(value);
//        return this;
//    }
//    @Override
//    public GameRecord value6(Integer value) {
//        setP1Winner(value);
//        return this;
//    }
//    @Override
//    public GameRecord value7(Integer value) {
//        setP2Winner(value);
//        return this;
//    }
//    @Override
//    public GameRecord value8(Integer value) {
//        setPointNumber(value);
//        return this;
//    }
//    @Override
//    public GameRecord value9(String value) {
//        setBackupKey(value);
//        return this;
//    }
//    @Override
//    public GameRecord value10(Integer value) {
//        setSetId(value);
//        return this;
//    }
//
//
//    // -------------------------------------------------------------------------
//    // @param value passed to value method
//    // -------------------------------------------------------------------------
//    @Override
//    public GameRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Integer value5,
//                             Integer value6, Integer value7, Integer value8, String value9, Integer value10) {
//        value1(value1);
//        value2(value2);
//        value3(value3);
//        value4(value4);
//        value5(value5);
//        value6(value6);
//        value7(value7);
//        value8(value8);
//        value9(value9);
//        value10(value10);
//        return this;
//    }
//
//
//    // -------------------------------------------------------------------------
//    // Constructors
//    // UpdatableRecordImpl -> TableRecordImpl -> AbstractQualifiedRecord -> AbstractRecord
//    // -------------------------------------------------------------------------
//    public GameRecord() {
//        super(Game.GAME);
//    }
//
//    public GameRecord(Integer gameId, Integer gameNum, Integer p1Score, String p2Score, String p1Winner, Integer p2Winner,
//                      Integer getPointNum, Integer serve_stats_id, String rally_stats_id, Integer error_stats_id) {
//        super(Game.GAME);
//        set(0, gameId);
//        set(1, gameNum);
//        set(2, p1Score);
//        set(3, p2Score);
//        set(4, p1Winner);
//        set(5, p2Winner);
//        set(6, getPointNum);
//        set(7, serve_stats_id);
//        set(8, rally_stats_id);
//        set(9, error_stats_id);
//    }
//
//
//}
//
//
//
