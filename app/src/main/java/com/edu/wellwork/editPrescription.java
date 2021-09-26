package com.edu.wellwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class editPrescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prescription);

        updatePrescription up = new updatePrescription();

        final EditText et_eM1 = findViewById(R.id.et_eM1);
        final EditText et_eM2 = findViewById(R.id.et_eM2);
        final EditText et_eM3 = findViewById(R.id.et_eM3);
        final EditText et_eM4 = findViewById(R.id.et_eM4);
        final EditText et_eM5 = findViewById(R.id.et_eM5);
        Button btnUpdate = findViewById(R.id.btn_eSave);

        btnUpdate.setOnClickListener(view -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("med1",et_eM1.getText().toString());
            hashMap.put("med2",et_eM2.getText().toString());
            hashMap.put("med3",et_eM3.getText().toString());
            hashMap.put("med4",et_eM4.getText().toString());
            hashMap.put("med5",et_eM5.getText().toString());

            up.update("-MkWDI1v55zkQDdiGa8T", hashMap).addOnSuccessListener(suc ->{
                Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
            finish();
        });
    }
}