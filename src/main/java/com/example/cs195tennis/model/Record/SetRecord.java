//package com.example.cs195tennis.model.Record;
//import org.jooq.*;
//import org.jooq.impl.UpdatableRecordImpl;
//
//import static com.example.cs195tennis.model.Tables.SET;
//
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class SetRecord extends UpdatableRecordImpl<SetRecord> implements Record4<Integer, Integer, Integer, Integer> {
//
//
//    public void setSetId(Integer value) {
//        set(0, value);
//    }
//
//    public Integer getSetId() {
//        return (Integer) get(0);
//    }
//
//    public void setSetNum(Integer value) {
//        set(1, value);
//    }
//
//    public Integer getSetNum() {
//        return (Integer) get(1);
//    }
//
//    public void setPointNum(Integer value) {
//        set(2, value);
//    }
//    public Integer getPointNum() {
//        return (Integer) get(2);
//    }
//
//    public void setMatchId(Integer value) {
//        set(3, value);
//    }
//
//    public Integer getMatchId() {
//        return (Integer) get(3);
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
//    public Row4<Integer, Integer, Integer,Integer> fieldsRow() {
//        return (Row4) super.fieldsRow();
//    }
//
//    @Override
//    public Row4<Integer, Integer, Integer,Integer> valuesRow() {
//        return (Row4) super.valuesRow();
//    }
//
//    @Override
//    public Field<Integer> field1() {
//        return SET.SET_ID;
//    }
//
//    @Override
//    public Field<Integer> field2() {return SET.SET_NUM;}
//
//    @Override
//    public Field<Integer> field3() {return SET.POINT_NUM;}
//
//    @Override
//    public Field<Integer> field4() {
//        return SET.MATCH_ID;
//    }
//
//    @Override
//    public Integer component1() {
//        return getSetId();
//    }
//
//    @Override
//    public Integer component2() {
//        return getSetNum();
//    }
//
//    @Override
//    public Integer component3() {
//        return getPointNum();
//    }
//
//    @Override
//    public Integer component4() {
//        return getMatchId();
//    }
//
//    @Override
//    public Integer value1() {
//        return getSetId();
//    }
//
//    @Override
//    public Integer value2() {
//        return getSetNum();
//    }
//
//    @Override
//    public Integer value3() {
//        return getPointNum();
//    }
//
//    @Override
//    public Integer value4() {
//        return getMatchId();
//    }
//
//    @Override
//    public SetRecord value1(Integer value) {
//        setSetId(value);
//        return this;
//    }
//
//    @Override
//    public SetRecord value2(Integer value) {
//        setSetNum(value);
//        return this;
//    }
//
//    @Override
//    public SetRecord value3(Integer value) {
//        setPointNum(value);
//        return this;
//    }
//
//    @Override
//    public SetRecord value4(Integer value) {
//        setMatchId(value);
//        return this;
//    }
//
//    @Override
//    public SetRecord values(Integer value1, Integer value2, Integer value3, Integer value4) {
//        value1(value1);
//        value2(value2);
//        value3(value3);
//        value4(value4);
//        return this;
//    }
//
//    // -------------------------------------------------------------------------
//    // Constructors
//    // -------------------------------------------------------------------------
//
//    public SetRecord() {
//        super(SET);
//    }
//
//    public SetRecord(Integer set_id, Integer SetNo, Integer pointNum, Integer matchId) {
//        super(SET);
//        set(0, set_id);
//        set(1, SetNo);
//        set(2, pointNum);
//        set(3, matchId);
//    }
//}
//
