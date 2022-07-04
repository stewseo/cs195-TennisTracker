package com.example.cs195tennis.Data.Record;

import com.example.cs195tennis.model.Enum.TournamentLevel;
import com.example.cs195tennis.model.Tournament;
import org.jooq.*;
import org.jooq.impl.UpdatableRecordImpl;


@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TournamentRecord extends UpdatableRecordImpl<TournamentRecord> implements Record5<Integer, String, Integer, TournamentLevel, Integer> {


        private static final long serialVersionUID = -2120822720;


        public void setTournamentId(Integer value) {
            set(0, value);
        }
        public Integer getTouranmentId() {
            return (Integer) get(0);
        }

        public void setTournamentName(String value) {
        set(1, value);
        }
        public String getTournamentName() {return (String) get(1);
        }

        public void setCourtId(Integer value) {set(2, value);}
        public Integer getCourtId() {
            return (Integer) get(2);
        }


        public void setTournamentLevel(TournamentLevel value) {
            set(3, value);
        }
        public TournamentLevel getTournamentLevel() {
            return (TournamentLevel) get(3);
        }

        public void setTournamentDate(Integer value) {
        set(4, value);
        }
        public Integer getTournmentDate() {return (Integer) get(4);}


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
        public Row5<Integer,String,Integer,TournamentLevel, Integer> fieldsRow() {
            return (Row5) super.fieldsRow();
        }

        @Override
        public Row5<Integer,String, Integer,TournamentLevel, Integer> valuesRow() {
            return (Row5) super.valuesRow();
        }

        @Override
        public Field<Integer> field1() {
            return Tournament.TOURNAMENT.TOURNAMENT_ID;
        }

        @Override
        public Field<String> field2() {
            return Tournament.TOURNAMENT.TOURNAMENT_NAME;
        }

        @Override
        public Field<Integer> field3() {
            return Tournament.TOURNAMENT.COURT_ID;
        }

        @Override
        public Field<TournamentLevel> field4() {
            return Tournament.TOURNAMENT.TOURNAMENT_LEVEL;
        }

        @Override
        public Field<Integer> field5() {
        return Tournament.TOURNAMENT.DATE;
    }



        @Override
        public Integer component1() {
            return getTouranmentId();
        }

        @Override
        public String component2() {
            return getTournamentName();
        }

        @Override
        public Integer component3() {
            return getCourtId();
        }

        @Override
        public TournamentLevel component4() {
            return getTournamentLevel();
        }

        @Override
        public Integer component5() {
        return getTournmentDate();
    }


        @Override
        public Integer value1() {
            return getTouranmentId();
        }

        @Override
        public String value2() {
            return getTournamentName();
        }

        @Override
        public Integer value3() {
            return getCourtId();
        }

        @Override
        public TournamentLevel value4() {
            return getTournamentLevel();
        }
        @Override
        public Integer value5() {return getTournmentDate();
     }


        @Override
        public TournamentRecord value1(Integer value) {
            setTournamentId(value);
            return this;
        }

        @Override
        public TournamentRecord value2(String value) {
            setTournamentName(value);
            return this;
        }

        @Override
        public TournamentRecord value3(Integer value) {
            setCourtId(value);
            return this;
        }

        @Override
        public TournamentRecord value4(TournamentLevel value) {
            setTournamentLevel(value);
            return this;
        }
        @Override
        public TournamentRecord value5(Integer value) {
        setTournamentDate(value);
        return this;
        }


        @Override
        public TournamentRecord values(Integer tid, String name, Integer match_num, TournamentLevel tlevel, Integer date)  {
            value1(tid);
            value2(name);
            value3(match_num);
            value4(tlevel);
            value5(date);
            return this;
        }

        // -------------------------------------------------------------------------
        // Constructors
        // -------------------------------------------------------------------------


        public TournamentRecord() {
            super(Tournament.TOURNAMENT);
        }


        public TournamentRecord(Integer tid, String name, Integer match_num, TournamentLevel tlevel, Integer date) {
            super(Tournament.TOURNAMENT);
            set(0, tid);
            set(1, name);
            set(2, match_num);
            set(3, tlevel);
            set(4, date);
        }
    }

