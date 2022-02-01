package com.example.copieprojet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


public class MenuController {
    @FXML
    private Button ExitBtn;
    @FXML
    private Button adhBtn;
    @FXML
    private Button EntrBtn;
    @FXML
    private Button FactureBtn;
    @FXML
    private Button aproposBtn;


    @FXML
    void ExitAction(ActionEvent event) throws IOException {
       Stage stage = new Stage();
       ExitBtn.getScene().getWindow().hide();
       Parent root;
       root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();

    }



    @FXML
    void AdherAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        adhBtn.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Adhérant.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void EntrAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        EntrBtn.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Entraineurs.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void FactureAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FactureBtn.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Facture.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AproposAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        aproposBtn.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("A Propos.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
