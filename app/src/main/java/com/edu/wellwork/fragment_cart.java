package com.edu.wellwork;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fragment_cart extends Fragment {

    RecyclerView recyclerView;
    ProgressBar pb_cart;
    OrderAdapter adapter;
    ArrayList<Orders> list;
    DBCart crud = new DBCart();
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = container.getContext();

        pb_cart = view.findViewById(R.id.pb_cart);

        try{
            recyclerView = view.findViewById(R.id.ordersList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            list = new ArrayList<>();
            adapter = new OrderAdapter(getActivity(), list);
            recyclerView.setAdapter(adapter);

            crud.ReadOrder(list, adapter, pb_cart);

        }
        catch (Exception ex){
            Toast.makeText(context, "Error: "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
