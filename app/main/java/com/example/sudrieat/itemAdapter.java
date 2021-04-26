package com.example.sudrieat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class itemAdapter extends FirebaseRecyclerAdapter<
        item, com.example.sudrieat.itemAdapter.itemsViewholder> {

    public itemAdapter(
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

        // Add Prix from model class (here
        // "item.class")to appropriate view in Card
        // view (here "item.xml")
        holder.Prix.setText(model.getSPrix());

        // Add Img from model class (here
        // "item.class")to appropriate view in Card
        // view (here "item.xml")
        Glide.with(holder.Img.getContext()).load(model.getImg()).into(holder.Img);
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
        return new com.example.sudrieat.itemAdapter.itemsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "item.xml")
    class itemsViewholder extends RecyclerView.ViewHolder {
        TextView Nom, Prix;
        CircleImageView Img;

        public itemsViewholder(@NonNull View itemView)
        {
            super(itemView);

            Nom = itemView.findViewById(R.id.Nom);
            Prix = itemView.findViewById(R.id.Prix);
            Img=itemView.findViewById(R.id.Img);
        }
    }
}