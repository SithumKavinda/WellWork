package com.edu.wellwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                    //Navigate to the Form home
                    openHomePage();
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
}