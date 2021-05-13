package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class PanierActivity extends AppCompatActivity {

    private String heure_selectione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        /*------------------ Partie Récap du panier ------------------------------------*/
        //A compléter....






        /*------------------ Partie Choix du jour de livraison -------------------------*/
        // Récupération du TextView qui recevra l'heure choisi par l'utilisateur.
        final TextView choix_date=(TextView)findViewById(R.id.choix_date);
        //TextView choix_date = findViewById(R.id.choix_date);

        // et du widget Calendrier
        DatePicker datePicker= findViewById(R.id.calendrier);



        // Récupération de l'heure courante
        final Calendar date=Calendar.getInstance();

        datePicker.setMinDate(date.getTimeInMillis());

        // Création d'un listener pour notre widget time
        int annee = date.get(date.YEAR);
        int mois = date.get(date.MONTH);
        int jour = date.get(date.DAY_OF_MONTH);

        choix_date.setText(Integer.toString(jour)+"/"+Integer.toString(mois+1)+"/"+Integer.toString(annee));

        datePicker.init(annee, mois, jour, new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(Calendar.YEAR, year);
                date.set(Calendar.MONTH, monthOfYear);
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                choix_date.setText(Integer.toString(dayOfMonth)+"/"+Integer.toString(monthOfYear+1)+"/"+Integer.toString(year));
            }
        });


        /*------------------ Partie Choix de l'heure de livraison -----------------------*/
        Spinner categorie = findViewById(R.id.choixHeure);
        TextView choix_heure = findViewById(R.id.choix_heure);

        TextView confirmation = findViewById(R.id.confirmation);




        /*------------------ Partie Boutons ---------------------------------------------*/
        //Pour le bouton "Valider commande":
        //Initialisation:
        Button bouton_valider = findViewById(R.id.valider_commande);

        //Mise en place du Listenner:
        bouton_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Choix de la catégorie:
                heure_selectione = (String) categorie.getSelectedItem();
                choix_heure.setText(heure_selectione);

                confirmation.setText("Vous avez sélectionné le "+ choix_date.getText().toString()+" à "+choix_heure.getText().toString()+". Confirmer ?");

                /*------------------ Fenêtre Pop-Up de confirmation ---------------------------------------------*/
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(PanierActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Confirmation de la commande");
                builder.setMessage(confirmation.getText().toString());
                builder.setPositiveButton("Confirmer",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //confirmation de la commande
                                //Ecrire ici dans la base de donnée les choix de l'utilisateur...
                                Toast toast = Toast.makeText(getApplicationContext(), "Panier validé !", Toast.LENGTH_SHORT);
                                toast.show();

                                finish();

                                startActivity(new Intent(getApplicationContext(), AccueilActivity.class)); //retour au menu d'accueil
                                overridePendingTransition(0,0);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //fermeture de la boite de dialogue
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        //Pour le bouton "Retour":
        //Initialisation:
        Button bouton_retour = findViewById(R.id.retour);

        //Mise en place du Listenner:
        bouton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProduitsActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }





}