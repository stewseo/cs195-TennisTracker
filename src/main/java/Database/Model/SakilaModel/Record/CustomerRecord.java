package Database.Model.SakilaModel.Record;

import Database.Model.SakilaModel.Table.CustomerTable;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CustomerRecord extends CustomRecord<CustomerRecord> {
    protected CustomerRecord(Table<CustomerRecord> table) {
        super(table);
    }

    protected CustomerRecord() {
        super(CustomerTable.CUSTOMER);
    }
}