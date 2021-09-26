package com.edu.wellwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    ArrayList<Orders> list;
    DBCart crud = new DBCart();

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

        TextView itemName, itemQty, unitPrice, totalPrice;
        Button btn_delete;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.tv_itemName);
            itemQty = itemView.findViewById(R.id.tv_qty);
            unitPrice = itemView.findViewById(R.id.tv_unitPrice);
            totalPrice = itemView.findViewById(R.id.tv_totalPrice);
            btn_delete = itemView.findViewById(R.id.btn_remove);

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String medName = itemName.getText().toString();
                    DBCart crud = new DBCart();
                    crud.removeOrder(view, medName);
                }
            });
        }
    }
}
