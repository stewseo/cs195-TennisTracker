package com.example.database.sakila_database.SakilaModel.Record;

import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CountryRecord extends CustomRecord<CountryRecord> {
    protected CountryRecord(Table<CountryRecord> table) {
        super(table);
    }
}
