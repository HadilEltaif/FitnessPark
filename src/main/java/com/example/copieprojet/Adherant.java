package com.example.copieprojet;

public class Adherant extends Personne {
    public String getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(String abonnement) {
        this.abonnement = abonnement;
    }

    private String abonnement;


    public Adherant(int id, String nom, String prenom, int numcin, String email, String adresse, int poids, int taille, String sexe,String abonnement) {
        super(id, nom, prenom, numcin, email, adresse, poids, taille, sexe);
        this.abonnement = abonnement;
    }
}
