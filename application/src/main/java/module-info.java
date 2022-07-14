module com.example.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires VirtualizedFX;
    requires fr.brouillard.oss.cssfx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires java.logging;
    requires org.kordamp.bootstrapfx.core;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    opens com.example.application.controller to javafx.fxml;
    opens com.example.application to javafx.fxml;
    exports com.example.application;
}