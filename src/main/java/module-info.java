module com.example.copieprojet{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires  java.sql;
    requires java.desktop;
    requires itextpdf;
    requires javax.mail;
    requires java.security.sasl;


    opens com.example.copieprojet to javafx.fxml;
    exports com.example.copieprojet;
}