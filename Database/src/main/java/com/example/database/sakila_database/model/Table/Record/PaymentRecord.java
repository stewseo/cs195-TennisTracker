package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.Payments.Payment;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class PaymentRecord extends CustomRecord<PaymentRecord> {
    protected PaymentRecord(Table<PaymentRecord> table) {
        super(table);
    }

    protected PaymentRecord() {
        super(Payment.PAYMENT);
    }
}
