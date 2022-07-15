module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires VirtualizedFX;
    requires fr.brouillard.oss.cssfx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires javafx.web;
    requires java.logging;
    requires org.kordamp.bootstrapfx.core;
    opens com.example.app.controller to javafx.fxml;
    opens com.example.app to javafx.fxml;
    exports com.example.app;
}