package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WatchmenLogin extends AppCompatActivity {
    Button signup, login;
    TextInputLayout watchmenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchmen_login);
        signup = findViewById(R.id.Watchmen_SignUpBtn);
        login = findViewById(R.id.Watchmen_LoginBtn);
        watchmenId = findViewById(R.id.WatchmenId);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WatchmenSignUp.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEnteredWatchmenId = watchmenId.getEditText().getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("watchmens");
                reference.addValueEventListener(new ValueEventListener(){

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("watchmenId").getValue().equals(userEnteredWatchmenId)) {
                                String temp_name = ds.child("name").getValue(String.class);
                                String temp_buildingName = ds.child("buildingName").getValue(String.class);
                                Log.i("Our Info", userEnteredWatchmenId);
                                Log.i("Our Info", temp_name);
                                Log.i("Our Info", temp_buildingName);
                                Toast.makeText(getApplicationContext(), "Login successful !!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), WatchmenDashboard.class);
                                intent.putExtra("name", temp_name);
                                intent.putExtra("buildingName", temp_buildingName);
                                intent.putExtra("watchmenId", userEnteredWatchmenId);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Login failed !!",Toast.LENGTH_LONG).show();

                    }
                    });
                }
            });

        }
    }