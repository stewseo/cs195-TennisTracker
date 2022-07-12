package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.Rental;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class RentalRecord extends CustomRecord<RentalRecord> {
    protected RentalRecord(Table<RentalRecord> table) {
        super(table);
    }

    protected RentalRecord() {
        super(Rental.RENTAL);
    }
}
