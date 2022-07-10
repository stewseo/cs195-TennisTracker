package com.example.database.sakila_database.SakilaModel.Record;

import com.example.database.sakila_database.SakilaModel.Table.Customer;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CustomerRecord extends CustomRecord<CustomerRecord> {
    protected CustomerRecord(Table<CustomerRecord> table) {
        super(table);
    }

    protected CustomerRecord() {
        super(Customer.CUSTOMER);
    }

}
