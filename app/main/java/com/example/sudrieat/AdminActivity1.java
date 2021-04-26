package com.example.sudrieat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);

        Button se_connecter_en_utilisateur = findViewById(R.id.connecter_utilisateur);
        Button se_connecter_en_admin = findViewById(R.id.connecter_admin);

        se_connecter_en_utilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // accès au compte utilisateur normal
                Toast toast = Toast.makeText(getApplicationContext(), "Vous êtes bien connecté en utilisateur !", Toast.LENGTH_SHORT);
                toast.show();

                startActivity(new Intent(getApplicationContext(), AccueilActivity.class));
                overridePendingTransition(0,0);
            }
        });


        se_connecter_en_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // accès au compte admin
                Toast toast = Toast.makeText(getApplicationContext(), "Vous êtes bien connecté en admin !", Toast.LENGTH_SHORT);
                toast.show();

                startActivity(new Intent(getApplicationContext(), AdminActivity2.class));
                overridePendingTransition(0,0);
            }
        });

    }
}