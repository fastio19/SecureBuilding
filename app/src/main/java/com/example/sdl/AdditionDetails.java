package com.example.sdl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdditionDetails extends AppCompatActivity {
    TextInputLayout buildingName,flatNo,vehicleNo;
    Button login,signup;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_details);
        buildingName=findViewById(R.id.AdditionalDetail_BuildingName);
        flatNo=findViewById(R.id.AdditionalDetail_FlatNo);
        vehicleNo=findViewById(R.id.AdditionalDetail_VehicleNo);
        login=findViewById(R.id.AdditionalDetail_LoginBtn);
        signup=findViewById(R.id.AdditionalDetail_SignUpBtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdditionDetails.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        UserHelperClass helperClass=new UserHelperClass(name,username,email,phoneNo,password);
                //       reference.child(username).setValue(helperClass);
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");
                if(!validateBuildingName() | !validateFlatNo() | !validateVehicleNo() ){
                    return;
                }
                Intent intent=getIntent();
                String name=intent.getStringExtra("name");
                String username=intent.getStringExtra("username");
                String email=intent.getStringExtra("email");
                String phoneNo=intent.getStringExtra("phoneNo");
                String password=intent.getStringExtra("password");
                String building_name=buildingName.getEditText().getText().toString().trim();
                String flat_no=flatNo.getEditText().getText().toString().trim();
                String vehicle_no=vehicleNo.getEditText().getText().toString().trim();
                UserHelperClass helperClass=new UserHelperClass(name,username,email,phoneNo,password,
                        building_name,flat_no,vehicle_no,"No");
                reference.child(username).setValue(helperClass);
                Toast.makeText(AdditionDetails.this,"Registered Successfully !!",
                        Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(AdditionDetails.this,Dashboard.class);
                intent1.putExtra("name",name);
                intent1.putExtra("building_name",building_name);
                intent1.putExtra("flat_no",flat_no);
                intent1.putExtra("phone_no",phoneNo);
                startActivity(intent1);

            }
        });

    }

    private boolean validateVehicleNo() {
        String val=vehicleNo.getEditText().getText().toString();
        if(val.isEmpty()){
            vehicleNo.setError("Field cannot be empty");
            return false;
        }
        else{
            vehicleNo.setError(null);
            vehicleNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFlatNo() {
        String val=flatNo.getEditText().getText().toString();
        if(val.isEmpty()){
            flatNo.setError("Field cannot be empty");
            return false;
        }
        else{
            flatNo.setError(null);
            flatNo.setErrorEnabled(false);
            return true;
        }
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
}