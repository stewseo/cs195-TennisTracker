//package com.example.cs195tennis.model.Record;
//
//
//import com.example.cs195tennis.model.Organization.WtaRank;
//
//import org.jooq.*;
//import org.jooq.impl.UpdatableRecordImpl;
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class WtaRankRecord extends UpdatableRecordImpl<WtaRankRecord> implements Record5<Integer, Integer, Integer,Integer, Integer> {
//
//    private static final long serialVersionUID = 1297442421;
//
//    /**
//     * Setter for <code>public.article.id</code>.
//     */
//    public void setId(Integer value) {
//        set(0, value);
//    }
//    public Integer getId() {
//        return (Integer) get(0);
//    }
//
//    public void setRankDate(Integer value) {
//        set(1, value);
//    }
//    public Integer getRankDate() {
//        return (Integer) get(1);
//    }
//
//
//    public void setRankPlayer(Integer value) {
//        set(2, value);
//    }
//    public Integer getRankPlayer() {
//        return (Integer) get(2);
//    }
//
//    public void setRanking(Integer value) {
//        set(3, value);
//    }
//    public Integer getRanking() {
//        return (Integer) get(3);
//    }
//
//
//    public void setRankPoints(Integer value) {
//        set(4, value);
//    }
//    public Integer getRankPoints() {
//        return (Integer) get(4);
//    }
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
//    // Record5 type implementation
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row5<Integer, Integer, Integer,Integer, Integer> fieldsRow() {
//        return (Row5) super.fieldsRow();
//    }
//
//    @Override
//    public Row5<Integer, Integer, Integer,Integer, Integer> valuesRow() {
//        return (Row5) super.valuesRow();
//    }
//
//    @Override
//    public Field<Integer> field1() {
//        return WtaRank.WTA_RANK.ID;
//    }
//
//    @Override
//    public Field<Integer> field2() {
//        return WtaRank.WTA_RANK.RANKING_DATE;
//    }
//
//    @Override
//    public Field<Integer> field3() {return WtaRank.WTA_RANK.PLAYER_ID;
//    }
//    @Override
//    public Field<Integer> field4() {return WtaRank.WTA_RANK.RANKING;
//    }
//    @Override
//    public Field<Integer> field5() {return WtaRank.WTA_RANK.POINTS;
//    }
//
//    @Override
//    public Integer component1() {
//        return getId();
//    }
//
//    @Override
//    public Integer component2() {
//        return getRankDate();
//    }
//
//    @Override
//    public Integer component3() {
//        return getRankPlayer();
//    }
//
//    @Override
//    public Integer component4() {
//        return getRanking();
//    }
//
//    @Override
//    public Integer component5() {
//        return getRankPoints();
//    }
//
//    @Override
//    public Integer value1() {
//        return getId();
//    }
//
//    @Override
//    public Integer value2() {
//        return getRankDate();
//    }
//
//    @Override
//    public Integer value3() {
//        return getRankPlayer();
//    }
//
//    @Override
//    public Integer value4() {
//        return getRanking();
//    }
//
//    @Override
//    public Integer value5() {
//        return getRankPoints();
//    }
//
//    @Override
//    public WtaRankRecord value1(Integer value) {
//        setId(value);
//        return this;
//    }
//
//    @Override
//    public WtaRankRecord value2(Integer value) {
//        setRanking(value);
//        return this;
//    }
//
//    @Override
//    public WtaRankRecord value3(Integer value) {
//        setRankPlayer(value);
//        return this;
//    }
//
//
//    @Override
//    public WtaRankRecord value4(Integer value) {
//        setRanking(value);
//        return this;
//    }
//
//    @Override
//    public WtaRankRecord value5(Integer value) {
//        setRankPoints(value);
//        return this;
//    }
//
//    @Override
//    public WtaRankRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Integer value5) {
//        value1(value1);
//        value2(value2);
//        value3(value3);
//        value3(value4);
//        value3(value5);
//        return this;
//    }
//
//    // -------------------------------------------------------------------------
//    // Constructors
//    // -------------------------------------------------------------------------
//
//    /**
//     * Create a detached ArticleRecord
//     */
//    public WtaRankRecord() {
//        super(WtaRank.WTA_RANK);
//    }
//
//    /**
//     * Create a detached, initialised ArticleRecord
//     */
//    public WtaRankRecord(Integer id, Integer ranking_date, Integer player, Integer rank, Integer points) {
//        super(WtaRank.WTA_RANK);
//        set(0, id);
//        set(1, ranking_date);
//        set(1, player);
//        set(2, rank);
//        set(3, points);
//    }
//
//}
//
