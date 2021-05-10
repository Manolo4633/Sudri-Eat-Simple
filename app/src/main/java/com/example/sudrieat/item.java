package com.example.sudrieat;



import java.text.DecimalFormat;

public class item
{
    // Variable to store data corresponding
    private String Nom;
    private double Prix;
    private String Img;
    private int Stock;


    // Mandatory empty constructor
    // for use of FirebaseUI
    public item() {}



    // Getter and setter method

    //Nom
    public String getNom()
    {
        return Nom;
    }
    public void setNom(String Nom)
    {
        this.Nom = Nom;
    }


    //Prix
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


    //Image
    public String getImg()
    {
        return Img;
    }
    public void setImg(String Img)
    {
        this.Img = Img;
    }


    //Stock
    public int getStock(){return Stock;}
    public String getStrStock(){
        String StrStock = String.valueOf(getStock());
        return StrStock;}

    public void setStock(int Stock){this.Stock = Stock;}

}
