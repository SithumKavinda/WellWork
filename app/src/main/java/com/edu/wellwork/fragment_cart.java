package com.edu.wellwork;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment_cart extends Fragment {

    TextView txt_grand_total;
    Button btn_pay;
    SpinKitView pb_cart;
    RecyclerView order_list;
    Cart_adapter adapter;
    DatabaseReference database;
    ArrayList<Orders> list;
    Context context;
    GrandTotal gt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = container.getContext();

        btn_pay = view.findViewById(R.id.btn_order);
        txt_grand_total = view.findViewById(R.id.txt_finalPrice);
        pb_cart = view.findViewById(R.id.pb_cart);
        order_list = view.findViewById(R.id.layout_item);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        database = FirebaseDatabase.getInstance().getReference().child("Prescription").child(userid);


        order_list.setHasFixedSize(true);
        order_list.setLayoutManager(new LinearLayoutManager(context));

        list = new ArrayList<>();
        adapter = new Cart_adapter(context, list);
        order_list.setAdapter(adapter);

        pb_cart.setVisibility(View.VISIBLE);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Orders order = dataSnapshot.getValue(Orders.class);
                    list.add(order);
                }
                adapter.notifyDataSetChanged();
                pb_cart.setVisibility(View.GONE);

                //Grand total
                int x = list.size() - 1;

                gt = new GrandTotal();
                String grandTotal = gt.countGtot(list, x);

                txt_grand_total.setText(grandTotal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
        
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Proceed to Payment page", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
