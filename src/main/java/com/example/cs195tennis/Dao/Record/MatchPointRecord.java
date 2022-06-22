package com.example.cs195tennis.Dao.Record;

import com.example.cs195tennis.model.MatchPointByPoint;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MatchPointRecord extends UpdatableRecordImpl<MatchPointRecord> implements Record4<Integer, Integer, Integer, Integer> {


    public void setId(Integer value) {
        set(0, value);
    }

    public Integer getId() {
        return (Integer) get(0);
    }

    public void setSetNo(Integer value) {
        set(1, value);
    }

    public Integer getSetNo() {
        return (Integer) get(1);
    }

    public void setGameNo(Integer value) {
        set(2, value);
    }

    public Integer getGameNo() {
        return (Integer) get(2);
    }


    public void setPointWinner(Integer value) {
        set(3, value);
    }

    public Integer getPointWinner() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, Integer, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return MatchPointByPoint.MATCHPOINT.ID;
    }

    @Override
    public Field<Integer> field2() {
        return MatchPointByPoint.MATCHPOINT.SET_NO;
    }

    @Override
    public Field<Integer> field3() {
        return MatchPointByPoint.MATCHPOINT.GAME_NO;
    }

    @Override
    public Field<Integer> field4() {
        return MatchPointByPoint.MATCHPOINT.P1_SCORE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getSetNo();
    }

    @Override
    public Integer component3() {
        return getGameNo();
    }

    @Override
    public Integer component4() {
        return getPointWinner();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getSetNo();
    }

    @Override
    public Integer value3() {
        return getGameNo();
    }

    @Override
    public Integer value4() {
        return getPointWinner();
    }

    @Override
    public MatchPointRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public MatchPointRecord value2(Integer value) {
        setSetNo(value);
        return this;
    }

    @Override
    public MatchPointRecord value3(Integer value) {
        setGameNo(value);
        return this;
    }

    @Override
    public MatchPointRecord value4(Integer value) {
        setPointWinner(value);
        return this;
    }

    @Override
    public MatchPointRecord values(Integer value1, Integer value2, Integer value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MatchRecord
     */
    public MatchPointRecord() {
        super(MatchPointByPoint.MATCHPOINT);
    }

    /**
     * Create a detached, initialised MatchRecord
     */
    public MatchPointRecord(Integer id, Integer SetNo, Integer GameNo, Integer PointWinner) {
        super(MatchPointByPoint.MATCHPOINT);
        set(0, id);
        set(1, SetNo);
        set(2, GameNo);
        set(3, PointWinner);
    }
}

