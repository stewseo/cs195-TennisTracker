package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.ActorRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class ActorTable extends CustomTable<ActorRecord> {
    public static final ActorTable ACTOR = new ActorTable();

    public final TableField<ActorRecord, Long> ACTOR_ID = createField(name("ACTOR_ID"), BIGINT);

    public final TableField<ActorRecord, String> FIRST_NAME  = createField(name("FIRST_NAME"), VARCHAR);
    public final TableField<ActorRecord, String> LAST_NAME  = createField(name("LAST_NAME"), VARCHAR);

    public ActorTable(){
        super(name("ACTOR"));
    }

    protected ActorTable(Name name, Schema schema) {
        super(name, schema);
    }


    @Override
    public Class<? extends ActorRecord> getRecordType() {
        return ActorRecord.class;
    }
}
