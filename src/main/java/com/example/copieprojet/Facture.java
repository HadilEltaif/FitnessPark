package com.example.copieprojet;

public class Facture {
    public Facture(int numFact, double montant, int id_Adherant ,String statut) {
        this.numFact = numFact;
        this.montant = montant;
        this.id_Adherant = id_Adherant;
        this.statut=statut;
    }

    public int getNumFact() {
        return numFact;
    }

    public void setNumFact(int numFact) {
        this.numFact = numFact;
    }

    public int getId_Adherant() {
        return id_Adherant;
    }

    public void setId_Adherant(int id_Adherant) {
        this.id_Adherant = id_Adherant;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    int numFact;
    double montant;
    int id_Adherant;
    String statut;

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }




}
