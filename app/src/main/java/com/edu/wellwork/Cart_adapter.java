package com.edu.wellwork;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.MyViewHolder> {

    SpinKitView pbDelete;
    Dialog dialogDelete;
    Context context;
    ArrayList<Orders> list;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Prescription");


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

        holder.orderCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Toast.makeText(context, "Pressed: " + order.getName(), Toast.LENGTH_SHORT).show();

                //Show custom dialog box
                dialogDelete = new Dialog(context);
                dialogDelete.setContentView(R.layout.custom_dialog);

                //Dialog box items
                ImageView dialog_img = dialogDelete.findViewById(R.id.msgbx_register);
                TextView dialog_text = dialogDelete.findViewById(R.id.msgbx_description);
                Button dialog_ok = dialogDelete.findViewById(R.id.confirm);
                Button dialog_cancel = dialogDelete.findViewById(R.id.cancel);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    dialogDelete.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.dialog_back_reg));
                }

                //Appearance
                dialogDelete.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialogDelete.setCancelable(false);
                dialogDelete.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog_text.setText("Are you sure you want to delete " + order.getName() + " ?");
                //dialog_img.setBackground

                //Show dialod box
                dialogDelete.show();

                //Dialog button actions
                dialog_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Start to show progress bar
                        pbDelete.setVisibility(View.VISIBLE);

                        //Get the ID of the user that currently sign in to the application
                        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        //Remove Item
                        dbRef.child(userID).child(order.getName()).removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        pbDelete.setVisibility(View.GONE);
                                        Toast.makeText(context, order.getName() + " Removed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                dialog_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogDelete.dismiss();
                    }
                });

                return false;
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Insert dummy data for Presentation
                /*insertMedicines("Vitamin D", "12", "10.00", "120.00");
                insertMedicines("Vitamin C", "10", "50.00", "500.00");
                insertMedicines("Vitamin B", "30", "20.00", "600.00");*/
            }
        });
    }

    //Method for insert new user to the database

      /*private void insertMedicines(String itemName, String qty, String unitPrice, String totalPrice) {
        Orders addOrder = new Orders(itemName, qty, unitPrice, totalPrice);

        String key = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Add to the DB
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Prescription").child(key);

        String medId = itemName;

        db.child(medId).setValue(addOrder);
    }*/

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
