package Database.Model.SakilaModel.Record;

import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class AddressRecord extends CustomRecord<AddressRecord> {
    protected AddressRecord(Table<AddressRecord> table) {
        super(table);
    }
}
