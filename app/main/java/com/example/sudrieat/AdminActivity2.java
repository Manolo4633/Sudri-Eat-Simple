package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sudrieat.Modele.Item;
import com.example.sudrieat.Modele.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity2 extends AppCompatActivity {

    private String item_selectione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);


        EditText nomProduit= findViewById(R.id.editNomProduit);
        EditText prix = findViewById(R.id.editPrixProduit);
        EditText img = findViewById(R.id.editImage);
        Spinner categorie = findViewById(R.id.choixCategorie);
        Button bouton_ajouter = findViewById(R.id.ajouter);

        //Initialisation de firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_products = database.getReference("Products");

        bouton_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_products.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Choix de la catégorie:
                        item_selectione = categorie.getSelectedItem().toString();

                        //Conversion du prix de String en Double:
                        String prix_string = prix.getText().toString();
                        double prix_double = Double.parseDouble(prix_string);

/*
                        if (nomProduit.getText().toString().isEmpty() || prix.getText().toString().isEmpty())
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Il manque des informations !", Toast.LENGTH_SHORT);
                            toast.show();
                        }

 */

                            if ("Produits sucrés".equals(item_selectione))
                            {
                                    //Rajouter produit sucrés
                                    Item item = new Item(nomProduit.getText().toString(), prix_double, img.getText().toString());
                                    table_products.child("Produits_sucres").child(nomProduit.getText().toString()).setValue(item);

                                    Toast toast = Toast.makeText(getApplicationContext(), "Produit sucré ajouté !", Toast.LENGTH_SHORT);
                                    toast.show();
                            }

                            else if ("Produits salés".equals(item_selectione))
                            {
                                //Rajouter produit salés
                                Item item = new Item(nomProduit.getText().toString(), prix_double, img.getText().toString());
                                table_products.child("Produits_sales").child(nomProduit.getText().toString()).setValue(item);

                                Toast toast = Toast.makeText(getApplicationContext(), "Produit salé ajouté !", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            else if ("Boissons".equals(item_selectione))
                            {
                                //Rajouter boisson
                                Item item = new Item(nomProduit.getText().toString(), prix_double, img.getText().toString());
                                table_products.child("Boissons").child(nomProduit.getText().toString()).setValue(item);

                                Toast toast = Toast.makeText(getApplicationContext(), "Boisson ajoutée !", Toast.LENGTH_SHORT);
                                toast.show();
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
