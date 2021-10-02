package com.edu.wellwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.MyViewHolder> {

    SpinKitView pbDelete;
    Context context;
    ArrayList<Orders> list;
    DatabaseReference reference;


    public Cart_adapter(Context context, ArrayList<Orders> list, SpinKitView pbDelete) {
        this.context = context;
        this.list = list;
        this.pbDelete = pbDelete;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Orders order = list.get(position);
        holder.iname.setText(order.getName());
        holder.qty.setText(order.getQty());
        holder.unitPrice.setText(order.getUnitPrice());
        holder.totPrice.setText(order.getTotal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView iname, qty, unitPrice, totPrice;
        Button btn_delete;
        LinearLayout orderCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iname = itemView.findViewById(R.id.tv_itemName);
            qty = itemView.findViewById(R.id.tv_qty);
            unitPrice = itemView.findViewById(R.id.tv_unitPrice);
            totPrice = itemView.findViewById(R.id.tv_totalPrice);
            btn_delete = itemView.findViewById(R.id.btn_remove);
            orderCard = itemView.findViewById(R.id.layout_order);
        }
    }
}
