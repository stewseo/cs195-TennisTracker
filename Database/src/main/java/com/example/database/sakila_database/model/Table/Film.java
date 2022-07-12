package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.schema.Keys;
import com.example.database.sakila_database.schema.Public;
import com.example.database.sakila_database.enums.MpaaRating;
import com.example.database.sakila_database.model.Table.Record.FilmRecord;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.VARCHAR;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Film extends TableImpl<FilmRecord> {

    private static final long serialVersionUID = 1L;

    public static final Film FILM = new Film();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FilmRecord> getRecordType() {
        return FilmRecord.class;
    }

    /**
     * The column <code>public.film.film_id</code>.
     */
    public final TableField<FilmRecord, Long> FILM_ID = createField(DSL.name("film_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.film.title</code>.
     */
    public final TableField<FilmRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.film.description</code>.
     */
    public final TableField<FilmRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");


    /**
     * The column <code>public.film.language_id</code>.
     */
    public final TableField<FilmRecord, Long> LANGUAGE_ID = createField(DSL.name("language_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.film.original_language_id</code>.
     */
    public final TableField<FilmRecord, Long> ORIGINAL_LANGUAGE_ID = createField(DSL.name("original_language_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.film.rental_duration</code>.
     */
    public final TableField<FilmRecord, Short> RENTAL_DURATION = createField(DSL.name("rental_duration"), SQLDataType.SMALLINT.nullable(false).defaultValue(DSL.field("3", SQLDataType.SMALLINT)), this, "");

    /**
     * The column <code>public.film.rental_rate</code>.
     */
    public final TableField<FilmRecord, BigDecimal> RENTAL_RATE = createField(DSL.name("rental_rate"), SQLDataType.NUMERIC(4, 2).nullable(false).defaultValue(DSL.field("4.99", SQLDataType.NUMERIC)), this, "");

    /**
     * The column <code>public.film.length</code>.
     */
    public final TableField<FilmRecord, Short> LENGTH = createField(DSL.name("length"), SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>public.film.replacement_cost</code>.
     */
    public final TableField<FilmRecord, BigDecimal> REPLACEMENT_COST = createField(DSL.name("replacement_cost"), SQLDataType.NUMERIC(5, 2).nullable(false).defaultValue(DSL.field("19.99", SQLDataType.NUMERIC)), this, "");

    /**
     * The column <code>public.film.rating</code>.
     */
    public final TableField<FilmRecord, MpaaRating> RATING = createField(DSL.name("rating"), SQLDataType.VARCHAR.defaultValue(DSL.field("'G'::mpaa_rating", SQLDataType.VARCHAR)).asEnumDataType(MpaaRating.class), this, "");

    /**
     * The column <code>public.film.last_update</code>.
     */
    public final TableField<FilmRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.film.special_features</code>.
     */
    public final TableField<FilmRecord, String[]> SPECIAL_FEATURES = createField(DSL.name("special_features"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public final TableField<FilmRecord, Object> FULLTEXT = createField(DSL.name("fulltext"), org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"tsvector\"").nullable(false), this, "");

    private Film(Name alias, Table<FilmRecord> aliased) {
        this(alias, aliased, null);
    }

    private Film(Name alias, Table<FilmRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.film</code> table reference
     */
    public Film(String alias) {
        this(DSL.name(alias), FILM);
    }

    /**
     * Create an aliased <code>public.film</code> table reference
     */
    public Film(Name alias) {
        this(alias, FILM);
    }

    /**
     * Create a <code>public.film</code> table reference
     */
    public Film() {
        this(DSL.name("film"), null);
    }

    public <O extends Record> Film(Table<O> child, ForeignKey<O, FilmRecord> key) {
        super(child, key, FILM);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }


    @Override
    public Identity<FilmRecord, Long> getIdentity() {
        return (Identity<FilmRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<FilmRecord> getPrimaryKey() {
        return Keys.FILM_PKEY;
    }

    @Override
    public List<ForeignKey<FilmRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FILM__FILM_LANGUAGE_ID_FKEY, Keys.FILM__FILM_ORIGINAL_LANGUAGE_ID_FKEY);
    }

    private transient Language _filmLanguageIdFkey;
    private transient Language _filmOriginalLanguageIdFkey;

    @Override
    public Film as(String alias) {
        return new Film(DSL.name(alias), this);
    }

    @Override
    public Film as(Name alias) {
        return new Film(alias, this);
    }


    /**
     * Rename this table
     */
    @Override
    public Film rename(String name) {
        return new Film(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Film rename(Name name) {
        return new Film(name, null);
    }



    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<Long, String, String, Integer, Long, Long, Short, BigDecimal, Short, BigDecimal, MpaaRating, LocalDateTime, String[], Object> fieldsRow() {
        return (Row14) super.fieldsRow();
    }
}


