package com.example.sudrieat;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class itemAdapterStock extends FirebaseRecyclerAdapter<item, itemAdapterStock.itemsViewholder> {


    //int stock = 0;


    public itemAdapterStock(
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


        // Add Img from model class (here
        // "item.class")to appropriate view in Card
        // view (here "item.xml")
        Glide.with(holder.Img.getContext()).load(model.getImg()).into(holder.Img);

        holder.Stock.setText(model.getStrStock());


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
                .inflate(R.layout.item_stock, parent, false);
        return new itemAdapterStock.itemsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "item.xml")
    class itemsViewholder extends RecyclerView.ViewHolder {
        TextView Nom, Stock;
        CircleImageView Img;


        public itemsViewholder(@NonNull View itemView)
        {
            super(itemView);

            Nom = itemView.findViewById(R.id.Nom);
            Img=itemView.findViewById(R.id.Img);
            Stock=itemView.findViewById(R.id.Stock);

        }
    }
}
