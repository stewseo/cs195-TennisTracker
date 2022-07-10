//package com.example.cs195tennis.model.Location;
//
//import com.example.database.schema.Keys;
//import com.example.cs195tennis.model.Record.CountryRecord;
//import org.jooq.Record;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.SQLDataType;
//import org.jooq.impl.TableImpl;
//
//public class Country extends TableImpl<CountryRecord> {
//
//    public static final Country COUNTRY = new Country();
//
//    public final TableField<CountryRecord, Integer> COUNTRY_ID = createField(DSL.name("country_id"), SQLDataType.INTEGER, COUNTRY, "");
//
//    public final TableField<CountryRecord, String> COUNTRY_NAME = createField(DSL.name("game_number"), SQLDataType.VARCHAR(50), this, "");
//
//    public Country(){
//        this(DSL.name("Country"), null);
//    }
//    private Country(Name alias, Table<CountryRecord> aliased) {
//        this(alias, aliased, null);
//    }
//
//    private Country(Name alias, Table<CountryRecord> aliased, Field<?>[] parameters) {
//        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
//    }
//
//    public <O extends Record> Country(Table<O> child, ForeignKey<O, CountryRecord> key) {
//        super(child, key, COUNTRY);
//    }
//
//    @Override
//    public Country as(String alias) {
//        return new Country(DSL.name(alias), this);
//    }
//
//    @Override
//    public Country as(Name alias) {
//        return new Country(alias, this);
//    }
//
//    @Override
//    public UniqueKey<CountryRecord> getPrimaryKey() {
//        return Keys.COUNTRY_KEY;
//    }
//
//
//    @Override
//    public Country rename(String name) {
//        return new Country(DSL.name(name), null);
//    }
//
//    @Override
//    public Country rename(Name name) {
//        return new Country(name, null);
//    }
//}
