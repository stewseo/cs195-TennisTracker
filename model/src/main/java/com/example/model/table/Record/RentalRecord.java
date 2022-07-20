package com.example.model.table.Record;

import com.example.model.table.Rental;
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
