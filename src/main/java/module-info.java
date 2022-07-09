module com.example.cs195tennis.main {
    requires org.jooq.meta;
    requires org.jooq;
    requires com.opencsv;
    requires MaterialFX;
    requires VirtualizedFX;
    requires fr.brouillard.oss.cssfx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;
    requires java.logging;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.kordamp.bootstrapfx.core;
    opens com.example.cs195tennis.controller to javafx.fxml;
    opens Database.Schema to javafx.base;
    opens Database.Model.SakilaModel.QueryParts to javafx.base;
    opens Database.Listeners to javafx.base;
    exports com.example.cs195tennis;
    opens Database.Model.SakilaModel.Record to org.jooq;
    opens com.example.cs195tennis.model to org.jooq;
    opens Database to javafx.base;
}
