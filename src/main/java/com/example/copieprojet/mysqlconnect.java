package com.example.copieprojet;

import javafx.scene.control.Alert;


import java.sql.Connection;
import java.sql.DriverManager;


public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDB(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/fitnesspark","root","root");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Connexion Etablie avec succés");
            alert.showAndWait();
            return conn;
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Probleme au niveau de connexion base de donnees  !");
            alert.showAndWait();
           return null;
        }

    }


}
