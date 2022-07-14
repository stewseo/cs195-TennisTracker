module com.example.Database {
    requires org.jooq.meta;
    requires org.jooq;
    requires java.sql;
    requires com.opencsv;
    requires org.apache.logging.log4j;
    exports com.example.database;
    exports com.example.database.connection;
    exports com.example.database.sakila_database.model.Table;
    exports com.example.database.sakila_database.model;
}
