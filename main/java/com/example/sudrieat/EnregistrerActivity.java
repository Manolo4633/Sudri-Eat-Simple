package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class EnregistrerActivity extends AppCompatActivity {

    private int test_mdp;
    private int test_tel;
    private int admin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer);

        EditText nom = findViewById(R.id.nom);
        EditText mot_de_passe = findViewById(R.id.mdp);
        EditText numero_telephone = findViewById(R.id.telephone);
        EditText prenom = findViewById(R.id.prenom);
        EditText date_de_naissance = findViewById(R.id.date_naissance);
        Button bouton_enregistrer = findViewById(R.id.enregistrer);


        //Initialisation de firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");


        bouton_enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Verifier si un utilisateur n'a pas déjà un num de tel
                        if (snapshot.child(numero_telephone.getText().toString()).exists())
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Vous avez déjà un compte !", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else
                        {
                                User user = new User(nom.getText().toString(), mot_de_passe.getText().toString(), prenom.getText().toString(), date_de_naissance.getText().toString(), admin);
                                test_mdp = mot_de_passe.getText().toString().length();
                                if (test_mdp < 8) //pour vérifier que le mot de passe à au moins 8 caractères
                                {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Rentrez un mot de passe de 8 caractères", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                else
                                {
                                    test_tel = numero_telephone.getText().toString().length();
                                    if (test_tel != 10) //pour vérifier si le numéro de tel est bien valide
                                    {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Numéro de téléphone invalide", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                    else //enregistre l'utilisateur avec comme clé son numéro
                                    {
                                        if (prenom.getText().toString().isEmpty() || nom.getText().toString().isEmpty() || date_de_naissance.getText().toString().isEmpty())
                                        {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Il manque des informations !", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                        else
                                        {
                                            table_user.child(numero_telephone.getText().toString()).setValue(user);
                                            Toast toast = Toast.makeText(getApplicationContext(), "Enregistré avec succès !", Toast.LENGTH_SHORT);
                                            toast.show();

                                            startActivity(new Intent(getApplicationContext(), AccueilActivity.class));
                                            overridePendingTransition(0,0);
                                        }

                                    }

                                }

                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}