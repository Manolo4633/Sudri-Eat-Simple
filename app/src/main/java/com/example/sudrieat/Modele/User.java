package com.example.sudrieat.Modele;

import java.util.List;

public class User {
    private String Nom;
    private String MdP;
    private String Prenom;
    private String Date_naissance;
    private int admin = 0;

    /*
    private double total;
    private String heure_livraison;
    private String date_livraison;
    private String date;
    private String etat;

     */



    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getdate_naissance() {
        return Date_naissance;
    }

    public void setdate_naissance(String Date_naissance) {
        Date_naissance = Date_naissance;
    }



    public User(){

    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getMdP() {
        return MdP;
    }

    public void setMdP(String MdP) {
        this.MdP = MdP;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        Prenom = Prenom;
    }




    //Constructeur
    public User(String Nom, String MdP, String Prenom, String Date_naissance, int admin) {
        this.Nom = Nom;
        this.MdP = MdP;
        this.Prenom = Prenom;
        this.Date_naissance = Date_naissance;
        this.admin = admin;

    }

}
