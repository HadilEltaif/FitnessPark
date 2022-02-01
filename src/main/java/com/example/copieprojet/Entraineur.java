package com.example.copieprojet;

public class Entraineur extends Personne{

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    private String activite;

    public Entraineur(int id, String nom, String prenom, int numcin, String email, String adresse, int poids, int taille, String sexe , String activite) {
        super(id, nom, prenom, numcin, email, adresse, poids, taille, sexe);
        this.activite=activite;
    }
}
