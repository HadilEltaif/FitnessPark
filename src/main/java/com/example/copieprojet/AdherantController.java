package com.example.copieprojet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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


public class AdherantController implements Initializable {


    @FXML
    private TableView<Adherant> tableAdh;
    @FXML
    private TableColumn<Adherant, Integer> col_id;
    @FXML
    private TableColumn<Adherant, String> col_nom;
    @FXML
    private TableColumn<Adherant, String> col_prenom;
    @FXML
    private TableColumn<Adherant, Integer> col_numcin;
    @FXML
    private TableColumn<Adherant, String> col_email;
    @FXML
    private TableColumn<Adherant, String> col_adresse;
    @FXML
    private TableColumn<Adherant, Integer> col_poids;
    @FXML
    private TableColumn<Adherant, Integer> col_taille;
    @FXML
    private TableColumn<Adherant, String> col_sexe;
    @FXML
    private TableColumn<Adherant, String> col_abonnement;

    @FXML
    private TextField abon;

    @FXML
    private TextField adr;

    @FXML
    private Button ajouter;

    @FXML
    private TextField cin;

    @FXML
    private TextField email;

    @FXML
    private Button homBtn;

    @FXML
    private TextField id;

    @FXML
    private Button modifier;

    @FXML
    private TextField nom;

    @FXML
    private TextField poids;

    @FXML
    private TextField prenom;

    @FXML
    private TextField sexe;

    @FXML
    private TextField filterField;

    @FXML
    private Button supprimer;
    @FXML
    private ImageView refreshbtn;

    @FXML
    private Button imprimerbtn;


    @FXML
    private TextField taille;

    ObservableList<Adherant> list = FXCollections.observableArrayList();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int index = -1;



    @FXML
    void ajouterAdh(ActionEvent event) {
        conn = mysqlconnect.ConnectDB();
        String sql = "INSERT INTO listadherants (id_Adh, nomAdh, prenomAdh, numCinAdh, emailAdh, adresseAdh, poidsAdh, tailleAdh, sexeAdh, abonnementAdh) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            pst.setString(10, abon.getText());
            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Adhérant ajouté avec succés!");
            alert.showAndWait();
            modifierTable();



        } catch (Exception e) {

        }

       // sendmail();

    }

