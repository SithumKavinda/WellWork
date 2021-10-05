/*References
* Custom Progress bar - https://github.com/ybq/Android-SpinKit
*/


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

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Firebase Authentication
    public FirebaseAuth mAuth;

    Button submit;
    EditText email, password;
    TextView register;
    Dialog confirmDialog, registerDialog;
    SpinKitView progressBar_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.btn_login);
        email = findViewById(R.id.inputMail);
        password = findViewById(R.id.inputPassword);
        register = findViewById(R.id.Register);
        progressBar_login = findViewById(R.id.pb_login);
        mAuth = FirebaseAuth.getInstance();

        //Confirm Dialog view
        confirmDialog = new Dialog(MainActivity.this);
        confirmDialog.setContentView(R.layout.custom_dialog);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            confirmDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }

        confirmDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        confirmDialog.setCancelable(false);
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
                confirmDialog.dismiss();
            }
        });

        //Register Dialog view
        registerDialog = new Dialog(MainActivity.this);
        registerDialog.setContentView(R.layout.register_card);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            registerDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_back_reg));
        }

        registerDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        registerDialog.setCancelable(true);
        registerDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        EditText FullName, ContactNo, Email, Password;
        FullName = registerDialog.findViewById(R.id.tv_name);
        ContactNo = registerDialog.findViewById(R.id.tv_contactNo);
        Email = registerDialog.findViewById(R.id.tv_email);
        SpinKitView pb = registerDialog.findViewById(R.id.reg_progressBar);
        Password = registerDialog.findViewById(R.id.tv_password);
        Button cardRegisterBtn = registerDialog.findViewById(R.id.btn_register);

        cardRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = FullName.getText().toString();
                String phone = ContactNo.getText().toString();
                String regMail = Email.getText().toString();
                String regPassword = Password.getText().toString();

                //Validations
                if (name.isEmpty()){
                    FullName.setError("Full name is required!");
                    FullName.requestFocus();
                    return;
                }

                if (phone.isEmpty()){
                    ContactNo.setError("Contact number is required!");
                    ContactNo.requestFocus();
                    return;
                }

                if(phone.length() < 10){
                    ContactNo.setError("Contact number should have 10 digits");
                    ContactNo.requestFocus();
                    return;
                }

                if (regMail.isEmpty()){
                    Email.setError("Email address is required!");
                    Email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(regMail).matches()){
                    Email.setError("Invalid Email Address");
                    Email.requestFocus();
                    return;
                }

                if (regPassword.isEmpty()){
                    Password.setError("Password is required!");
                    Password.requestFocus();
                    return;
                }

                if(regPassword.length() < 6){
                    Password.setError("Password should contain more than 8 elements");
                    Password.requestFocus();
                    return;
                }

                if(!name.isEmpty() && !phone.isEmpty() && !regMail.isEmpty() && !regPassword.isEmpty() && phone.length() >= 10 && Patterns.EMAIL_ADDRESS.matcher(regMail).matches() && regPassword.length() >= 6){

                    //Show progress bar until user details goes to the database
                    pb.setVisibility(View.VISIBLE);

                    //Send data to the Firebase Authentication
                    mAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        users user = new users(FullName.getText().toString(), ContactNo.getText().toString(), Email.getText().toString());

                                        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");
                                        database
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(MainActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                                pb.setVisibility(View.GONE);
                                                registerDialog.dismiss();

                                                //Redirected to Login page here

                                            }
                                            else{
                                                Toast.makeText(MainActivity.this, "Register Failed!\n"+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                pb.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                    }
                                }
                            });
                }
            }
        });

        //Submit button onclick
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go inside the applications

                if(validateEmail(email) && validatePassword(password)) {

                    progressBar_login.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        openHomePage();
                                        progressBar_login.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "Check Credentials", Toast.LENGTH_LONG).show();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar_login.setVisibility(View.GONE);
                                }
                            });
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
            password.requestFocus();
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
                email.requestFocus();
            }
            return false;
        }
    }
}