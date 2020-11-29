package com.example.sdl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class WatchmenEntryApproval extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchmen_entry_approval);

        b1=findViewById(R.id.sendRequestBtn);
        b2=findViewById(R.id.checkStatus);

        Intent intent1=getIntent();
        final String buildingName=intent1.getStringExtra("buildingName");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WatchmenEntryApproval.this,GuestApproval.class);
                intent.putExtra("buildingName",buildingName);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),CheckStatus.class);
                intent1.putExtra("buildingName",buildingName);
                startActivity(intent1);
            }
        });
    }
}
