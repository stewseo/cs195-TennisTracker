package com.example.database.sakila_database.schema;


import com.example.database.sakila_database.model.Table.*;
import com.example.database.sakila_database.model.Table.Payments.Payment;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------


    public static final Index IDX_ACTOR_LAST_NAME = Internal.createIndex(DSL.name("idx_actor_last_name"), Actor.ACTOR, new OrderField[] { Actor.ACTOR.LAST_NAME }, false);
    public static final Index IDX_FK_ADDRESS_ID = Internal.createIndex(DSL.name("idx_fk_address_id"), Customer.CUSTOMER, new OrderField[] { Customer.CUSTOMER.ADDRESS_ID }, false);
    public static final Index IDX_FK_CITY_ID = Internal.createIndex(DSL.name("idx_fk_city_id"), Address.ADDRESS, new OrderField[] { Address.ADDRESS.CITY_ID }, false);
    public static final Index IDX_FK_COUNTRY_ID = Internal.createIndex(DSL.name("idx_fk_country_id"), City.CITY, new OrderField[] { City.CITY.COUNTRY_ID }, false);
    public static final Index IDX_FK_CUSTOMER_ID = Internal.createIndex(DSL.name("idx_fk_customer_id"), Payment.PAYMENT, new OrderField[] { Payment.PAYMENT.CUSTOMER_ID }, false);
    public static final Index IDX_FK_FILM_ID = Internal.createIndex(DSL.name("idx_fk_film_id"), FilmActor.FILM_ACTOR, new OrderField[] { FilmActor.FILM_ACTOR.FILM_ID }, false);
    public static final Index IDX_FK_INVENTORY_ID = Internal.createIndex(DSL.name("idx_fk_inventory_id"), Rental.RENTAL, new OrderField[] { Rental.RENTAL.INVENTORY_ID }, false);
    public static final Index IDX_FK_LANGUAGE_ID = Internal.createIndex(DSL.name("idx_fk_language_id"), Film.FILM, new OrderField[] { Film.FILM.LANGUAGE_ID }, false);
    public static final Index IDX_FK_ORIGINAL_LANGUAGE_ID = Internal.createIndex(DSL.name("idx_fk_original_language_id"), Film.FILM, new OrderField[] { Film.FILM.ORIGINAL_LANGUAGE_ID }, false);
    public static final Index IDX_FK_STAFF_ID = Internal.createIndex(DSL.name("idx_fk_staff_id"), Payment.PAYMENT, new OrderField[] { Payment.PAYMENT.STAFF_ID }, false);
    public static final Index IDX_FK_STORE_ID = Internal.createIndex(DSL.name("idx_fk_store_id"), Customer.CUSTOMER, new OrderField[] { Customer.CUSTOMER.STORE_ID }, false);
    public static final Index IDX_LAST_NAME = Internal.createIndex(DSL.name("idx_last_name"), Customer.CUSTOMER, new OrderField[] { Customer.CUSTOMER.LAST_NAME }, false);
    public static final Index IDX_STORE_ID_FILM_ID = Internal.createIndex(DSL.name("idx_store_id_film_id"), Inventory.INVENTORY, new OrderField[] { Inventory.INVENTORY.STORE_ID, Inventory.INVENTORY.FILM_ID }, false);
    public static final Index IDX_TITLE = Internal.createIndex(DSL.name("idx_title"), Film.FILM, new OrderField[] { Film.FILM.TITLE }, false);
    public static final Index IDX_UNQ_MANAGER_STAFF_ID = Internal.createIndex(DSL.name("idx_unq_manager_staff_id"), Store.STORE, new OrderField[] { Store.STORE.MANAGER_STAFF_ID }, true);
    public static final Index IDX_UNQ_RENTAL_RENTAL_DATE_INVENTORY_ID_CUSTOMER_ID = Internal.createIndex(DSL.name("idx_unq_rental_rental_date_inventory_id_customer_id"), Rental.RENTAL, new OrderField[] { Rental.RENTAL.RENTAL_DATE, Rental.RENTAL.INVENTORY_ID, Rental.RENTAL.CUSTOMER_ID }, true);
}