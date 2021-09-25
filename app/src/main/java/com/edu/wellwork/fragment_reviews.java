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

public class fragment_reviews extends Fragment {
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_reviews,container,false);
        button = (Button) view.findViewById(R.id.buttonAddReview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityAddReview();
            }
        });
        return view;
    }

    public void openActivityAddReview() {
        Intent intent = new Intent(getActivity(), AddReview.class);
        startActivity(intent);
    }
}
