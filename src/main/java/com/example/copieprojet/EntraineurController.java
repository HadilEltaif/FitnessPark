package com.example.copieprojet;

import com.itextpdf.text.*;
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
import java.util.stream.Collectors;

public class EntraineurController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField adr;
    @FXML
    private TextField poids;
    @FXML
    private TextField taille;
    @FXML
    private TextField sexe;
    @FXML
    private TextField activite;
    @FXML
    private TextField filterField;


    @FXML
    private TableView<Entraineur> tableEntr;


    @FXML
    private TableColumn<Entraineur, Integer> col_id;
    @FXML
    private TableColumn<Entraineur, String> col_nom;
    @FXML
    private TableColumn<Entraineur, String> col_prenom;
    @FXML
    private TableColumn<Entraineur, Integer> col_numcin;
    @FXML
    private TableColumn<Entraineur, String> col_email;
    @FXML
    private TableColumn<Entraineur, String> col_adresse;
    @FXML
    private TableColumn<Entraineur, Integer> col_poids;
    @FXML
    private TableColumn<Entraineur, Integer> col_taille;
    @FXML
    private TableColumn<Entraineur, String> col_sexe;
    @FXML
    private TableColumn<Entraineur, String> col_activite;


    @FXML
    private ImageView hmbtn;
    @FXML
    private Button homBtn;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button imprimerBtn;
    @FXML
    private ImageView refreshbtn;
    @FXML
    private Button filter;

    ObservableList<Entraineur> data=FXCollections.observableArrayList();
    ObservableList<Entraineur> list = FXCollections.observableArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int index = -1;

    public void homee() throws IOException {
        Stage hm = new Stage();
        homBtn.getScene().getWindow().hide();
        Parent root1;
        root1 = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(root1);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
    }

    @FXML
    void ajouterEntr(ActionEvent event) {
        conn = mysqlconnect.ConnectDB();
        String sql = "INSERT INTO listentraineurs (id_Entr, nomEntr, prenomEntr, numCinEntr, emailEntr, adresseEntr, poidsEntr, tailleEntr, sexeEntr, activiteEntr) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id.getText());
            pst.setString(2, nom.getText());
            pst.setString(3, prenom.getText());
            pst.setString(4, cin.getText());
            pst.setString(5, email.getText());
            pst.setString(6, adr.getText());
            pst.setString(7, poids.getText());
            pst.setString(8, taille.getText());
            pst.setString(9, sexe.getText());
            pst.setString(10, activite.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Entraineur ajouté avec succés!!!");
            alert.showAndWait();
            modifierTable();


        } catch (Exception e) {

        }
    }

    @FXML
    void modifierEntr(ActionEvent event) {
        try {
            conn = mysqlconnect.ConnectDB();

            String value1 = id.getText();
            String value2 = nom.getText();
            String value3 = prenom.getText();
            String value4 = cin.getText();
            String value5 = email.getText();
            String value6 = adr.getText();
            String value7 = poids.getText();
            String value8 = taille.getText();
            String value9 = sexe.getText();
            String value10 = activite.getText();

            String sql = " update listentraineurs set id_Entr='" + value1 + "', nomEntr='" + value2 + "', prenomEntr='"
                    + value3 + "', numCinEntr='" + value4 + "', emailEntr='" + value5 + "', adresseEntr='"
                    + value6 + "', poidsEntr='" + value7 + "', tailleEntr='" + value8 + "', sexeEntr='"
                    + value9 + "', activiteEntr='" + value10 + "'WHERE id_Entr='" + value1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment modifier les infos personnels !!!!");
            alert.showAndWait();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Entraineur modifié avec succés!!!");
            alert1.showAndWait();

            modifierTable();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Probleme d'ajout !!!!");
            alert.showAndWait();
        }

    }

    @FXML
    void supprimerEntr(ActionEvent event) {
        Connection conn = mysqlconnect.ConnectDB();
        String sql = "DELETE FROM listentraineurs WHERE id_Entr=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment le supprimer !!!");
            alert.showAndWait();

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Entraineur supprimé avec succés!!!");
            alert1.showAndWait();
            modifierTable();
        } catch (Exception e) {

        }
    }



    //cette methode sert à modifier la tableView sons quitter l'application et rentrer
    public void modifierTable() {

        col_id.setCellValueFactory(new PropertyValueFactory<Entraineur, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Entraineur, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Entraineur, String>("prenom"));
        col_numcin.setCellValueFactory(new PropertyValueFactory<Entraineur, Integer>("numcin"));
        col_email.setCellValueFactory(new PropertyValueFactory<Entraineur, String>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<Entraineur, String>("adresse"));
        col_poids.setCellValueFactory(new PropertyValueFactory<Entraineur, Integer>("poids"));
        col_taille.setCellValueFactory(new PropertyValueFactory<Entraineur, Integer>("taille"));
        col_sexe.setCellValueFactory(new PropertyValueFactory<Entraineur, String>("sexe"));
        col_activite.setCellValueFactory(new PropertyValueFactory<Entraineur, String>("activite"));
        tableEntr.setItems(list);

    }


    //méthode select Entraineurs
    public void getSelected(MouseEvent mouseEvent) {
        index = tableEntr.getSelectionModel().getSelectedIndex();
        if (index < -1) {
            return;
        }

        id.setText(col_id.getCellData(index).toString());
        nom.setText(col_nom.getCellData(index).toString());
        prenom.setText(col_prenom.getCellData(index).toString());
        cin.setText(col_numcin.getCellData(index).toString());
        email.setText(col_email.getCellData(index).toString());
        adr.setText(col_adresse.getCellData(index).toString());
        poids.setText(col_poids.getCellData(index).toString());
        taille.setText(col_taille.getCellData(index).toString());
        sexe.setText(col_sexe.getCellData(index).toString());
        activite.setText(col_activite.getCellData(index).toString());

    }
    @FXML
    void refreshAction(MouseEvent event) {
        try {
            list.clear();
            conn = mysqlconnect.ConnectDB();
            String sql = "SELECT * FROM listentraineurs";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Entraineur((rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void imprimerAction(ActionEvent event) {
        int sid = tableEntr.getSelectionModel().getSelectedItem().getId();
        String snom = tableEntr.getSelectionModel().getSelectedItem().getNom();
        String sprenom = tableEntr.getSelectionModel().getSelectedItem().getPrenom();
        int snumCin = tableEntr.getSelectionModel().getSelectedItem().getNumcin();
        String sEmail = tableEntr.getSelectionModel().getSelectedItem().getEmail();
        String sadresse = tableEntr.getSelectionModel().getSelectedItem().getAdresse();
        int sPoids = tableEntr.getSelectionModel().getSelectedItem().getPoids();
        int staille = tableEntr.getSelectionModel().getSelectedItem().getTaille();
        String sSexe = tableEntr.getSelectionModel().getSelectedItem().getSexe();
        String sActivite = tableEntr.getSelectionModel().getSelectedItem().getActivite();


        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream(snom+".pdf"));
            document.open();

            Font f=new Font(FontFactory.getFont(FontFactory.TIMES_BOLD,24,Font.UNDERLINE));
            f.setColor(244,193,50);

            Font f2=new Font(FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD));
            f2.setColor(61,69,80);
            Paragraph p1 = new Paragraph("Entraineur numéro :  "+ sid ,f);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph p0 = new Paragraph("     ");
            Paragraph pe = new Paragraph("____________________________________________________________________________");
            Paragraph p2 = new Paragraph("\n\n    -   Nom : ");
            p2.add("             "+snom);
            Paragraph p3 = new Paragraph("   -   Prénom : ");
            p3.add("        "+sprenom);
            Paragraph p4 = new Paragraph("   -   numCin :   " +"            "+snumCin);
            Paragraph p5 = new Paragraph("   -   Email :  "+"  "+sEmail);
            Paragraph p6 = new Paragraph("   -   Adresse :  ");
            p6.add("      "+sadresse);
            Paragraph p7 = new Paragraph("   -   Poids :  ");
            p7.add("   "+sPoids);
            Paragraph p8 = new Paragraph("   -   Taille :  ");
            p8.add("      "+staille);
            Paragraph p9 = new Paragraph("   -   Sexe :  ");
            p9.add("      "+sSexe);
            Paragraph p10 = new Paragraph("   -   Activité :  ");
            p10.add("      "+sActivite);
            Paragraph p11 = new Paragraph("Bienvenue au MY FITNESS PARK : Se DEPASSER - Se SURPASSER <3 ");
            p11.setAlignment(Paragraph.ALIGN_RIGHT);

            document.add(p0);
            document.add(p1);
            document.add(pe);
            document.add(p0);
            document.add(p2);
            document.add(p3);
            document.add(p4);
            document.add(p5);
            document.add(p6);
            document.add(p7);
            document.add(p8);
            document.add(p9);
            document.add(p10);
            document.add(p0);
            document.add(pe);
            document.add(p0);
            document.add(p11);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("FITNESS PARK");
            alert.setContentText("Fiche d'Adhérant  -  " + snom + "  -  générée avec succés !");
            alert.showAndWait();
        }
        catch(DocumentException | FileNotFoundException e){
            System.out.println(e);
        }
        document.close();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = mysqlconnect.ConnectDB();
        try {
            String sql = "select * from listentraineurs";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Entraineur((rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
                data.add(new Entraineur((rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));

            }
            data = list.stream().filter(x -> (Integer.toString(x.getNumcin())).equals(filterField.getText()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            tableEntr.setItems(data);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modifierTable();
    }


    @FXML
    void filterAction(ActionEvent event) {




}}
