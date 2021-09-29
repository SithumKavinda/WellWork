package com.edu.wellwork;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText email, password;
    TextView register;
    Dialog confirmDialog, registerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.btn_login);
        email = findViewById(R.id.inputMail);
        password = findViewById(R.id.inputPassword);
        register = findViewById(R.id.Register);

        //Confirm Dialog view
        confirmDialog = new Dialog(MainActivity.this);
        confirmDialog.setContentView(R.layout.custom_dialog);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            confirmDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }

        confirmDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        confirmDialog.setCancelable(true);
        confirmDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button dialog_confirm = confirmDialog.findViewById(R.id.confirm);
        Button dialog_cancel = confirmDialog.findViewById(R.id.cancel);

        dialog_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.dismiss();
                registerDialog.show();
            }
        });

        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "User pressed cancel", Toast.LENGTH_SHORT).show();
                confirmDialog.dismiss();
            }
        });

        //Register Dialog view
        registerDialog = new Dialog(MainActivity.this);
        registerDialog.setContentView(R.layout.register_card);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            registerDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }

        registerDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        registerDialog.setCancelable(true);
        registerDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button cardRegisterBtn = registerDialog.findViewById(R.id.btn_register);

        cardRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Register Button Pressed", Toast.LENGTH_SHORT).show();
                registerDialog.dismiss();
            }
        });



        //Submit button onclick
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go inside the applications
                if(validateEmail(email) && validatePassword(password)) {

                    if(validateCredentials(email.getText().toString(), password.getText().toString())){
                        openHomePage();
                    }
                }
            }
        });

        //New user register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.show();
            }
        });
    }

    //Form navigation methods
    private void openHomePage() {
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    //Form validations
    private boolean validatePassword(EditText password) {
        String passwordInput = password.getText().toString();
        
        if(!passwordInput.isEmpty()){
            password.setError(null);
            return true;
        }
        else{
            password.setError("This field cannot be empty");
            return false;
        }
    }

    private boolean validateEmail(EditText email) {
        String emailInput = email.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError(null);
            return true;
        }
        else {
            if(emailInput.isEmpty()){
                email.setError("This field cannot be empty");
            }
            else{
                email.setError("Invalid Email address");
            }
            return false;
        }
    }

    public boolean validateCredentials(String email, String password){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getValue(users.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Connection Error!", Toast.LENGTH_SHORT).show();
            }
        });

        users user = new users();

        if(email == user.getUsername().toString() && password == user.getPassword().toString()){
            return true;
        }

        return false;
    }
}