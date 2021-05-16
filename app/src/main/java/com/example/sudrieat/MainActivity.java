package com.example.sudrieat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pour mettre la police sur le texte du titre
        TextView titre = findViewById(R.id.nom);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
        titre.setTypeface(face);

        //Pour mettre la police sur le texte du sous-titre
        TextView sous_titre = findViewById(R.id.sous_titre);
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/AdventPro-Medium.ttf");
        sous_titre.setTypeface(face2);

        /*------------------ Pour lancer la page de connection ------------------*/
        //Initialisation:
        Button boutton_connecter = findViewById(R.id.button_connecter);

        //Mise en place du Listenner:
        boutton_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ConnecterActivity.class));
                overridePendingTransition(0,0);
            }
        });





        /*------------------ Pour lancer la page d'enregistrement ------------------*/
        Button boutton_enregistrer = findViewById(R.id.button_enregistrer);

        //Mise en place du Listenner:
        boutton_enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EnregistrerActivity.class));
                overridePendingTransition(0,0);
            }
        });




        /*------------------ Pour créer une animation lorsqu'on clique sur le logo ------------------*/
        ImageView logo_toast = findViewById(R.id.logo);
        logo_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Votre asso humanitaire !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}