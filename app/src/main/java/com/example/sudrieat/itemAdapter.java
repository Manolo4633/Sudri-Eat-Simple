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
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class itemAdapter extends FirebaseRecyclerAdapter<item, itemAdapter.itemsViewholder> {

    public static double total_panier;

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


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_order = database.getReference("User").child(ConnecterActivity.user_num).child("Cartlist");

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

        List<Integer> numb = new ArrayList<Integer>();
        numb.add(0);
        numb.add(0);




        holder.Numb.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            int i=0;


            @Override
            public void onClick(View view) {
                String Snum = holder.Numb.getNumber();
                int numm = Integer.valueOf(Snum);
                model.setStock(model.getStock() - numm);
                double total = numm * model.getPrix();
                int previous_numb = numb.get(1);
                numb.set(0, previous_numb);
                numb.set(1, numm);

                if (numb.get(1) == 0) {
                    if (numb.get(0) == 1 && numb.get(1) == 0) {
                        table_order.child(model.getNom()).removeValue();
                    }
                } else {
                    item order = new item(model.getNom(), model.getPrix(), model.getImg(), numm);
                    table_order.child(model.getNom()).setValue(order);
                }
                ;

                if (numb.get(1) > numb.get(0)){
                    total_panier+=model.getPrix();
                }
                if (numb.get(1) < numb.get(0)) {
                    total_panier-=model.getPrix();
                }
                else {};

                DecimalFormat df = new DecimalFormat("#0.00");
                String Stotal_panier= String.valueOf(df.format(total_panier));
                ProduitsActivity.txt_prix_total.setText("Total: "+Stotal_panier+"€");
            }
        });



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
                .inflate(R.layout.item, parent, false);
        return new itemAdapter.itemsViewholder(view);


    }

    // Sub Class to create references of the views in Crad
    // view (here "item.xml")



    class itemsViewholder
            extends RecyclerView.ViewHolder {
        TextView Nom, Prix;
        CircleImageView Img;
        ElegantNumberButton Numb;

        public itemsViewholder(@NonNull View itemView)
        {
            super(itemView);

            Nom = itemView.findViewById(R.id.Nom);
            Prix = itemView.findViewById(R.id.Prix);
            Img=itemView.findViewById(R.id.Img);
            Numb=itemView.findViewById(R.id.number_button);


        }
    }
}