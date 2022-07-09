package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.FilmActorRecord;
import Database.Schema.Keys;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import java.lang.ref.Reference;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.cs195tennis.VerifyDataIntegrity.VerifyDatabase.ctx;
import static java.lang.System.out;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class FilmActor extends CustomTable<FilmActorRecord> {
    public static final FilmActor FILM_ACTOR = new FilmActor();

    public final TableField<FilmActorRecord, Long> ACTOR_ID = createField(name("ACTOR_ID"), BIGINT);
    public final TableField<FilmActorRecord, Long> FILM_ID = createField(name("FILM_ID"), BIGINT);

    public FilmActor() {
        super(name("FILM_ACTOR"));
    }

    protected FilmActor(Name name, Schema schema) {
        super(name, schema);
    }


    @Override
    public Class<? extends FilmActorRecord> getRecordType() {
        return FilmActorRecord.class;
    }

    private transient Actor _actor;
    private transient Film _film;

}