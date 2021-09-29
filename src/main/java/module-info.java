module fr.dylan.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    opens fr.dylan.library to javafx.fxml;
    opens fr.dylan.library.entity to javafx.base;
    exports fr.dylan.library;
    exports fr.dylan.library.controller;
    opens fr.dylan.library.controller to javafx.fxml;
}