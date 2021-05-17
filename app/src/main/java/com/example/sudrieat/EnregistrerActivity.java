package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;

import com.example.sudrieat.Modele.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EnregistrerActivity extends AppCompatActivity {

    private int test_mdp;
    private int test_tel;
    private int admin = 0;
    public static int page=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer);

        EditText nom = findViewById(R.id.nom);
        EditText mot_de_passe = findViewById(R.id.mdp);
        EditText numero_telephone = findViewById(R.id.telephone);
        EditText prenom = findViewById(R.id.prenom);
        Button bouton_enregistrer = findViewById(R.id.enregistrer);





        //Initialisation de firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");




        final TextView choix_date1=(TextView)findViewById(R.id.choix_date1);


        // et du widget Calendrier
        DatePicker datePicker1= findViewById(R.id.date_naissance);

        // Récupération de l'heure courante
        final Calendar date=Calendar.getInstance();

        // Création d'un listener pour notre widget time
        int annee = date.get(date.YEAR);
        int mois = date.get(date.MONTH);
        int jour = date.get(date.DAY_OF_MONTH);

        choix_date1.setText("Vous avez sélectionné le: "+ Integer.toString(jour)+"/"+Integer.toString(mois+1)+"/"+Integer.toString(annee));

        datePicker1.init(annee, mois, jour, new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(Calendar.YEAR, year);
                date.set(Calendar.MONTH, monthOfYear);
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                choix_date1.setText(Integer.toString(dayOfMonth)+"/"+Integer.toString(monthOfYear+1)+"/"+Integer.toString(year));
            }
        });



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
                                User user = new User(nom.getText().toString(), mot_de_passe.getText().toString(), prenom.getText().toString(), choix_date1.getText().toString() , admin);
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
                                        if (prenom.getText().toString().isEmpty() || nom.getText().toString().isEmpty() )
                                        {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Il manque des informations !", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                        else
                                        {
                                            ConnecterActivity.user_num=numero_telephone.getText().toString();
                                            table_user.child(numero_telephone.getText().toString()).setValue(user);
                                            if (page==0) {
                                                Toast toast = Toast.makeText(getApplicationContext(), "Enregistré avec succès !", Toast.LENGTH_SHORT);
                                                toast.show();
                                                page=1;

                                                startActivity(new Intent(getApplicationContext(), AccueilActivity.class));
                                                overridePendingTransition(0, 0);

                                            };
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