package com.example.database.sakila_database.SakilaModel.Table;

import com.example.database.db_connection.Connect;
import com.example.database.sakila_database.SakilaModel.Record.FilmRecord;
import org.jooq.*;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;

import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class Film extends CustomTable<FilmRecord> {
    public static final Film FILM = new Film();

    public final TableField<FilmRecord, Long> FILM_ID = createField(name("FILM_ID"), BIGINT);
    public final TableField<FilmRecord, String> TITLE  = createField(name("TITLE"), VARCHAR);
    public final TableField<FilmRecord, String> DESCRIPTION  = createField(name("DESCRIPTION"), VARCHAR);
    public final TableField<FilmRecord, String> RELEASE_YEAR  = createField(name("RELEASE_YEAR"), VARCHAR);
    public final TableField<FilmRecord, String> LANGUAGE_ID  = createField(name("LANGUAGE_ID"), VARCHAR);
    public final TableField<FilmRecord, String> ORIGINAL_LANGUAGE_ID  = createField(name("ORIGINAL_LANGUAGE_ID"), VARCHAR);
    public final TableField<FilmRecord, String> RENTAL_DURATION  = createField(name("RENTAL_DURATION"), VARCHAR);
    public final TableField<FilmRecord, String> RENTAL_RATE  = createField(name("RENTAL_RATE"), VARCHAR);
    public final TableField<FilmRecord, String> LENGTH  = createField(name("length"), VARCHAR);
    public final TableField<FilmRecord, String> REPLACEMENT_COST  = createField(name("replacement_cost"), VARCHAR);
    public final TableField<FilmRecord, String> RATING  = createField(name("rating"), VARCHAR);



    public Film(){
        super(name("Film"));
    }


    protected Film(Name name) {
        super(name);
    }

    protected Film(Name name, Schema schema) {
        super(name, schema);
    }

    @Override
    public Class<? extends FilmRecord> getRecordType() {
        return FilmRecord.class;
    }

    public List<Index> getIndexes() {
        Connect connect = new Connect();
         return connect.create()
                 .meta()
                 .getSchemas("sakila")
                 .get(0)
                 .getTable("Film")
                 .getIndexes();
    }

    @Override
    public Identity<FilmRecord, Long> getIdentity() {
        return (Identity<FilmRecord, Long>) super.getIdentity();
    }

    private transient Address _address;


}


