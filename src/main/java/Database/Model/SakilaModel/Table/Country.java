package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.CityRecord;
import Database.Model.SakilaModel.Record.CountryRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.LocalDateTime;

import static org.jooq.impl.DSL.name;

public class Country extends CustomTable<CountryRecord> {


        //====================================================================================
        //                           reference to Table 'Sakila'.'Country'
        //====================================================================================
        public static final Country COUNTRY = new Country();

        //====================================================================================
        //                                Field 'country_id', long, identity
        //                                Field 'country', long
        //                                Field 'last_update'
        //====================================================================================
        public final TableField<CountryRecord, Long> COUNTRY_ID = createField(name("country_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");
        public final TableField<CountryRecord, String> COUNTRY_ = createField(DSL.name("country"), SQLDataType.VARCHAR(50).nullable(false), this, "");

        public final TableField<CountryRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

        //=====================================================================================================
        //                                Default Constructor so that this instance can access the Table
        //====================================================================================================
        public Country(){
            super(name("Country"));
        }

        //=====================================================================================================
        //                               Use name and schema if schema mapping unavailable
        //====================================================================================================
        protected Country(Name name, Schema schema) {
            super(name, schema);
        }

        //=====================================================================================================
        //                             The record type produced by this Table.
        //====================================================================================================
        @Override
        public Class<? extends CountryRecord> getRecordType() {
            return CountryRecord.class;
        }
}
