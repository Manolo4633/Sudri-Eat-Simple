package com.example.sudrieat;



import java.text.DecimalFormat;

public class item
{
    // Variable to store data corresponding
    // to firstname keyword in database
    private String Nom;

    // Variable to store data corresponding
    // to lastname keyword in database
    private double Prix;

    // Variable to store data corresponding
    // to age keyword in database
    private String Img;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public item() {}

    // Getter and setter method
    public String getNom()
    {
        return Nom;
    }
    public void setNom(String Nom)
    {
        this.Nom = Nom;
    }

    public double getPrix() { return Prix; }

    public String getSPrix(){
        DecimalFormat df = new DecimalFormat("#0.00");
        String SPrix= String.valueOf(df.format(getPrix()));
        SPrix=SPrix+" €";
        return SPrix; }

    public void setPrix(double Prix)
    {
        this.Prix = Prix;
    }
    public String getImg()
    {
        return Img;
    }
    public void setImg(String Img)
    {
        this.Img = Img;
    }
}
