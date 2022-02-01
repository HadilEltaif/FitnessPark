package com.example.copieprojet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class FactureController implements Initializable {

    @FXML
    private Button homeBtn;

    @FXML
    private Button ajouter;

    @FXML
    private TableColumn<Facture, Integer> col_Adhr;

    @FXML
    private TableColumn<Facture, Integer> col_fact;

    @FXML
    private TableColumn<Facture, Double> col_mnt;

    @FXML
    private TableColumn<Facture,String> col_statut;



    @FXML
    private TextField id_Adh;



    @FXML
    private TextField mnt;

    @FXML
    private TextField num;


    @FXML
    private TextField statut;

    @FXML
    private Button supprimer;

    @FXML
    private Button imprimer;


    @FXML
    private ImageView refreshbtn;


    @FXML
    private TableView<Facture> tableFact;

    ObservableList<Facture> oblistFact = FXCollections.observableArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int index = -1;



    @FXML
    public void homee() throws IOException {
        Stage hm = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root1;
        root1 = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(root1);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         conn = mysqlconnect.ConnectDB();
        try {
            String sql = "select * from facture";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while (rs.next()) {
                oblistFact.add(new Facture(rs.getInt(1), (int) rs.getDouble(2),rs.getInt(3),rs.getString(4)));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        col_fact.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("numFact"));
        col_mnt.setCellValueFactory(new PropertyValueFactory<Facture,Double>("montant"));
        col_Adhr.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("id_Adherant"));
        col_statut.setCellValueFactory(new PropertyValueFactory<Facture,String>("statut"));
        tableFact.setItems(oblistFact);

    }

    public  void imprimerAction(ActionEvent event) {
        int inumFact = tableFact.getSelectionModel().getSelectedItem().getNumFact();
        double imontant = tableFact.getSelectionModel().getSelectedItem().getMontant();
        int iid_Adherant = tableFact.getSelectionModel().getSelectedItem().getId_Adherant();
        String iStatut = tableFact.getSelectionModel().getSelectedItem().getStatut();
        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream("Facture N."+inumFact+".pdf"));
            document.open();
            Font f=new Font(FontFactory.getFont(FontFactory.TIMES_BOLD,24,Font.UNDERLINE));
            f.setColor(244,193,50);

            Font f2=new Font(FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD));
            f2.setColor(61,69,80);




            Paragraph p1 = new Paragraph("Facture numéro :  "+ inumFact,f );
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph p0 = new Paragraph("     ");
            Paragraph p2 = new Paragraph("____________________________________________________________________________");
            Paragraph p4 = new Paragraph("   -   Montant :   " +"                       "+imontant);
            Paragraph p5 = new Paragraph("   -   Identifiant du adhérant:  "+"          "+iid_Adherant);
            Paragraph p6 = new Paragraph("   -   Statut:  "+"          "+iStatut);
            Paragraph p9 = new Paragraph("Bienvenue au MY FITNESSPARK <3 ");
            p9.setAlignment(Paragraph.ALIGN_RIGHT);


            document.add(p0);
            document.add(p1);
            document.add(p0);
            document.add(p2);
            document.add(p0);
            document.add(p0);
            document.add(p4);
            document.add(p0);
            document.add(p5);
            document.add(p6);
            document.add(p0);
            document.add(p2);
            document.add(p0);
            document.add(p0);
            document.add(p9);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("FITNESS PARK");
            alert.setContentText("Facture ( "+ inumFact + " ) du Adhérant  ( " + iid_Adherant + " ) statut ( "+ iStatut + " )  générée avec succés !");
            alert.showAndWait();


        }
        catch( FileNotFoundException | com.itextpdf.text.DocumentException e){
            System.out.println(e);
        }
        document.close();

    }

    @FXML
    void ajouterAction(ActionEvent event) {
        conn = mysqlconnect.ConnectDB();
        String sql = "INSERT INTO facture (numeroFacture, montant, idAdhérant, Statut) VALUES (?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, num.getText());
            pst.setString(2, mnt.getText());
            pst.setString(3, id_Adh.getText());
            pst.setString(4, statut.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Facture ajouté avec succés!!!");
            alert.showAndWait();



        } catch (Exception e) {

        }

    }

    @FXML
    void supprimerAction(ActionEvent event) {
        Connection conn = mysqlconnect.ConnectDB();
        String sql = "DELETE FROM facture WHERE numeroFacture=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, num.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment supprimer cette facture !!!");
            alert.showAndWait();

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Facture supprimée avec succés!!!");
            alert1.showAndWait();
            //modifierTable();
        } catch (Exception e) {

        }

    }

    public void getSelected(MouseEvent mouseEvent) {
        index = tableFact.getSelectionModel().getSelectedIndex();
        if (index < -1) {
            return;
        }

        num.setText(col_fact.getCellData(index).toString());
        mnt.setText(col_mnt.getCellData(index).toString());
        id_Adh.setText(col_Adhr.getCellData(index).toString());
        statut.setText(col_statut.getCellData(index).toString());


    }

    @FXML
    void refreshAction(MouseEvent event) {
        try {
            oblistFact.clear();
            conn = mysqlconnect.ConnectDB();
            String sql = "SELECT * FROM facture";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                oblistFact.add(new Facture((rs.getInt(1)), rs.getDouble(2), rs.getInt(3), rs.getString(4)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
