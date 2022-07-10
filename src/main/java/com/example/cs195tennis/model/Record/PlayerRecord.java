//package com.example.cs195tennis.model.Record;
//
//
//import com.example.cs195tennis.model.Organization.Player;
//import org.jooq.*;
//import org.jooq.impl.UpdatableRecordImpl;
//
//@SuppressWarnings({ "all", "unchecked", "rawtypes" })
//public class PlayerRecord extends UpdatableRecordImpl<PlayerRecord> implements Record4<Integer, String, String, String> {
//
//    private static final long serialVersionUID = 1297442421;
//
//
//    public void setId(Integer value) {
//        set(0, value);
//    }
//
//
//    public Integer getId() {
//        return (Integer) get(0);
//    }
//
//
//    public void setFirstName(String value) {
//        set(1, value);
//    }
//
//
//    public String getFirstName() {
//        return (String) get(1);
//    }
//
//
//
//    public void setLastName(String value) {
//        set(2, value);
//    }
//
//
//    public String getLastName() {
//        return (String) get(2);
//    }
//
//    public void setMatchId(String value) {
//        set(3, value);
//    }
//
//
//    public String getMatchId() {
//        return (String) get(3);
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
//    // Record3 type implementation
//    // -------------------------------------------------------------------------
//
//    @Override
//    public Row4<Integer, String, String, String> fieldsRow() {
//        return (Row4) super.fieldsRow();
//    }
//
//    @Override
//    public Row4<Integer, String, String, String> valuesRow() {
//        return (Row4) super.valuesRow();
//    }
//
//    @Override
//    public Field<Integer> field1() {
//        return Player.PLAYER.PLAYER_ID;
//    }
//
//    @Override
//    public Field<String> field2() {
//        return Player.PLAYER.FIRST_NAME;
//    }
//    @Override
//    public Field<String> field3() {return Player.PLAYER.LAST_NAME;
//    }
//    @Override
//    public Field<String> field4() {return Player.PLAYER.COUNTRY_ID;
//    }
//
//    @Override
//    public Integer component1() {
//        return getId();
//    }
//
//    @Override
//    public String component2() {
//        return getFirstName();
//    }
//
//    @Override
//    public String component3() {
//        return getLastName();
//    }
//
//    @Override
//    public String component4() {
//        return getMatchId();
//    }
//
//
//    @Override
//    public Integer value1() {
//        return getId();
//    }
//
//    @Override
//    public String value2() {
//        return getFirstName();
//    }
//
//    @Override
//    public String value3() {
//        return getLastName();
//    }
//
//    @Override
//    public String value4() {
//        return getMatchId();
//    }
//
//    @Override
//    public PlayerRecord value1(Integer value) {
//        setId(value);
//        return this;
//    }
//
//    @Override
//    public PlayerRecord value2(String value) {
//        setFirstName(value);
//        return this;
//    }
//
//    @Override
//    public PlayerRecord value3(String value) {
//        setLastName(value);
//        return this;
//    }
//
//    @Override
//    public PlayerRecord value4(String value) {
//        setLastName(value);
//        return this;
//    }
//
//
//    @Override
//    public PlayerRecord values(Integer value1, String value2, String value3, String value4) {
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
//    /**
//     * Create a detached ArticleRecord
//     */
//    public PlayerRecord() {
//        super(Player.PLAYER);
//    }
//
//    /**
//     * Create a detached, initialised ArticleRecord
//     */
//    public PlayerRecord(Integer id, String title, String description, String match_id) {
//        super(Player.PLAYER);
//        set(0, id);
//        set(1, title);
//        set(2, description);
//        set(3, match_id);
//    }
//
//}
