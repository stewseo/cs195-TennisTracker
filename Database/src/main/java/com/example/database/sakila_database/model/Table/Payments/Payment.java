package com.example.database.sakila_database.model.Table.Payments;

import com.example.database.sakila_database.model.Table.Record.PaymentRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.jooq.impl.DSL.name;

public class Payment extends CustomTable<PaymentRecord> {
    public static final Payment PAYMENT = new Payment();

    @Override
    public Class<PaymentRecord> getRecordType() {
        return PaymentRecord.class;
    }

    public final TableField<PaymentRecord, Long> PAYMENT_ID =
            createField(DSL.name("payment_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");


    public final TableField<PaymentRecord, Long> CUSTOMER_ID =
            createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false), this, "");


    public final TableField<PaymentRecord, Long> STAFF_ID =
            createField(DSL.name("staff_id"), SQLDataType.BIGINT.nullable(false), this, "");


    public final TableField<PaymentRecord, Long> RENTAL_ID =
            createField(DSL.name("rental_id"), SQLDataType.BIGINT.nullable(false), this, "");

    public final TableField<PaymentRecord, BigDecimal> AMOUNT =
            createField(DSL.name("amount"), SQLDataType.NUMERIC(5, 2).nullable(false), this, "");

    public final TableField<PaymentRecord, LocalDateTime> PAYMENT_DATE =
            createField(DSL.name("payment_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    public Payment() {
        super(name("payment"));
    }

    protected Payment(Name name, Schema schema) {
        super(name, schema);
    }
}
