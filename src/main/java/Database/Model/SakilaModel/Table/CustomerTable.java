package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.CustomerRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class CustomerTable extends CustomTable<CustomerRecord> {
    public static final CustomerTable CUSTOMER = new CustomerTable();

    public final TableField<CustomerRecord, String> CUSTOMER_ID = createField(name("customer_id"), VARCHAR);
    public final TableField<CustomerRecord, String> FIRST_NAME  = createField(name("first_name"), VARCHAR);
    public final TableField<CustomerRecord, String> LAST_NAME  = createField(name("last_name"), VARCHAR);

    public CustomerTable(){
        super(name("Customer"));
    }


    protected CustomerTable(Name name) {
        super(name);
    }

    protected CustomerTable(Name name, Schema schema) {
        super(name, schema);
    }

    @Override
    public Class<? extends CustomerRecord> getRecordType() {
        return null;
    }
}

