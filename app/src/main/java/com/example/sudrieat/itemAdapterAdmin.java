package com.example.sudrieat;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import de.hdodenhof.circleimageview.CircleImageView;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class itemAdapterAdmin extends FirebaseRecyclerAdapter<item, itemAdapterAdmin.itemsViewholder> {

    int item_position = 0; //valeur pour savoir si on supprime ou pas
    private Context context;


    public itemAdapterAdmin(
            @NonNull FirebaseRecyclerOptions<item> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "item.xml") with data in
    // model class(here "item.class")
    @Override
    protected void
    onBindViewHolder(@NonNull itemsViewholder holder,
                     int position, @NonNull item model) {

        // Add Nom from model class (here
        // "item.class")to appropriate view in Card
        // view (here "item.xml")
        holder.Nom.setText(model.getNom());

        // Add Prix from model class (here
        // "item.class")to appropriate view in Card
        // view (here "item.xml")
        holder.Prix.setText(model.getSPrix());

        // Add Img from model class (here
        // "item.class")to appropriate view in Card
        // view (here "item.xml")
        Glide.with(holder.Img.getContext()).load(model.getImg()).into(holder.Img);
        
        String id = model.getNom();

        holder.Supp.setOnClickListener(new View.OnClickListener() {
            private Context context;

            @Override
            public void onClick(View v) {
                //holder.Supp.setText("OK");
                effacerProduit(id); //appel de la fonction qui va effacer


                //item_position = position;
                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Suppression")
                        .setMessage("Etes-vous sûr de vouloir de supprimer le produit ?")
                        .setPositiveButton("Effacer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //suppression du produit
                                effacerProduit(); //appel de la fonction qui va effacer

                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //on annule
                                dialog.dismiss();
                            }
                        })
                        .show();

                 */

            }
        });
    }

    private void effacerProduit(String id){
        //Initialisation de firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_products = database.getReference("Products");

        table_products.child("Boissons").child(id).removeValue();
        table_products.child("Produits_sales").child(id).removeValue();
        table_products.child("Produits_sucres").child(id).removeValue();
        //Toast.makeText(context, "Produit supprimé", Toast.LENGTH_SHORT).show();
/*
        if ("Boissons".equals(id)) {
            //effacer dans la rubrique boisson
            //table_products.child(String.valueOf(database.getReference())).child("Boissons").child(id).removeValue();
            //table_products.child("Boissons").child(id).removeValue();
            Toast.makeText(context, "Produit supprimé", Toast.LENGTH_SHORT).show();
        }
*/
    }


    // Function to tell the class about the Card view (here
    // "item.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public itemsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_supp, parent, false);

        return new itemAdapterAdmin.itemsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "item.xml")
    class itemsViewholder extends RecyclerView.ViewHolder {
        TextView Nom, Prix;
        CircleImageView Img;
        Button Supp;

        public itemsViewholder(@NonNull View itemView)
        {
            super(itemView);

            Nom = itemView.findViewById(R.id.Nom);
            Prix = itemView.findViewById(R.id.Prix);
            Img=itemView.findViewById(R.id.Img);
            Supp = itemView.findViewById(R.id.bouton_supprimer);
        }
    }
}
