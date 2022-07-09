package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.ActorRecord;
import Database.Model.SakilaModel.Record.CityRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.SQLDataType;

import static org.jooq.impl.DSL.name;

//=================================================================================================
//                                 Extend CustomTable Type Param CustomRecordName
//=================================================================================================
public class City extends CustomTable<CityRecord> {

    //====================================================================================
    //                                 Constant for Easy Table Access
    //====================================================================================
    public static final City CITY = new City();

    //====================================================================================
    //                                Fields / Column Data Types and Names
    //====================================================================================
    public final TableField<CityRecord, Long> CITY_ID = createField(name("city_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");
    public final TableField<CityRecord, String> CITY_ = createField(name("city"), SQLDataType.VARCHAR(50).nullable(false), this, "");
    public final TableField<CityRecord, Long> COUNTRY_ID = createField(name("country_id"), SQLDataType.BIGINT.nullable(false), this, "");

    //=====================================================================================================
    //                                Default Constructor so that this instance can access the Table
    //====================================================================================================
    public City(){
        super(name("City"));
    }

    //=====================================================================================================
    //                               Use name and schema if schema mapping unavailable
    //====================================================================================================
    protected City(Name name, Schema schema) {
        super(name, schema);
    }

    //=====================================================================================================
    //                             The record type produced by this Table.
    //====================================================================================================
    @Override
    public Class<? extends CityRecord> getRecordType() {
        return CityRecord.class;
    }

}
