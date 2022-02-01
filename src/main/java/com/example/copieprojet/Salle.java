package com.example.copieprojet;

public class Salle {

    private int capacite;
    private int num_salle;

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNum_salle() {
        return num_salle;
    }

    public void setNum_salle(int num_salle) {
        this.num_salle = num_salle;
    }


    public Salle(int capacite, int num_salle) {
        this.capacite = capacite;
        this.num_salle = num_salle;
    }




}
