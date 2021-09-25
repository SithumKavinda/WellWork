package com.edu.wellwork;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment_cart extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference database;
    OrderAdapter adapter;
    ArrayList<Orders> list;
    DBCart crud = new DBCart();
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = container.getContext();

        try{
            //Read data from the firebase database
            //crud.ReadOrders();

            recyclerView = view.findViewById(R.id.ordersList);
            database = FirebaseDatabase.getInstance().getReference("Orders");
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));     //Error Error Error

            list = new ArrayList<>();
            adapter = new OrderAdapter(getActivity(), list);    //Error Error Error
            recyclerView.setAdapter(adapter);

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
        catch (Exception ex){
            Toast.makeText(context, "Error: "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
