module warSalib {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires gson;
    requires xstream;
    requires passay;
    requires jdk.jdi;

    exports view;
    opens view to javafx.fxml;
}