package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sudrieat.Modele.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConnecterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecter);


        EditText numero_telephone = findViewById(R.id.telephone);
        Button se_connecter = findViewById(R.id.se_connecter);
        Button se_creer_compte = findViewById(R.id.se_creer_compte);

        //Initialisation de firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        //Quand on clique sur le bouton 'se connecter'
        se_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Vérifier si l'utilisateur n'existe pas déjà
                        if (snapshot.child(numero_telephone.getText().toString()).exists())
                        {

                            //Récuperer les infos de l'utilisateur
                            User user = snapshot.child(numero_telephone.getText().toString()).getValue(User.class);

                            EditText mot_de_passe = findViewById(R.id.mdp);

                            if (user.getMdP() != null && user.getMdP().equals(mot_de_passe.getText().toString()) && user.getAdmin() == 0)
                            {
                                // accès au compte utilisateur normal
                                Toast toast = Toast.makeText(getApplicationContext(), "Vous êtes bien connecté !", Toast.LENGTH_SHORT);
                                toast.show();

                                startActivity(new Intent(getApplicationContext(), AccueilActivity.class));
                                overridePendingTransition(0,0);
                            }

                            else if (user.getMdP() != null && user.getMdP().equals(mot_de_passe.getText().toString()) && user.getAdmin() == 1)
                            {
                                // accès à l'interface admin
                                Toast toast = Toast.makeText(getApplicationContext(), "Accès admin autorisé", Toast.LENGTH_SHORT);
                                toast.show();

                                startActivity(new Intent(getApplicationContext(), AdminActivity1.class));
                                overridePendingTransition(0,0);

                            }

                            else
                                {
                                Toast toast = Toast.makeText(getApplicationContext(), "Mot de passe incorrect", Toast.LENGTH_LONG);
                                toast.show();
                                }

                        }


                        else
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Vous n'avez pas de compte", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        se_creer_compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EnregistrerActivity.class));
                overridePendingTransition(0,0);
            }
        });

    }
}