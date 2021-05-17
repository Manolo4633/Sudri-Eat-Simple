package com.example.sudrieat.Modele;

import java.util.ArrayList;

public class Order {
    private String Nom;
    private double Prix;
    private String Img;
    private int Stock;


    public Order(){

    }

    //Getter et Setter:

    //Image
    public void setImg(String img) {
        Img = img;
    }
    public String getImg() {
        return Img;
    }

    //Nom
    public void setNom(String Nom) {
        Nom = Nom;
    }
    public String getNom() { return Nom; }

    //Prix
    public void setPrix(double Prix) { Prix = Prix; }
    public double getPrix() {
        return Prix;
    }

    //Stock
    public void setStock(int Stock){ Stock = Stock; }
    public int getStock(){return Stock;}




    //Constructeur:
    public Order(String Nom, double Prix, String Img, int Stock) {
        this.Nom = Nom;
        this.Prix = Prix;
        this.Img = Img;
        this.Stock = Stock;
    }
}
