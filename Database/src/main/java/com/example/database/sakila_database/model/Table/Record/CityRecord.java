package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.City;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CityRecord extends CustomRecord<CityRecord> {
    protected CityRecord(Table<CityRecord> table) {
        super(table);
    }

    protected CityRecord() {
        super(City.CITY);
    }

}
