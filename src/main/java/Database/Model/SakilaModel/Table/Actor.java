package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.ActorRecord;
import com.example.cs195tennis.model.Record.AtpRankRecord;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class Actor extends CustomTable<ActorRecord> {
    public static final Actor ACTOR = new Actor();

    public final TableField<ActorRecord, Long> ACTOR_ID = createField(name("ACTOR_ID"), BIGINT);

    public final TableField<ActorRecord, String> FIRST_NAME  = createField(name("FIRST_NAME"), VARCHAR);
    public final TableField<ActorRecord, String> LAST_NAME  = createField(name("LAST_NAME"), VARCHAR);

    public Actor(){
        super(name("ACTOR"));
    }

    protected Actor(Name name, Schema schema) {
        super(name, schema);
    }

    @Override
    public Class<? extends ActorRecord> getRecordType() {
        return ActorRecord.class;
    }
}
