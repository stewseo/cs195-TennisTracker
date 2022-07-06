package com.example.cs195tennis.model.Enum;

import Database.Schema.Public;
import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum TournamentLevel implements EnumType {
    ATP("ATP"),
    ATP_1000("ATP_1000"),
    GRANDSLAM("GRANDSLAM"),
    WTA("WTA");

    private final String literal;

    private TournamentLevel(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
            return Public.SCHEMA;
        }

        @Override
        public String getName() {
            return "league";
        }

        @Override
        public String getLiteral() {
            return literal;
        }

        public static TournamentLevel lookupLiteral(String literal) {
            return EnumType.lookupLiteral(TournamentLevel.class, literal);
        }
    }

