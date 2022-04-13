module com.example.cs195tennis {

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;
    requires java.logging;
    requires org.apache.logging.log4j;
    requires org.controlsfx.controls;
    requires java.sql;
    requires com.jfoenix;
    exports com.example.cs195tennis.model;
    exports com.example.cs195tennis.controller;
    opens com.example.cs195tennis.controller;
    opens com.example.cs195tennis to javafx.fxml;
    exports com.example.cs195tennis;

}