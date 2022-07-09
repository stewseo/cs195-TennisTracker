package Database.Model.SakilaModel.Record;

import Database.Model.SakilaModel.Table.City;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CityRecord extends CustomRecord<CityRecord> {
    protected CityRecord(Table<CityRecord> table) {
        super(table);
    }

    protected CityRecord() {
        super(City.CITY);
    }

}
