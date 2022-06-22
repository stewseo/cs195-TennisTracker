package com.example.cs195tennis.Dao.Record;

import com.example.cs195tennis.model.GrandSlam;
import org.jooq.*;
import org.jooq.impl.UpdatableRecordImpl;


@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GrandSlamRecord extends UpdatableRecordImpl<GrandSlamRecord> implements Record9<Integer, String, Integer, String, String, String, Integer,  Integer, Integer> {

        private static final long serialVersionUID = -2120822720;


        public void setId(Integer value) {
            set(0, value);
        }
        public Integer getId() {
            return (Integer) get(0);
        }

        public void setSlam(String value) {
        set(1, value);
        }
        public String getSlam() {return (String) get(1);
        }

        public void setMatchNum(Integer value) {set(2, value);}
        public Integer getMatchNum() {
            return (Integer) get(2);
        }


        public void setPlayerOne(String value) {
            set(3, value);
        }
        public String getPlayerOne() {
            return (String) get(3);
        }

        public void setPlayerTwo(String value) {
        set(4, value);
        }
        public String getPlayerTwo() {return (String) get(4);}

        public void setMatchid(String value) {
            set(5, value);
        }

        public String getMatchid() {
            return (String) get(5);
        }

        public void setYear(Integer value) {
            set(6, value);
        }

    public Integer getYear() {
        return (Integer) get(6);
    }

    public void setPlayerOneId(Integer value) {
        set(7, value);
    }

    public Integer getPlayerOneId() {
        return (Integer) get(7);
    }

    public void setPlayerTwoId(Integer value) {
        set(8, value);
    }

    public Integer getPlayerTwoId() {
        return (Integer) get(8);
    }


    // ------------------------------------------------------------------
    // -------
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
        public Row9<Integer,String,Integer,String, String, String,Integer,Integer,Integer> fieldsRow() {
            return (Row9) super.fieldsRow();
        }

        @Override
        public Row9<Integer,String, Integer,String, String, String,Integer,Integer,Integer> valuesRow() {
            return (Row9) super.valuesRow();
        }

        @Override
        public Field<Integer> field1() {
            return GrandSlam.GRANDSLAM.ID;
        }

        @Override
        public Field<String> field2() {
            return GrandSlam.GRANDSLAM.NAME;
        }

        @Override
        public Field<Integer> field3() {
            return GrandSlam.GRANDSLAM.MATCH_NUM;
        }

        @Override
        public Field<String> field4() {
            return GrandSlam.GRANDSLAM.PLAYER_ONE;
        }

        @Override
        public Field<String> field5() {
        return GrandSlam.GRANDSLAM.PLAYER_TWO;
    }

        @Override
        public Field<String> field6() {
        return GrandSlam.GRANDSLAM.MATCH_ID;
    }

        @Override
        public Field<Integer> field7() {
        return GrandSlam.GRANDSLAM.YEAR;
    }

        @Override
        public Field<Integer> field8() {
        return GrandSlam.GRANDSLAM.P1ID;
    }

        @Override
        public Field<Integer> field9() {
        return GrandSlam.GRANDSLAM.P2ID;
    }


        @Override
        public Integer component1() {
            return getId();
        }

        @Override
        public String component2() {
            return getSlam();
        }

        @Override
        public Integer component3() {
            return getMatchNum();
        }

        @Override
        public String component4() {
            return getPlayerOne();
        }

        @Override
        public String component5() {
        return getPlayerTwo();
    }

        @Override
         public String component6() {return getMatchid();}

        @Override
        public Integer component7() {
        return getYear();
    }

        @Override
        public Integer component8() {
        return getPlayerOneId();
    }

        @Override
        public Integer component9() {return getPlayerTwoId();
        }


        @Override
        public Integer value1() {
            return getId();
        }

        @Override
        public String value2() {
            return getSlam();
        }

        @Override
        public Integer value3() {
            return getMatchNum();
        }

        @Override
        public String value4() {
            return getPlayerOne();
        }
        @Override
        public String value5() {return getPlayerTwo();
     }
    @Override
    public String value6() {
        return getMatchid();
    }
    @Override
    public Integer value7() {
        return getYear();
    }
    @Override
    public Integer value8() {return getPlayerOneId();
    }
    @Override
    public Integer value9() {return getPlayerTwoId();
    }


        @Override
        public GrandSlamRecord value1(Integer value) {
            setId(value);
            return this;
        }

        @Override
        public GrandSlamRecord value2(String value) {
            setSlam(value);
            return this;
        }

        @Override
        public GrandSlamRecord value3(Integer value) {
            setMatchNum(value);
            return this;
        }

        @Override
        public GrandSlamRecord value4(String value) {
            setPlayerOne(value);
            return this;
        }
        @Override
        public GrandSlamRecord value5(String value) {
        setPlayerTwo(value);
        return this;
        }

        @Override
        public GrandSlamRecord value6(String value) {
            setMatchid(value);
            return this;
        }

        @Override
        public GrandSlamRecord value7(Integer value){
                setYear(value);
                return this;
        }
        @Override
        public GrandSlamRecord value8(Integer value){
            setPlayerOneId(value);
            return this;
        }

        @Override
        public GrandSlamRecord value9(Integer value){
         setPlayerTwoId(value);
        return this;
        }


        @Override
        public GrandSlamRecord values(Integer value1, String value2, Integer value3, String value4, String value5, String value6, Integer value7, Integer value8, Integer value9) {
            value1(value1);
            value2(value2);
            value3(value3);
            value4(value4);
            value5(value5);
            value6(value6);
            value7(value7);
            value8(value8);
            value9(value9);
            return this;
        }

        // -------------------------------------------------------------------------
        // Constructors
        // -------------------------------------------------------------------------


        public GrandSlamRecord() {
            super(GrandSlam.GRANDSLAM);
        }


        public GrandSlamRecord(Integer id, String slam, Integer match_num, String player1, String player2, String matchId, Integer year, Integer pi1d, Integer p2id) {
            super(GrandSlam.GRANDSLAM);
            set(0, id);
            set(1, slam);
            set(2, match_num);
            set(3, player1);
            set(4, player2);
            set(5, matchId);
            set(6, year);
            set(7, pi1d);
            set(8, p2id);

        }
    }

