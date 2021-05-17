package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class InfosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);


        //Initialisation:
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //On met Jeux en selectionne par défaut:
        bottomNavigationView.setSelectedItemId(R.id.nav_infos);

        //Mise en place du Listenner:
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_accueil:
                        startActivity(new Intent(getApplicationContext(), AccueilActivity.class));
                        overridePendingTransition(0,0);
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
                        return true;

                }
                return false;
            }
        });


        Button bouton_mini_jeu = findViewById(R.id.bouton_jeu_cookie);
        bouton_mini_jeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CookieActivity.class));
                overridePendingTransition(0,0);
            }
        });


        Button bouton_deconnection = findViewById(R.id.bouton_deco);
        bouton_deconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                EnregistrerActivity.page=0;
                ConnecterActivity.page=0;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }
}