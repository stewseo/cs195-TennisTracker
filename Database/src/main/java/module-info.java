module com.example.Database {
    requires org.jooq.meta;
    requires org.jooq;
    requires java.sql;
    requires com.opencsv;
    requires org.apache.logging.log4j;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.tx;
    requires spring.beans;

    exports com.example.database.sakila_database.connection;
    exports com.example.database.sakila_database.model.Table;
    exports com.example.database.sakila_database.model;
    exports com.example.dao;
    exports com.example.database.sakila_database;
    opens com.example.database.sakila_database;
}
