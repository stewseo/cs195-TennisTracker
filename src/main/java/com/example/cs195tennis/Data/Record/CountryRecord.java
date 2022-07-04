package com.example.cs195tennis.Data.Record;

import com.example.cs195tennis.model.Country;
import com.example.cs195tennis.model.Game;
import org.jooq.*;
import org.jooq.impl.UpdatableRecordImpl;

import static com.example.cs195tennis.model.Country.COUNTRY;
import static com.example.cs195tennis.model.Game.GAME;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CountryRecord extends UpdatableRecordImpl<CountryRecord> implements Record2<Integer, String> {

    public void setCountryId(Integer value) {
        set(0, value);
    }
    public Integer getCountryId() {
        return (Integer) get(0);
    }

    public void setCountry(String value) {
        set(1, value);
    }
    public String getCountry() {
        return (String) get(1);
    }

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }


    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {return COUNTRY.COUNTRY_ID;}
    @Override
    public Field<String> field2() {
        return COUNTRY.COUNTRY_NAME;
    }

    @Override
    public Integer value1() {
        return getCountryId();
    }

    @Override
    public String value2() {
        return getCountry();
    }


    @Override
    public CountryRecord value1(Integer value) {
        setCountryId(value);
        return this;
    }

    @Override
    public CountryRecord value2(String value) {
        setCountry(value);
        return this;
    }


    @Override
    public CountryRecord values(Integer integer, String s) {
        value1(integer);
        value2(s);
        return this;
    }


    @Override
    public Integer component1() {
        return getCountryId();
    }


    @Override
    public String component2() {
        return getCountry();
    }


    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public CountryRecord() {
        super(COUNTRY);
    }

    public CountryRecord(Integer cound, String country) {
        super(Country.COUNTRY);
        set(0, cound);
        set(1, country);

    }

}
