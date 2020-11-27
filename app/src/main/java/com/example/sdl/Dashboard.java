package com.example.sdl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
    TextView t1,t2,t3,t4;
    private CardView residents,noticeBoard,acceptGuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        t1=findViewById(R.id.full_name);
        t2=findViewById(R.id.building_name);
        t3=findViewById(R.id.flat_no);
        t4=findViewById(R.id.phone_no);
        final Intent intent=getIntent();
        String Name=intent.getStringExtra("name");
        String BuildingName=intent.getStringExtra("building_name");
        String FlatNo=intent.getStringExtra("flat_no");
        String PhoneNo=intent.getStringExtra("phone_no");
        PhoneNo="+91 "+PhoneNo;
        FlatNo="Flat "+FlatNo;
        t1.setText(Name);
        t2.setText(BuildingName);
        t3.setText(FlatNo);
        t4.setText(PhoneNo);
        residents=(CardView)findViewById(R.id.Residents);
        noticeBoard=(CardView)findViewById(R.id.NoticeBoard);
        acceptGuest=(CardView)findViewById(R.id.accept_guest);
        residents.setOnClickListener(this);
        noticeBoard.setOnClickListener(this);
        acceptGuest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String name=t1.getText().toString();
        String buildingName=t2.getText().toString();
        switch (v.getId()){
            case R.id.Residents:intent=new Intent(this,Residents.class);
                                  intent.putExtra("buildingName",buildingName);startActivity(intent);break;
            case R.id.NoticeBoard:intent=new Intent(this,NoticeBoard.class);startActivity(intent);break;
            case R.id.accept_guest:intent=new Intent(this,AcceptGuest.class);
                                    intent.putExtra("name",name);
                                    startActivity(intent);break;
            default:break;
        }
    }
}
