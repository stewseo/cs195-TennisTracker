package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.ActorRecord;
import Database.Model.SakilaModel.Record.FilmActorRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import java.math.BigInteger;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class FilmActorTable extends CustomTable<FilmActorRecord> {
    public static final FilmActorTable FILM_ACTOR = new FilmActorTable();

    public final TableField<FilmActorRecord, Long> ACTOR_ID = createField(name("ACTOR_ID"), BIGINT);
    public final TableField<FilmActorRecord, Long> FILM_ID  = createField(name("FILM_ID"), BIGINT);

    public FilmActorTable(){
        super(name("FILM_ACTOR"));
    }

    protected FilmActorTable(Name name, Schema schema) {
        super(name, schema);
    }


    @Override
    public Class<? extends FilmActorRecord> getRecordType() {
        return FilmActorRecord.class;
    }
}