//Lorsque un adhearant vient a notre salle de sport pour s'inscrire apres remplir tous ces infos personnels un mail sera envoyer pour la verification
  /*  private void sendmail() throws  IOException {
        try {
            String host = "smtp.gmail.com";
            String user = "hadil@gmail.com";
            String pass = "testMail123";
            String to = "hadilltaif78@gmail.com";
            String from = "hadil@gmail.com";
            String subject = "Nouveau adhérant au Salle de Sport Fitness Park . ";
            String messageText = "Bienvenue au  Salle de Sport Fitness Park. \n Votre inscription a été effectuée avec succés !";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

            try (Transport transport = mailSession.getTransport("smtp")) {
                transport.connect(host, user, pass);
                transport.sendMessage(msg, msg.getAllRecipients());
            }
            System.out.println("message send successfully");
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }
*/
    @FXML
    void homee(ActionEvent event) throws IOException {
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
    void modifierAdh(ActionEvent event) {
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
            String value10 = abon.getText();

            String sql = " update listadherants set id_Adh='" + value1 + "', nomAdh='" + value2 + "', prenomAdh='"
                    + value3 + "', numCinAdh='" + value4 + "', emailAdh='" + value5 + "', adresseAdh='"
                    + value6 + "', poidsAdh='" + value7 + "', tailleAdh='" + value8 + "', sexeAdh='"
                    + value9 + "', abonnementAdh='" + value10 + "'WHERE id_Adh='" + value1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment modifier les infos personnels !!!!");
            alert.showAndWait();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Adhérant modifié avec succés!!!!");
            alert1.showAndWait();

            modifierTable();


        } catch (Exception e) {

        }
    }

    @FXML
    void supprimerAdh(ActionEvent event) {
        Connection conn = mysqlconnect.ConnectDB();
        String sql = "DELETE FROM listadherants WHERE id_Adh=?";
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
            alert1.setContentText("Adhérant supprimé avec succés!!!");
            alert1.showAndWait();
            modifierTable();

        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = mysqlconnect.ConnectDB();
        try {
            String sql = "select * from listadherants";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Adherant((rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
      modifierTable();

            FilteredList<Adherant> filteredData = new FilteredList<Adherant>(list, b -> true);
            //  Définissez le prédicat de filtre chaque fois que le filtre change.
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Adherant -> {
                    // Si le texte du filtre est vide, affichez toutes les personnes.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare nom and prenom de chaque personne avec filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Adherant.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filtrer selon le nom
                    } else if (Adherant.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter selon le prenom
                    }
                    else if (Adherant.getEmail().indexOf(lowerCaseFilter)!=-1)
                        return true;
                    else
                        return false; // ne marche pas.
                });
            });
            //  Enveloppe la FilteredList dans une SortedList..
            SortedList<Adherant> sortedData = new SortedList<Adherant>(filteredData);

            //  Liez le comparateur SortedList au comparateur TableView.
           //   Sinon, le tri de la TableView n'aurait aucun effet.
            sortedData.comparatorProperty().bind(tableAdh.comparatorProperty());

            //  Ajoutez des données triées (et filtrées) à la table.
            tableAdh.setItems(sortedData);


        }



    //méthode select adhérants
    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableAdh.getSelectionModel().getSelectedIndex();
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
        abon.setText(col_abonnement.getCellData(index).toString());

    }

    //cette methode sert à modifier la tableView sons quitter l'application et rentrer
    public void modifierTable() {

        col_id.setCellValueFactory(new PropertyValueFactory<Adherant, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Adherant, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Adherant, String>("prenom"));
        col_numcin.setCellValueFactory(new PropertyValueFactory<Adherant, Integer>("numcin"));
        col_email.setCellValueFactory(new PropertyValueFactory<Adherant, String>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<Adherant, String>("adresse"));
        col_poids.setCellValueFactory(new PropertyValueFactory<Adherant, Integer>("poids"));
        col_taille.setCellValueFactory(new PropertyValueFactory<Adherant, Integer>("taille"));
        col_sexe.setCellValueFactory(new PropertyValueFactory<Adherant, String>("sexe"));
        col_abonnement.setCellValueFactory(new PropertyValueFactory<Adherant, String>("abonnement"));
        tableAdh.setItems(list);

    }

    //methode permet de refresher la page à chaque modification
    @FXML
    void refreshAction(MouseEvent event) {
        try {
            list.clear();
            conn = mysqlconnect.ConnectDB();
            String sql = "SELECT * FROM listadherants";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Adherant((rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    void imprimerAction(ActionEvent event) {
        int sid = tableAdh.getSelectionModel().getSelectedItem().getId();
        String snom = tableAdh.getSelectionModel().getSelectedItem().getNom();
        String sprenom = tableAdh.getSelectionModel().getSelectedItem().getPrenom();
        int snumCin = tableAdh.getSelectionModel().getSelectedItem().getNumcin();
        String sEmail = tableAdh.getSelectionModel().getSelectedItem().getEmail();
        String sadresse = tableAdh.getSelectionModel().getSelectedItem().getAdresse();
        int sPoids = tableAdh.getSelectionModel().getSelectedItem().getPoids();
        int staille = tableAdh.getSelectionModel().getSelectedItem().getTaille();
        String sSexe = tableAdh.getSelectionModel().getSelectedItem().getSexe();
        String sAbonnement = tableAdh.getSelectionModel().getSelectedItem().getAbonnement();


        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream(snom+".pdf"));
            document.open();

            Font f=new Font(FontFactory.getFont(FontFactory.TIMES_BOLD,24,Font.UNDERLINE));
            f.setColor(244,193,50);

            Font f2=new Font(FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD));
            f2.setColor(61,69,80);
            Paragraph p1 = new Paragraph("Adhérant numéro :  "+ sid ,f);
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
            Paragraph p10 = new Paragraph("   -   Abonnement :  ");
            p10.add("      "+sAbonnement);
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


    public void filterAction(ActionEvent event) {
    }
}