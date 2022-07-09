package Database.Model.SakilaModel.Record;

import Database.Model.SakilaModel.Table.FilmActor;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class FilmActorRecord extends CustomRecord<FilmActorRecord> {

        protected FilmActorRecord(Table<FilmActorRecord> table) {
            super(table);
        }

        protected FilmActorRecord() {
            super(FilmActor.FILM_ACTOR);
        }
    }
