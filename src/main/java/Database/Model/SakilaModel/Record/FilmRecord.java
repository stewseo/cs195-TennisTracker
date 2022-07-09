package Database.Model.SakilaModel.Record;

import Database.Model.SakilaModel.Table.Film;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class FilmRecord extends CustomRecord<FilmRecord> {

    protected FilmRecord(Table<FilmRecord> table) {
        super(table);
    }

    protected FilmRecord() {
        super(Film.FILM);
    }
}
