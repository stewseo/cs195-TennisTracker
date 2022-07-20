module com.example.app {
    requires com.example.utilities;
    requires java.logging;
    requires transitive com.example.model;
    requires com.example.list;
    exports com.example.app;
}