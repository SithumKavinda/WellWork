package com.edu.wellwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText name, tel, age, email, password;
    View main;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            name = findViewById(R.id.tv_name);
            tel = findViewById(R.id.tv_telNo);
            age = findViewById(R.id.tv_age);
            email = findViewById(R.id.tv_email);
            password = findViewById(R.id.tv_password);
            btn_register = findViewById(R.id.btn_register);
            main = findViewById(R.id.view4);

            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validateName(name) && validateTel(tel) && validateAge(age) && validateMail(email) && validatePassword(password)){
                        registerUser(name.getText().toString(), tel.getText().toString(), age.getText().toString(),
                        email.getText().toString(), password.getText().toString());
                        openLogin();
                    }
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
        }
    }

    //Navigation
    public void openLogin(){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
    }

    //Form Validations
    private boolean validateName(EditText name) {
        String value = name.getText().toString();

        if (value.isEmpty()){
            name.setError("Field Cannot be empty");
            return false;
        }
        else if(!value.matches("^[A-Z a-z]+$")){
            name.setError("Name can contain letters only");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }

    private boolean validateTel(EditText tel) {
        String value = tel.getText().toString();

        if (value.isEmpty()){
            tel.setError("Field Cannot be empty");
            return false;
        }
        else if(!Patterns.PHONE.matcher(value).matches()){
            tel.setError("Invalid Contact number");
            return false;
        }
        else if(value.length() < 10 || value.length() > 10){
            tel.setError("Invalid Contact number");
            return false;
        }
        else{
            tel.setError(null);
            return true;
        }
    }

    private boolean validateAge(EditText age) {
        String value = age.getText().toString();

        if(value.isEmpty()){
            age.setError("Field cannot be empty");
            return false;
        }
        else if(Integer.parseInt(value) > 110 || Integer.parseInt(value) < 10){
            age.setError("Invalid age");
            return false;
        }
        else if(!value.matches("[0-9]+")){
            age.setError("Age can only contain number");
            return false;
        }
        else{
            age.setError(null);
            return true;
        }
    }

    private boolean validateMail(EditText email){
        String value = email.getText().toString();

        if(value.isEmpty()){
            email.setError("Field Cannot be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            email.setError("Invalid Email address");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword(EditText password){
        String value = password.getText().toString().trim();
        String regexRules = "^" +
                /*
                "(?=.*[0-9])" +          //at least 1 digit
                "(?=.*[a-z])" +          //at least 1 lower case letter
                "(?=.*[A-Z])" +          //at least 1 upper case letter
                 */
                "(?=.*[a-zA-Z])" +       //any letter
                //"(?=.*[@#$%^&+=])" +     //at least 1 special character
                "(?=\\S+$)" +            //no white spaces
                ".{4,}"+                //at least 4 characters
                "$";

        if(value.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else if(!value.matches(regexRules)){
            password.setError("Password shouldn't contain whitespaces & Must have more than 4 characters");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }

    public boolean registerUser(String name, String pNo, String age, String email, String password){

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User");
        database.setValue(users.class).addOnSuccessListener(success ->
        {
            Toast.makeText(register.this, "Registered Succefully!", Toast.LENGTH_SHORT).show();
        });

        return true;
    }
}