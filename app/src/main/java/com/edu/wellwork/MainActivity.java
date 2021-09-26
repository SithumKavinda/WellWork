package com.edu.wellwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.btn_login);
        email = findViewById(R.id.inputMail);
        password = findViewById(R.id.inputPassword);
        register = findViewById(R.id.Register);

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

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you need to Register?")
                        .setIcon(android.R.drawable.ic_menu_info_details)

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                openRegisterPage();
                            }
                        })

                        .setNegativeButton(android.R.string.no,null).show();
            }
        });
    }

    //Form navigation methods
    private void openHomePage() {
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    private void openRegisterPage(){
        Intent register = new Intent(this, register.class);
        startActivity(register);
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