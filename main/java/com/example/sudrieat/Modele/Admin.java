package com.example.sudrieat.Modele;

public class Admin extends User{
    private int admin;

    public Admin(int admin) {
        this.admin = admin;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
