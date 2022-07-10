package com.example.database.sakila_database.SakilaModel.Table;

import com.example.database.sakila_database.SakilaModel.Record.FilmActorRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;

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