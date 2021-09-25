package com.edu.wellwork;

import com.google.firebase.database.DatabaseReference;

public class DBCart {

    public DatabaseReference database;

    /*public void ReadOrders(){
        database = FirebaseDatabase.getInstance().getReference().child("Orders").child("1");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String itemName = snapshot.child("itemName").getValue().toString();
                String qty = snapshot.child("qty").getValue().toString();
                String unitPrice = snapshot.child("unitPrice").getValue().toString();
                String totalPrice = snapshot.child("totalPrice").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
