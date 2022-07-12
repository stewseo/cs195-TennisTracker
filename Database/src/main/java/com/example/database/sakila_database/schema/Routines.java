package com.example.database.sakila_database.schema;

import com.example.database.sakila_database.routines.GroupConcat;
import com.example.database.sakila_database.routines._GroupConcat;
import org.jooq.AggregateFunction;
import org.jooq.Configuration;
import org.jooq.Field;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {


    public static String _GroupConcat(
            Configuration configuration
            , String __1
            , String __2
    ) {
        _GroupConcat f = new _GroupConcat();
        f.set__1(__1);
        f.set__2(__2);

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public._group_concat</code> as a field.
     */
    public static Field<String> _GroupConcat(
            String __1
            , String __2
    ) {
        _GroupConcat f = new _GroupConcat();
        f.set__1(__1);
        f.set__2(__2);

        return f.asField();
    }

    /**
     * Get <code>public._group_concat</code> as a field.
     */
    public static Field<String> _GroupConcat(
            Field<String> __1
            , Field<String> __2
    ) {
        _GroupConcat f = new _GroupConcat();
        f.set__1(__1);
        f.set__2(__2);

        return f.asField();
    }


    /**
     * Get <code>public.group_concat</code> as a field.
     */
    public static AggregateFunction<String> groupConcat(
            String __1
    ) {
        GroupConcat f = new GroupConcat();
        f.set__1(__1);

        return f.asAggregateFunction();
    }

    /**
     * Get <code>public.group_concat</code> as a field.
     */
    public static AggregateFunction<String> groupConcat(
            Field<String> __1
    ) {
        GroupConcat f = new GroupConcat();
        f.set__1(__1);

        return f.asAggregateFunction();
    }
}