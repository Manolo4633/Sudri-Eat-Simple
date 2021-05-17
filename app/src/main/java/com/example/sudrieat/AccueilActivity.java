package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        //Initialisation:
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //On met Jeux en selectionne par défaut:
        bottomNavigationView.setSelectedItemId(R.id.nav_accueil);

        //Mise en place du Listenner:
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_accueil:
                        return true;

                    case R.id.nav_commandes:
                        startActivity(new Intent(getApplicationContext(), CommandesActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_produits:
                        startActivity(new Intent(getApplicationContext(), ProduitsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_infos:
                        startActivity(new Intent(getApplicationContext(), InfosActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


        //Pour le bouton "Nos Produits":
        //Initialisation:
        Button bouton_produits = findViewById(R.id.boutton_produits);

        //Mise en place du Listenner:
        bouton_produits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProduitsActivity.class));
                overridePendingTransition(0,0);
            }
        });


        //Pour le bouton "Commandes":
        //Initialisation:
        Button bouton_commandes = findViewById(R.id.bouttons_commandes);

        //Mise en place du Listenner:
        bouton_commandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CommandesActivity.class));
                overridePendingTransition(0,0);
            }
        });


        //Boutons flottant panier
        FloatingActionButton bouton_flottant= findViewById(R.id.floatingActionButton);
        bouton_flottant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aller au panier
                Toast toast = Toast.makeText(getApplicationContext(), "Test du bouton flottant", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }
}