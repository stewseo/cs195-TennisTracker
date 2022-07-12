package com.example.database.sakila_database.Listeners.QueryParts;

import org.jooq.Context;
import org.jooq.DataType;
import org.jooq.Name;
import org.jooq.impl.CustomField;

import static java.lang.System.out;

public class CustField<T> extends CustomField<T> {

    protected CustField(String name, DataType<T> type) {
        super(name, type);
    }

    protected CustField(Name name, DataType<T> type) {
        super(name, type);
    }

    //=============================================================================================================
    //      This QueryPart accepts a Context object in order to render a SQL string or to bind its variables
    //============================================================================================================
    @Override
    public void accept(Context<?> ctx) {
        out.println("custom field accept: " + ctx);
    }
}
