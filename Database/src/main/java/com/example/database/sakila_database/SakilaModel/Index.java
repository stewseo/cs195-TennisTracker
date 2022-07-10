package com.example.database.sakila_database.SakilaModel;

import com.example.database.sakila_database.SakilaModel.Table.*;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Index {
    //================================================================================
    //                  Indexes created sakila-create.sql script
    //                  meta.schema('sakila').getTable('tableName').getIndexes()
    //================================================================================

    public static final Index IDX_ACTOR_LAST_NAME =
            (Index) Internal.createIndex(DSL.name("idx_actor_last_name"),
                   Actor.ACTOR,
                   new OrderField[] { Actor.ACTOR.LAST_NAME }, false);

    public static final Index IDX_FK_ADDRESS_ID =
            (Index) Internal.createIndex(DSL.name("idx_fk_address_id"),
                    Customer.CUSTOMER,
                    new OrderField[] { Customer.CUSTOMER.ADDRESS_ID }, false);

    public static final Index IDX_FK_CITY_ID =
            (Index) Internal.createIndex(DSL.name("idx_fk_city_id"),
                    Address.ADDRESS,
                    new OrderField[]{ Address.ADDRESS.CITY_ID }, false);

    public static final Index IDX_FK_COUNTRY_ID =
            (Index) Internal.createIndex(DSL.name("idx_fk_country_id"),
                    City.CITY,
                    new OrderField[]{ City.CITY.COUNTRY_ID }, false);

    public static final Index IDX_FK_FILM_ID =
            (Index) Internal.createIndex(DSL.name("idx_fk_film_id"),
                    FilmActor.FILM_ACTOR,
                    new OrderField[] { FilmActor.FILM_ACTOR.FILM_ID }, false);

    public static final Index IDX_FK_STORE_ID =
            (Index) Internal.createIndex(DSL.name("idx_fk_store_id"),
                    Customer.CUSTOMER,
                    new OrderField[] { Customer.CUSTOMER.STORE_ID }, false);

    public static final Index IDX_LAST_NAME =
            (Index) Internal.createIndex(DSL.name("idx_last_name"),
                    Customer.CUSTOMER,
                    new OrderField[] { Customer.CUSTOMER.LAST_NAME }, false);

    public static final Index IDX_TITLE =
            (Index) Internal.createIndex(DSL.name("idx_title"),
                    Film.FILM,
                    new OrderField[] { Film.FILM.TITLE }, false);
}


