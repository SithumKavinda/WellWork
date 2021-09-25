package com.edu.wellwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    ArrayList<Orders> list;

    public OrderAdapter(Context context, ArrayList<Orders> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Orders order = list.get(position);
        //holder.id.setText(order.getOrderId());
        holder.itemName.setText(order.getItemName());
        holder.itemQty.setText(order.getQty());
        holder.unitPrice.setText(order.getUnitPrice());
        holder.totalPrice.setText(order.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView /*id, */itemName, itemQty, unitPrice, totalPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            /*id = itemView.findViewById(R.id.tv_id);*/
            itemName = itemView.findViewById(R.id.tv_itemName);
            itemQty = itemView.findViewById(R.id.tv_qty);
            unitPrice = itemView.findViewById(R.id.tv_unitPrice);
            totalPrice = itemView.findViewById(R.id.tv_totalPrice);
        }
    }
}
