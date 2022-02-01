package com.example.copieprojet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AproposController implements Initializable {
        @FXML
        private Button AdhBtn;

        @FXML
        private Button CnxBtn;

        @FXML
        private Button EntrBtn;

        @FXML
        private Button factBtn;

        @FXML
        private Button homeBtn;

       @FXML
       private MenuItem ActMusculation;

        @FXML
        private MenuItem Cardio_Training;
        @FXML
        private MenuItem crossTraining;
        @FXML
         private MenuItem coursCollectifs;
        @FXML
        void AdherantAction(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            AdhBtn.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Adhérant.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void ConnexionAction(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            CnxBtn.getScene().getWindow().hide();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void EntraineurAction(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            EntrBtn.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Entraineurs.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void FactureAction(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            factBtn.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Facture.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void homee(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void MusculationAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        ActMusculation.getParentMenu();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Musculation.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Cardio_TrainingAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Cardio_Training.getParentMenu();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("CardioTrainning.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void crossTrainingAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        crossTraining.getParentMenu();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("CrossTraining.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void coursCollectifsAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        coursCollectifs.getParentMenu();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("coursCollectifs.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}


