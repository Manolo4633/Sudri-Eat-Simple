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

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    itemAdapter adapter1; // Create Object of the Adapter class
    itemAdapter adapter2;
    itemAdapter adapter3;
    DatabaseReference mbase1; // Create object of the
    DatabaseReference mbase2;
    DatabaseReference mbase3;

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
        mbase1 = FirebaseDatabase.getInstance().getReference("Products").child("Boissons");
        mbase2 = FirebaseDatabase.getInstance().getReference("Products").child("Produits_sucres");
        mbase3 = FirebaseDatabase.getInstance().getReference("Products").child("Produits_sales");
        recyclerView1 = findViewById(R.id.recycler1);
        recyclerView2 = findViewById(R.id.recycler2);
        recyclerView3 = findViewById(R.id.recycler3);

        // To display the Recycler view linearly
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<item> option1
                = new FirebaseRecyclerOptions.Builder<item>()
                .setQuery(mbase1, item.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter1 = new itemAdapter(option1);
        // Connecting Adapter class with the Recycler view*/
        recyclerView1.setAdapter(adapter1);


        FirebaseRecyclerOptions<item> option2
                = new FirebaseRecyclerOptions.Builder<item>()
                .setQuery(mbase2, item.class)
                .build();
        adapter2 = new itemAdapter(option2);
        recyclerView2.setAdapter(adapter2);

        FirebaseRecyclerOptions<item> option3
                = new FirebaseRecyclerOptions.Builder<item>()
                .setQuery(mbase3, item.class)
                .build();
        adapter3 = new itemAdapter(option3);
        recyclerView3.setAdapter(adapter3);


    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart() {
        super.onStart();
        adapter1.startListening();
        adapter2.startListening();
        adapter3.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter1.stopListening();
        adapter2.stopListening();
        adapter3.stopListening();
    }


}