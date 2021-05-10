package com.example.sudrieat;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.lang.Math.abs;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class itemAdapter extends FirebaseRecyclerAdapter<item, itemAdapter.itemsViewholder> {


    int compteur = 0;
    double prix_total = 0;
    String txt_prix_total;
/*
    private static final int LAYOUT_ONE= 0;
    private static final int LAYOUT_TWO= 1;

    @Override
    public int getItemViewType(int position)
    {
        if(position==0)
            return LAYOUT_ONE;
        else
            return LAYOUT_TWO;
    }
*/

    public itemAdapter(
            @NonNull FirebaseRecyclerOptions<item> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "item.xml") with data in
    // model class(here "item.class")
    @SuppressLint("WrongConstant")
    @Override
    protected void
    onBindViewHolder(@NonNull itemsViewholder holder,
                     int position, @NonNull item model)
    {


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





        //quand on clique sur le bouton '+'
        holder.bouton_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    compteur ++;
                    holder.Nombre_produit.setText(Integer.toString(compteur));

                //prix_total = model.getPrix() * compteur;
                //txt_prix_total = String.valueOf(prix_total);
                //holder.total_panier.setText(txt_prix_total);
            }
        });

        //quand on clique sur le bouton '-'
            holder.bouton_moins.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {


                    if (compteur <= 0)
                    {
                        holder.Nombre_produit.setText("0");
                    }
                    else
                     {
                        compteur--;
                        holder.Nombre_produit.setText(Integer.toString(compteur));

                         //prix_total = model.getPrix() * compteur;
                         //txt_prix_total = String.valueOf(prix_total);
                         //holder.total_panier.setText(txt_prix_total);
                     }

                }
            });



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
                .inflate(R.layout.item, parent, false);
/*
        View view2 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.total_panier, parent, false);*/
        return new itemAdapter.itemsViewholder(view);


    }


    // Sub Class to create references of the views in Crad
    // view (here "item.xml")
    class itemsViewholder extends RecyclerView.ViewHolder {
        TextView Nom, Prix, Nombre_produit, total_panier;
        CircleImageView Img;
        Button bouton_plus, bouton_moins;

        public itemsViewholder(View itemView)
        {
            super(itemView);

            Nom = itemView.findViewById(R.id.Nom);
            Prix = itemView.findViewById(R.id.Prix);
            Img=itemView.findViewById(R.id.Img);
            Nombre_produit=itemView.findViewById(R.id.Nombre_produit);
            bouton_plus=itemView.findViewById(R.id.bouton_plus);
            bouton_moins=itemView.findViewById(R.id.bouton_moins);
            total_panier = itemView.findViewById(R.id.txt_total_price);
        }
    }
}
