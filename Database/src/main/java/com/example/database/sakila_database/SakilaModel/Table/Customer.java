package com.example.database.sakila_database.SakilaModel.Table;

import com.example.database.db_connection.Connect;
import com.example.database.sakila_database.SakilaModel.Record.CustomerRecord;
import org.jooq.*;
import org.jooq.impl.CustomTable;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.*;

public class Customer extends CustomTable<CustomerRecord> {
    public static final Customer CUSTOMER = new Customer();

    //====================================================================================
    //                                PK customer_id
    //                                KEY idx_fk_store_id (store_id)
    //                                KEY idx_fk_address_id (address_id)
    //                                KEY idx_last_name (last_name)
    //====================================================================================
    public final TableField<CustomerRecord, Long> CUSTOMER_ID = createField(name("customer_id"), BIGINT);
    public final TableField<CustomerRecord, String> FIRST_NAME  = createField(name("first_name"), VARCHAR);
    public final TableField<CustomerRecord, String> LAST_NAME  = createField(name("last_name"), VARCHAR);
    public final TableField<CustomerRecord, String> EMAIL  = createField(name("email"), VARCHAR);
    public final TableField<CustomerRecord, Long> ADDRESS_ID  = createField(name("address_id"), BIGINT);
    public final TableField<CustomerRecord, Long> STORE_ID  = createField(name("store_id"), BIGINT);
    public final TableField<CustomerRecord, Boolean> ACTIVE  = createField(name("active"), BOOLEAN);
    public final TableField<CustomerRecord, Date> CREATE_DATE  = createField(name("create_date"), DATE);
    public final TableField<CustomerRecord, Timestamp> LAST_UPDATE  = createField(name("last_update"), TIMESTAMP);

    public Customer(){
        super(name("Customer"));
    }

    protected Customer(Name name) {
        super(name);
    }

    @Override
    public Class<? extends CustomerRecord> getRecordType() {
        return CustomerRecord.class;
    }

    public Identity<?, ?> getCustomerIdentity() {
        return getCustomerTable().getIdentity();
    }

    public List<Index> getCustomerIndex() {
        return getCustomerTable().getIndexes();
    }
    public UniqueKey<?> getCustomerPrimaryKey() {return getCustomerTable().getPrimaryKey();}

    public List<? extends UniqueKey<?>> getCustomerUniqueKeys() {return getCustomerTable().getUniqueKeys();}

    public List<? extends ForeignKey<?, ?>> getCustomerReferences() {
        return getCustomerTable().getReferences();
    }

    public Table<?> getCustomerTable() {
        Connect connect = new Connect();
        Table<?> table = connect.create()
                .meta()
                .getSchemas("sakila")
                .get(0)
                .getTable("customer");
        return table;
    }


}

