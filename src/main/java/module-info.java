module com.example.cs195tennis {
    requires MaterialFX;
    requires VirtualizedFX;
    requires fr.brouillard.oss.cssfx;
    requires org.jooq;
    requires com.querydsl.core;
    requires com.google.common;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires com.opencsv;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;
    requires java.logging;
    requires org.apache.logging.log4j;
    requires org.controlsfx.controls;
    requires java.sql;
    requires com.jfoenix;
    requires org.xerial.sqlitejdbc;
    requires java.sql.rowset;
    requires org.kordamp.bootstrapfx.core;
    opens com.example.cs195tennis.model to javafx.base;
    opens com.example.cs195tennis.controller to javafx.fxml;
    exports com.example.cs195tennis;
}
