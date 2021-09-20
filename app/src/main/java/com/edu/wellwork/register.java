package com.edu.wellwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText fname, lname, telno, dob;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.tv_fName);
        lname = findViewById(R.id.tv_lName);
        telno = findViewById(R.id.tv_telNo);
        dob = findViewById(R.id.tv_dob);
        datePicker = findViewById(R.id.dob);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        String status = Integer.toString(dob.getVisibility());

        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}