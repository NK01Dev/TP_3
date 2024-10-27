package com.example.tp1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private ImageButton BtnBack,BtnSubReg;
    private TextInputEditText TxtName,TxtEmail,TxtPswd;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private ProgressBar progressBar;
    private String UserName,Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        BtnBack=findViewById(R.id.id_BtnBackReg);
        BtnSubReg=findViewById(R.id.id_BtnSubReg);
        TxtName=findViewById(R.id.id_Name_reg);
        TxtEmail=findViewById(R.id.id_email_reg);
        TxtPswd=findViewById(R.id.id_password_reg);
        progressBar=findViewById(R.id.id_progressBar);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        //  getSupportActionBar().setTitle("Register");
        BtnSubReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }
    private void createUser() {
        UserName=TxtName.getText().toString();
        Email=TxtEmail.getText().toString();
        Password=TxtPswd.getText().toString();
        if (TextUtils.isEmpty(UserName)){
            TxtName.setError("User Name cannot be empty");
            TxtName.requestFocus();}
        else if (TextUtils.isEmpty(Email)){
            TxtEmail.setError("User Name cannot be empty");
            TxtEmail.requestFocus();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            TxtEmail.setError("Valid email is required");
            TxtEmail.requestFocus();
        }else if (TextUtils.isEmpty(Password)){
            TxtPswd.setError("Password is required ");
            TxtPswd.requestFocus();
        }  else if (Password.length()<6 ){
            TxtPswd.setError("Password too weak");
            TxtPswd.requestFocus();
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            registerUSer(UserName,Email,Password);
        }

    }
    private void registerUSer(String userName, String email,  String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    firebaseUser=task.getResult().getUser();
                    // firebaseUser= mAuth.getCurrentUser();
                    // send verification to email
                    firebaseUser.sendEmailVerification();

                    DatabaseReference newUser=databaseReference.child(firebaseUser.getUid());
                    newUser.child("User Name").setValue(userName);
                    newUser.child("Email Address").setValue(email);
                    newUser.child("Password").setValue(password);


                    Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class)
                    );
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}