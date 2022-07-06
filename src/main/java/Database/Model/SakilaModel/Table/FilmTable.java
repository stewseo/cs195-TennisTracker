package Database.Model.SakilaModel.Table;

import Database.Model.SakilaModel.Record.FilmRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class FilmTable extends CustomTable<FilmRecord> {
    public static final FilmTable FILM = new FilmTable();

    public final TableField<FilmRecord, String> FILM_ID = createField(name("FILM_ID"), VARCHAR);
    public final TableField<FilmRecord, String> TITLE  = createField(name("TITLE"), VARCHAR);
    public final TableField<FilmRecord, String> LAST_NAME  = createField(name("LAST_NAME"), VARCHAR);

    public FilmTable(){
        super(name("Film"));
    }


    protected FilmTable(Name name) {
        super(name);
    }

    protected FilmTable(Name name, Schema schema) {
        super(name, schema);
    }

    @Override
    public Class<? extends FilmRecord> getRecordType() {
        return null;
    }
}


