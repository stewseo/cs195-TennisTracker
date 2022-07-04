package com.example.cs195tennis.Util;
import Data.Schema.Public;
import org.jooq.*;
import org.jooq.Record;

import java.util.function.BiPredicate;

import static Data.Database.ctx;
import static com.example.cs195tennis.model.Match.MATCH;
import static com.example.cs195tennis.model.Tournament.TOURNAMENT;
import static org.jooq.impl.DSL.count;

public class ConvertDataTypes {


    private Condition getTextCondition(Object value, BiPredicate<?, ?> biPredicate, Field<Object> field) {
        String predicateString = biPredicate.toString();

        return switch (predicateString) {
            case ("LIKE") ->
                    field.like(
                            value.toString()
                                    .replace("*", "%"));

            case ("UNLIKE") ->
                    field
                            .notLike(
                                    value.toString()
                                            .replace("*", "%"));

            case ("REGEXP") ->
                    field
                            .likeRegex(
                                    value.toString());

            case ("UNREGEXP") -> field
                    .notLikeRegex(
                            value.toString());

            case ("PREFIX") -> field.
                    like(value.
                            toString() + "%");

            case ("UNPREFIX") ->
                    field.
                    notLike(value
                            .toString() + "%");

            default -> throw new IllegalArgumentException("predicate not supported in has step: " + biPredicate.toString());
        };
    }


    public static Result<Record> getRecordsFromTable(String tableName) {
        Table<?> table =
                Public
                        .SCHEMA
                        .getTable(tableName);

        SelectField<?>[] select = {
                TOURNAMENT.DATE.concat(TOURNAMENT.TOURNAMENT_NAME),
                count()
        };

        Table<?> from = TOURNAMENT.join(MATCH).on(MATCH.TOURNAMENT_ID.eq(TOURNAMENT.TOURNAMENT_ID));

        GroupField[] groupBy = {TOURNAMENT.TOURNAMENT_ID, TOURNAMENT.TOURNAMENT_NAME, TOURNAMENT.DATE};

        SortField<?>[] orderBy = {count().desc()};

        return ctx()
                .select(select)
                .from(from)
                .groupBy(groupBy)
                .orderBy(orderBy)
                .fetch();

    }
//    static Converter<String, Field<?>> c1 =
//            Converter.ofNullable(String.class, Field.class, Field::, Object::toString);
//
//    static Converter<Long, Integer> c2 =
//            Converter.ofNullable(Long.class, Integer.class, Number::intValue, Number::longValue);
//
//
//
//    public static Field<?> convert(String string) {
//        DataType<Long> d1 = SQLDataType.VARCHAR.asConvertedDataType(c1);
//        DataType<Field<?>> d2 = d1.asConvertedDataType(c2);
//
//    }
}
