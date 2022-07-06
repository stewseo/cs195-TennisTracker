package Database.Model.SakilaModel.Record;

import Database.Model.SakilaModel.Table.CustomerTable;
import Database.Model.SakilaModel.Table.FilmActorTable;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class FilmActorRecord extends CustomRecord<FilmActorRecord> {

        protected FilmActorRecord(Table<FilmActorRecord> table) {
            super(table);
        }

        protected FilmActorRecord() {
            super(FilmActorTable.FILM_ACTOR);
        }
    }
