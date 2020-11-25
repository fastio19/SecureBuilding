package com.example.sdl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    TextView t1,t2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        t1=findViewById(R.id.full_name);
        t2=findViewById(R.id.phone_no);
        b1=findViewById(R.id.flat);
        Intent intent=getIntent();
        String Name=intent.getStringExtra("name");
        String PhoneNo=intent.getStringExtra("phoneNo");
        PhoneNo="+91 "+PhoneNo;
        t1.setText(Name);
        t2.setText(PhoneNo);


    }

}
