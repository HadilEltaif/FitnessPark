package com.example.copieprojet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  @FXML
  private Button admBtn;

  @FXML
  private ImageView img;

  @FXML
  private TextField login;

  @FXML
  private PasswordField pswd;

     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pst = null;

  @FXML
  void loginActionAdm(ActionEvent event) throws Exception {

    conn= mysqlconnect.ConnectDB();
    String sql = "select * from users where username=? AND password=? ";
    try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,login.getText());
            pst.setString(2,pswd.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("nom de l'utilisateur et mot de passe correctes");
                alert.showAndWait();
              Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                Stage window = (Stage) admBtn.getScene().getWindow();
                window.setScene(new Scene(root));


            }else
            {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez vérifier votre Identifiant et votre Mot de passe !");
            alert.showAndWait();
            }


    }catch (Exception e){

    }

  }
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}

