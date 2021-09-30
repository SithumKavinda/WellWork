package com.edu.wellwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.MyViewHolder> {

    Context context;
    ArrayList<Orders> list;

    public Cart_adapter(Context context, ArrayList<Orders> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Orders order = list.get(position);
        holder.iname.setText(order.getName());
        holder.qty.setText(order.getQty());
        holder.unitPrice.setText(order.getUnitPrice());
        holder.totPrice.setText(order.getTotal());
        
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                /*FirebaseDatabase.getInstance().getReference("Prescription").child(id).setValue(null);*/
                //Add new medicines into the database
                //Create the same to the one more child positions
                //Add animations to the Cart page
                //Add start page with logo when start the application
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView iname, qty, unitPrice, totPrice, grandTot;
        Button btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iname = itemView.findViewById(R.id.tv_itemName);
            qty = itemView.findViewById(R.id.tv_qty);
            unitPrice = itemView.findViewById(R.id.tv_unitPrice);
            totPrice = itemView.findViewById(R.id.tv_totalPrice);
            grandTot = itemView.findViewById(R.id.txt_finalPrice);
            btn_delete = itemView.findViewById(R.id.btn_remove);
        }
    }
}
