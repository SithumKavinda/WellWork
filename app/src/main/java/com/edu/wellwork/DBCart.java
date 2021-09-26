package com.edu.wellwork;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DBCart {

    public DatabaseReference database;
    ProgressBar pb;

    public void ReadOrder(ArrayList<Orders> list, OrderAdapter adapter, ProgressBar pb){
        this.pb = pb;
        database = FirebaseDatabase.getInstance().getReference("Orders");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Orders order = dataSnapshot.getValue(Orders.class);
                    list.add(order);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void removeOrder(View view, String medName){
        database = FirebaseDatabase.getInstance().getReference("Orders").child(medName);
        database.removeValue().addOnSuccessListener(yes->
        {
            Toast.makeText(view.getContext(), medName+" Parent "+ database.getRoot().toString(), Toast.LENGTH_SHORT).show();
        });
    }
}
