module com.example.Database {
    requires org.jooq.meta;
    requires org.jooq;
    requires java.sql;
    requires com.example.utilities;
    requires kotlin.stdlib;
    requires gradle.api;
    requires org.jooq.codegen;
    requires kotlin.reflect;
    requires gradle.kotlin.dsl;
    requires org.codehaus.groovy;
    requires jdbc;
    requires testcontainers;
    requires org.flywaydb.core;
}
