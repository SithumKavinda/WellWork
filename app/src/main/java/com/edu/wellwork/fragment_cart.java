package com.edu.wellwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_cart extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        onButtonClicked(view);
        return view;
    }

    protected void onButtonClicked(View view){
        System.out.println("CLICKED");
        Button cardButton = (Button)view.findViewById(R.id.pay);
        cardButton.setOnClickListener(view1 -> {
            Intent cardActivity = new Intent(view.getContext(), CardManagment.class);
            startActivity(cardActivity);
        });
    }
}
