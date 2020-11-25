package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class Login extends AppCompatActivity {
    Button callSignUp, login_Btn, forgetPassword;
    TextInputLayout email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        callSignUp = findViewById(R.id.signup_screen);
        login_Btn = findViewById(R.id.LoginBtn);
        email = (TextInputLayout) findViewById(R.id.login_email);
        password = (TextInputLayout) findViewById(R.id.login_password);
        forgetPassword = findViewById(R.id.ForgetPassword);
        mAuth = FirebaseAuth.getInstance();
        forgetPassword.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Login.this,ForgetPassword.class);
                    startActivity(intent);
                    finish();
                }
            }
        );
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();

            }
        });
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword())
                    return;
                else
                    isUser();
            }
        });
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private void isUser() {
        final String userEnteredEmail = email.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        mAuth.signInWithEmailAndPassword(userEnteredEmail, userEnteredPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                if (ds.child("email").getValue().equals(userEnteredEmail)) {

                                    String temp_name = ds.child("name").getValue(String.class);
                                    String temp_email = ds.child("email").getValue(String.class);
                                    String temp_password = ds.child("password").getValue(String.class);
                                    String temp_username = ds.child("username").getValue(String.class);
                                    String temp_phoneNo = ds.child("phoneNo").getValue(String.class);
                                    Log.i("Our Info", temp_username);
                                    Log.i("Our Info", temp_password);
                                    Log.i("Our Info", temp_phoneNo);
                                    Log.i("Our Info", temp_name);
                                    Log.i("Our Info", temp_email);
                                    Toast.makeText(getApplicationContext(), "Login successful !!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login.this, Dashboard.class);
                                    intent.putExtra("name", temp_name);
                                    intent.putExtra("username", temp_username);
                                    intent.putExtra("password", temp_password);
                                    intent.putExtra("phoneNo", temp_phoneNo);
                                    intent.putExtra("email", temp_email);
                                    startActivity(intent);
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
