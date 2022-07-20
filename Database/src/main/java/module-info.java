module com.example.database {
    requires java.sql;
    requires org.jooq;
    requires org.jooq.meta;
    requires org.apache.logging.log4j;
    requires redis.clients.jedis;
    exports com.example.database;
    exports com.example.database.redis;
}
