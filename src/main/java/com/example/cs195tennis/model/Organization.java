package com.example.cs195tennis.model;

import org.jooq.Catalog;
import org.jooq.Converter;
import org.jooq.EnumType;
import org.jooq.Schema;
import org.jooq.impl.EnumConverter;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Organization implements EnumType {
    ATP("ATP"),
    WTA("WTA");

    private final String literal;

    private Organization(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

        @Override
        public Schema getSchema() {
            return Public.PUBLIC;
        }

        @Override
        public String getName() {
            return "league";
        }

        @Override
        public String getLiteral() {
            return literal;
        }

        public static Organization lookupLiteral(String literal) {
            return EnumType.lookupLiteral(Organization.class, literal);
        }
    }

