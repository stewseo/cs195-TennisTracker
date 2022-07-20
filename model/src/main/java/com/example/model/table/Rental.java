package com.example.model.table;

import com.example.model.table.Record.RentalRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.LocalDateTime;

import static org.jooq.impl.DSL.name;

public class Rental extends CustomTable<RentalRecord> {
    public static final Rental RENTAL = new Rental();

    @Override
    public Class<RentalRecord> getRecordType() {
        return RentalRecord.class;
    }

    public final TableField<RentalRecord, Long> RENTAL_ID =
            createField(DSL.name("rental_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");


    public final TableField<RentalRecord, LocalDateTime> RENTAL_DATE =
            createField(DSL.name("rental_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");


    public final TableField<RentalRecord, Long> INVENTORY_ID = createField(DSL.name("inventory_id"), SQLDataType.BIGINT.nullable(false), this, "");

    public final TableField<RentalRecord, Long> CUSTOMER_ID = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false), this, "");

    public final TableField<RentalRecord, LocalDateTime> RETURN_DATE = createField(DSL.name("return_date"), SQLDataType.LOCALDATETIME(6), this, "");

    public final TableField<RentalRecord, Long> STAFF_ID = createField(DSL.name("staff_id"), SQLDataType.BIGINT.nullable(false), this, "");


    public final TableField<RentalRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    public Rental() {
        super(name("rental"));
    }

    protected Rental(Name name, Schema schema) {
        super(name, schema);
    }


}
