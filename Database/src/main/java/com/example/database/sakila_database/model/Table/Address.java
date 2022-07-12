package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.model.Table.Record.AddressRecord;
import org.jooq.*;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.LocalDateTime;
import java.util.List;

import static org.jooq.impl.DSL.name;

public class Address extends CustomTable<AddressRecord> {
    public static final Address ADDRESS = new Address(name("Address"));
    public final TableField<AddressRecord, Long> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    public final TableField<AddressRecord, String> ADDRESS_ = createField(DSL.name("address"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    public final TableField<AddressRecord, String> ADDRESS2 = createField(DSL.name("address2"), SQLDataType.VARCHAR(50), this, "");

    public final TableField<AddressRecord, String> DISTRICT = createField(DSL.name("district"), SQLDataType.VARCHAR(20).nullable(false), this, "");
    public final TableField<AddressRecord, Long> CITY_ID = createField(DSL.name("city_id"), SQLDataType.BIGINT.nullable(false), this, "");

    public final TableField<AddressRecord, String> POSTAL_CODE = createField(DSL.name("postal_code"), SQLDataType.VARCHAR(10), this, "");

    public final TableField<AddressRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    public final TableField<AddressRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    public Address(Customer customer, ForeignKey<?,?> customer__customer_address_id_fkey) {
        super((Name) customer, (Schema) customer__customer_address_id_fkey);
    }


    @Override
    public Class<? extends AddressRecord> getRecordType() {
        return AddressRecord.class;
    }


    protected Address(Name name) {
        super(name);
    }

//    protected Address(Name name, Schema schema) {
//        super(name, schema);
//    }

    public Identity<AddressRecord, ?> getIdentity() {
        return super.getIdentity();
    }

    @Override
    public UniqueKey<AddressRecord> getPrimaryKey() {
        return super.getPrimaryKey();
    }

    @Override
    public List<UniqueKey<AddressRecord>> getUniqueKeys() {
        return super.getUniqueKeys();
    }

}
