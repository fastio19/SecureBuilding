package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
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

public class ParkingProblem extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    TextInputLayout vechicleNo;
    Button sendSms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_problem);
        vechicleNo=findViewById(R.id.VechicleNo);
        sendSms=findViewById(R.id.SendSms);
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEnteredVechicleNo = vechicleNo.getEditText().getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("vehicleNo").getValue().equals(userEnteredVechicleNo)) {

                                String temp_name = ds.child("name").getValue(String.class);
                                String temp_email = ds.child("email").getValue(String.class);
                                String temp_password = ds.child("password").getValue(String.class);
                                String temp_username = ds.child("username").getValue(String.class);
                                String temp_phoneNo = ds.child("phoneNo").getValue(String.class);
                                String temp_vehicleNo=ds.child("vehicleNo").getValue(String.class);
                                Log.i("Our Info", temp_username);
                                Log.i("Our Info", temp_password);
                                Log.i("Our Info", temp_phoneNo);
                                Log.i("Our Info", temp_name);
                                Log.i("Our Info", temp_email);
                                Log.i("Our Info",temp_vehicleNo);
                                String message="Hello "+temp_name+" your vehicle ending with "+temp_vehicleNo+
                                        " is not alligned properly so please allign it !!"+
                                        "- SecureBuilding";
                                try{
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse("smsto:"));
                                    i.putExtra("address", new String(temp_phoneNo));
                                    i.putExtra("sms_body",message);
                                    startActivity(Intent.createChooser(i, "Send sms via:"));
                                    Toast.makeText(ParkingProblem.this,
                                            "SMS send successfully", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    Toast.makeText(ParkingProblem.this,
                                            "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


}
