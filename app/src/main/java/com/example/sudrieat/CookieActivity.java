package com.example.sudrieat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CookieActivity extends AppCompatActivity {

    private int nbrClics = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie);

        TextView nbr_clics = findViewById(R.id.nbrclics);
        ImageView cookie = findViewById(R.id.cookie);
        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbrClics++;
                nbr_clics.setText("Nombre de clics: " + nbrClics);

                if (nbrClics == 10){
                    Toast toast = Toast.makeText(getApplicationContext(), "Bien joué !", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if (nbrClics == 100){
                    Toast toast = Toast.makeText(getApplicationContext(), "Wahou, t'es motivé !", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if (nbrClics == 1000){
                    Toast toast = Toast.makeText(getApplicationContext(), "Mais arrête t'es accro ou quoi ??", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        Button bouton_retour = findViewById(R.id.bouton_retour);
        bouton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InfosActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }
}