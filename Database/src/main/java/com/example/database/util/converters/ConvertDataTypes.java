package com.example.database.util.converters;

import org.jooq.*;

import java.util.function.BiPredicate;


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

}
