package com.week1.cashregisterpart1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
Context context;
ArrayList<PurchasedProduct> purchasedProducts;

    public HistoryAdapter(Context context, ArrayList<PurchasedProduct> purchasedProducts) {
        this.context = context;
        this.purchasedProducts = purchasedProducts;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        PurchasedProduct product = purchasedProducts.get(position);
        Log.d("HistoryAdapter", "Position: " + position + ", Product: " + product);
        if (product != null) {
            holder.Product.setText(product.getName());
            String price = String.valueOf(product.getPrice());
            holder.Price.setText(price);
            holder.Quantity.setText(String.valueOf(product.getQuantity()));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // Set the title of the dialog
                builder.setMessage("Purchased Product: " + product.getName()
                        + "\n"
                        + "Price: " + product.getPrice()
                        +"\n"
                        +"Purchase Date: "
                        +product.getTimestamp());  // Set the message of the dialog
                builder.setCancelable(false);
                // Set a positive button and its click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform action when the positive button is clicked
                    }
                });
                // Create the dialog
                alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return purchasedProducts.size();
    }

    class HistoryHolder extends RecyclerView.ViewHolder{
        TextView Product,Price,Quantity;
        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            Product = itemView.findViewById(R.id.product_name);
            Quantity = itemView.findViewById(R.id.quantity);
            Price = itemView.findViewById(R.id.price);
        }
    }
}
