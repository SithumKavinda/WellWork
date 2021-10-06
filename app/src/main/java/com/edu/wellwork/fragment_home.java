package com.edu.wellwork;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_home extends Fragment {

    //Database properties
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users").child(userID);

    Context context;
    Orders order;

    //UI properties
    TextView name;
    SpinKitView pb_home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        context = getContext();

        //UI components binding
        name = view.findViewById(R.id.tv_callName2);
        pb_home = view.findViewById(R.id.pb_home);

        //Show progress bar when loading name
        pb_home.setVisibility(View.VISIBLE);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Progress bar disappears
                pb_home.setVisibility(View.GONE);

                order = snapshot.getValue(Orders.class);
                name.setText(order.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
