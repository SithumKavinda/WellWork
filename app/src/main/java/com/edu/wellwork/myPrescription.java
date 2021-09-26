package com.edu.wellwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myPrescription extends AppCompatActivity {
    TextView m1, m2, m3, m4, m5;
    Button btnView, btnEdit, btnDelete;
    DatabaseReference DbRef2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prescription);

        m1 = (TextView) findViewById(R.id.tv_medi1);
        m2 = (TextView) findViewById(R.id.tv_medi2);
        m3 = (TextView) findViewById(R.id.tv_medi3);
        m4 = (TextView) findViewById(R.id.tv_medi4);
        m5 = (TextView) findViewById(R.id.tv_medi5);

        btnView = (Button) findViewById(R.id.btn_viewP);
        btnEdit = (Button) findViewById(R.id.btn_editP);
        btnDelete = (Button) findViewById(R.id.btn_deleteP);

        //View
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbRef2 = FirebaseDatabase.getInstance().getReference().child("Prescription").child("-MkWDI1v55zkQDdiGa8T");
                DbRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String medicine1 = snapshot.child("med1").getValue().toString();
                        String medicine2 = snapshot.child("med2").getValue().toString();
                        String medicine3 = snapshot.child("med3").getValue().toString();
                        String medicine4 = snapshot.child("med4").getValue().toString();
                        String medicine5 = snapshot.child("med5").getValue().toString();

                        m1.setText(medicine1);
                        m2.setText(medicine2);
                        m3.setText(medicine3);
                        m4.setText(medicine4);
                        m5.setText(medicine5);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        //Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbRef2 = FirebaseDatabase.getInstance().getReference().child("Prescription").child("-MkWDI1v55zkQDdiGa8T");
                DbRef2.removeValue();
                Toast.makeText(myPrescription.this, "Prescription deleted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myPrescription.this, editPrescription.class);
                startActivity(intent);
            }
        });
    }
}