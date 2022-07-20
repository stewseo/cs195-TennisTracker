package com.example.model.table;
import com.example.model.DefaultCatalog;
import com.example.model.table.Payments.Payment;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;
    public static final Public PUBLIC = new Public();

    public final Actor ACTOR = Actor.ACTOR;
    public final Address ADDRESS = Address.ADDRESS;
    public final Category CATEGORY = Category.CATEGORY;
    public final City CITY = City.CITY;
    public final Country COUNTRY = Country.COUNTRY;
    public final Customer CUSTOMER = Customer.CUSTOMER;
    public final Film FILM = Film.FILM;
    public final FilmActor FILM_ACTOR = FilmActor.FILM_ACTOR;
    public final FilmCategory FILM_CATEGORY = FilmCategory.FILM_CATEGORY;
    public final Inventory INVENTORY = Inventory.INVENTORY;
    public final Language LANGUAGE = Language.LANGUAGE;
    public final Payment PAYMENT = Payment.PAYMENT;
    public final Store STORE = Store.STORE;

    private Public() {
        super("mysql", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }


    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
                Actor.ACTOR,
                Address.ADDRESS,
                Category.CATEGORY,
                City.CITY,
                Country.COUNTRY,
                Customer.CUSTOMER,
                Film.FILM,
                FilmActor.FILM_ACTOR,
                FilmCategory.FILM_CATEGORY,
                Inventory.INVENTORY,
                Language.LANGUAGE,
                Payment.PAYMENT,
                Rental.RENTAL,
                Store.STORE
        );
    }
}