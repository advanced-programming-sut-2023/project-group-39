module warSalib {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires passay;
    requires jdk.jdi;
    requires com.google.gson;


    exports view;
    opens control to com.google.gson;
    exports control;
    opens model to com.google.gson;
    exports model;
    opens view to com.google.gson, javafx.fxml;
}