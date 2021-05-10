package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CommandesActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);

        //Initialisation:
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //On met Jeux en selectionne par défaut:
        bottomNavigationView.setSelectedItemId(R.id.nav_commandes);

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

        //Boutons flottant pour aller au panier
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