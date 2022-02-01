package com.example.copieprojet;

public class Personne {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumcin() {
        return numcin;
    }

    public void setNumcin(int numcin) {
        this.numcin = numcin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    public Personne(int id, String nom, String prenom, int numcin, String email, String adresse, int poids, int taille) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numcin = numcin;
        this.email = email;
        this.adresse = adresse;
        this.poids = poids;
        this.taille = taille;
    }

    public Personne(int id, String nom, String prenom, int numcin, String email, String adresse, int poids, int taille, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numcin = numcin;
        this.email = email;
        this.adresse = adresse;
        this.poids = poids;
        this.taille = taille;
        this.sexe = sexe;
    }
    private  int id;
    private   String nom;
    private   String prenom;
    private  int numcin;
    private  String email;
    private String adresse;
    private int poids;
    private int taille;
    private String sexe;
}

