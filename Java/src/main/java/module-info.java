/**
 * Breakout Game For COMP2013 Coursework
 */
module com.ning.breakout {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.fxml;
    requires javafx.swing;

    exports com.ning.breakout;
    opens com.ning.breakout to javafx.fxml;
    opens com.ning.breakout.controller to javafx.fxml;
}