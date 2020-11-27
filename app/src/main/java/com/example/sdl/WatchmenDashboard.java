package com.example.sdl;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WatchmenDashboard extends AppCompatActivity implements View.OnClickListener{
    TextView t1,t2;
    CardView parkingProblem,guestApproval;
    String Name,BuildingName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchmen_dashboard);
        t1=findViewById(R.id.Dashboard_WatchmenName);
        t2=findViewById(R.id.Dashboard_WatchmenBuildingName);
        parkingProblem=(CardView)findViewById(R.id.ParkingProblem);
        guestApproval=(CardView)findViewById(R.id.guest_approval);
        final Intent intent=getIntent();
        Name=intent.getStringExtra("name");
        BuildingName=intent.getStringExtra("buildingName");
        t1.setText(Name);
        t2.setText(BuildingName);
        parkingProblem.setOnClickListener(this);
        guestApproval.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.ParkingProblem:intent=new Intent(this,ParkingProblem.class);
                                        startActivity(intent);break;
            case R.id.guest_approval:intent=new Intent(this,GuestApproval.class);
                                        intent.putExtra("buildingName",BuildingName);
                                        startActivity(intent);break;
            default:break;
        }
    }
}