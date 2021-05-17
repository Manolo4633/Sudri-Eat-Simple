package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class PanierActivity extends AppCompatActivity {

    private String heure_selectione;
    CartlistAdapter adapter;
    public static TextView total_panier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);



        total_panier=(TextView)findViewById(R.id.Total_cart);




        /*------------------ Partie Récap du panier ------------------------------------*/
        DatabaseReference mbase = FirebaseDatabase.getInstance().getReference("User").child(ConnecterActivity.user_num).child("Cartlist");
        RecyclerView recyclerView = findViewById(R.id.recycler_panier);

        DatabaseReference order_base = FirebaseDatabase.getInstance().getReference("User").child(ConnecterActivity.user_num).child("Order");

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<item> option
                = new FirebaseRecyclerOptions.Builder<item>()
                .setQuery(mbase, item.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new CartlistAdapter(option);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);


        /*------------------ Total du Panier ---------------------------------------------*/








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

                               // order_base.setValue(mbase);

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

    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }



}