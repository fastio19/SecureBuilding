package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WatchmenSignUp extends AppCompatActivity {
    TextInputLayout name,buildingName,watchmenId;
    Button register,login;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchmen_sign_up);
        name=findViewById(R.id.WatchmenSignUp_Name);
        buildingName=findViewById(R.id.WatchmenSignUp_BuildingName);
        watchmenId=findViewById(R.id.WatchmenSignUp_WatchmenID);
        register=findViewById(R.id.WatchmenSignUp_RegisterBtn);
        login=findViewById(R.id.WatchmenSignUp_LoginBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference();
                if(!validateName() | !validateWatchmenId() | !validateBuildingName() ){
                    return;
                }
                final String temp_name=name.getEditText().getText().toString();
                final String temp_watchmenId=watchmenId.getEditText().getText().toString();
                final String temp_buildingName=buildingName.getEditText().getText().toString();
                WatchmenHelperClass watchmenHelperClass=new WatchmenHelperClass(temp_name,
                        temp_buildingName,temp_watchmenId);
                reference.child("watchmens").child(temp_watchmenId).setValue(watchmenHelperClass);
                Toast.makeText(WatchmenSignUp.this,"Registered Successfully !!",
                        Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),WatchmenDashboard.class);
                intent.putExtra("name", temp_name);
                intent.putExtra("buildingName", temp_buildingName);
                intent.putExtra("watchmenId",temp_watchmenId);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),WatchmenLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private boolean validateBuildingName() {
        String val=buildingName.getEditText().getText().toString();
        if(val.isEmpty()){
            buildingName.setError("Field cannot be empty");
            return false;
        }
        else{
            buildingName.setError(null);
            buildingName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateWatchmenId() {
        String val=watchmenId.getEditText().getText().toString();
        if(val.isEmpty()){
            watchmenId.setError("Field cannot be empty");
            return false;
        }
        else{
            watchmenId.setError(null);
            watchmenId.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateName() {
        String val=name.getEditText().getText().toString();
        if(val.isEmpty()){
            name.setError("Field cannot be empty");
            return false;
        }
        else{
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }
}
