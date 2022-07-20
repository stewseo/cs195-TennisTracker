package com.example.model.table.Record;

import com.example.model.table.Payments.Payment;
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
