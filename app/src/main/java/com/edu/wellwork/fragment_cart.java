package com.edu.wellwork;

import android.content.Context;
import android.content.Intent;
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

    /*Defining variables for connect UI and java classes*/
    TextView txt_grand_total;
    Button btn_pay;
    SpinKitView pb_cart;        //Progress bar
    RecyclerView order_list;

    /*Variables that are needed to implement the scenario*/
    Cart_adapter adapter;       //Adapter class for recycler view
    DatabaseReference database; //Database
    ArrayList<Orders> list;     //ArrayList for store the model class objects which are filled by firebase database data
    Context context;            //Context of this java class
    GrandTotal gt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Open this class's UI as a fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = container.getContext();

        //Components declarations
        btn_pay = view.findViewById(R.id.btn_order);
        txt_grand_total = view.findViewById(R.id.txt_finalPrice);
        pb_cart = view.findViewById(R.id.pb_cart);
        order_list = view.findViewById(R.id.layout_item);

        //Getting User ID of currently logged in user
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        //Database Connection
        database = FirebaseDatabase.getInstance().getReference().child("Prescription").child(userid);

        //Recycler view Fixing
        order_list.setHasFixedSize(true);
        order_list.setLayoutManager(new LinearLayoutManager(context));

        //Define array and set this page context and arrayList in to the adapter class constructor
        list = new ArrayList<>();
        adapter = new Cart_adapter(context, list, pb_cart);
        order_list.setAdapter(adapter);

        //Set the visibility of progress bar to visible
        pb_cart.setVisibility(View.VISIBLE);

        //Retrieve data from firebase db and insert them into arrayList as model class objects
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Iterate the function to read all the data until the last data of the node
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Orders order = dataSnapshot.getValue(Orders.class);
                    list.add(order);
                }
                //Notify adapter that the data retrieve has done
                adapter.notifyDataSetChanged();

                //Set the visibility of Progress bar as GONE
                pb_cart.setVisibility(View.GONE);

                //Grand total
                int x = list.size() - 1;

                gt = new GrandTotal();
                String grandTotal = gt.countGtot(list, x);

                txt_grand_total.setText(grandTotal);    //Set the Grand total to the Cart UI
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });

        //This onClickListener will proceed user to Payment UI
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CardManagment.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
