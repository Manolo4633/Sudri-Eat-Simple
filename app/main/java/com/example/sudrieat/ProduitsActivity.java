package com.example.sudrieat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProduitsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    itemAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits);

        //Initialisation:
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //On met Jeux en selectionne par défaut:
        bottomNavigationView.setSelectedItemId(R.id.nav_produits);

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
                        startActivity(new Intent(getApplicationContext(), CommandesActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_produits:
                        return true;

                    case R.id.nav_infos:
                        startActivity(new Intent(getApplicationContext(), InfosActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        // Create a instance of the database and get
        // its reference
        mbase = FirebaseDatabase.getInstance().getReference("Products").child("Boissons");
        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<item> options
                = new FirebaseRecyclerOptions.Builder<item>()
                .setQuery(mbase, item.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new itemAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);







    }


    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
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