package com.example.sudrieat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartlistAdapter extends FirebaseRecyclerAdapter<item, CartlistAdapter.itemsViewholder> {
    static double toto=0;
    static double hello=0;

    public CartlistAdapter(
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
                     int position, @NonNull item model)
    {

        // Add Nom from model class (here
        // "item.class")to appropriate view in Card
        // view (here "item.xml")
        holder.Nom.setText(model.getNom());
        holder.Quantite.setText(String.valueOf(model.getStock()));

        double Totall= model.getPrix()*model.getStock();
        DecimalFormat df = new DecimalFormat("#0.00");
        String STotall= String.valueOf(df.format(Totall));
        holder.Total.setText(STotall+"€");

        toto += Totall;
       // PanierActivity.total_cart=model.getPrix()*model.getStock();

        PanierActivity.total_panier.setText(String.valueOf(toto)+"€");
        hello+=Totall;





    }




    // Function to tell the class about the Card view (here
    // "item.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public itemsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartlist, parent, false);
        return new CartlistAdapter.itemsViewholder(view);


    }

    // Sub Class to create references of the views in Crad
    // view (here "item.xml")



    class itemsViewholder
            extends RecyclerView.ViewHolder {
        TextView Nom, Quantite, Total;

        public itemsViewholder(@NonNull View itemView)
        {
            super(itemView);

            Nom = itemView.findViewById(R.id.product_cart);
            Quantite = itemView.findViewById(R.id.quantite_cart);
            Total=itemView.findViewById(R.id.total_product);

        }
    }}
