package com.edu.wellwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addPrescription extends AppCompatActivity {
    EditText txtMed1, txtMed2, txtMed3, txtMed4, textMed5;
    Button btnAdd;
    DatabaseReference DbRef;
    Prescription pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        txtMed1 = (EditText) findViewById(R.id.et_med1);
        txtMed2 = (EditText) findViewById(R.id.et_med2);
        txtMed3 = (EditText) findViewById(R.id.et_med3);
        txtMed4 = (EditText) findViewById(R.id.et_med4);
        textMed5 = (EditText) findViewById(R.id.et_med5);
        btnAdd = (Button)findViewById(R.id.btn_prescriptionSubmit);

        pres = new Prescription();
        DbRef = FirebaseDatabase.getInstance().getReference().child("Prescription");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pres.setMed1(txtMed1.getText().toString().trim());
                pres.setMed2(txtMed2.getText().toString().trim());
                pres.setMed3(txtMed3.getText().toString().trim());
                pres.setMed4(txtMed4.getText().toString().trim());
                pres.setMed5(textMed5.getText().toString().trim());

                DbRef.push().setValue(pres);
                Toast.makeText(addPrescription.this, "Added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}