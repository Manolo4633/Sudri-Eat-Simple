package com.example.sudrieat.Modele;

public class Item {
    private String Nom;
    private double Prix;
    private String Img;


    public Item(){

    }

    public void setImg(String img) {
        Img = img;
    }

    public String getImg() {
        return Img;
    }

    public void setNom(String Nom) {
        Nom = Nom;
    }

    public void setPrix(double Prix) {
        Prix = Prix;
    }

    public String getNom() {
        return Nom;
    }

    public double getPrix() {
        return Prix;
    }

    public Item(String Nom, double Prix, String Img) {
        this.Nom = Nom;
        this.Prix = Prix;
        this.Img = Img;
    }
}
