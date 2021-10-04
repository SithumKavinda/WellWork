package com.edu.wellwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_prescription extends Fragment {
    Button btnAdd, btnMyP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prescription,container,false);

        btnAdd = view.findViewById(R.id.btn_addPrescription);
        btnMyP = view.findViewById(R.id.btn_myPrescription);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),addPrescription.class);
                startActivity(intent);
            }
        });

        btnMyP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),myPrescription.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
