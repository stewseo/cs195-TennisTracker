module com.test.viewercontrollertest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.jfoenix;
    opens com.test.viewercontrollertest to javafx.fxml;
    exports com.test.viewercontrollertest;
}