package com.example.sdl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendRequest extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextInputLayout t1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);
        t1=findViewById(R.id.GuestName);
        b1=findViewById(R.id.sendRequestBtn);
        Intent intent=getIntent();
        final String owner=intent.getStringExtra("owner");
        final String buildingName=intent.getStringExtra("buildingName");
        final String flatno=intent.getStringExtra("flatno");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName()) {
                    return;
                }
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference();
                String guest=t1.getEditText().getText().toString().trim();
                RequestHelperClass requestHelperClass=new RequestHelperClass(owner,guest,"Pending");
                reference.child("requests").child(guest).setValue(requestHelperClass);

            }
        });
    }

    private boolean validateName() {
        String val=t1.getEditText().getText().toString();
        if(val.isEmpty()){
            t1.setError("Field cannot be empty");
            return false;
        }
        else{
            t1.setError(null);
            t1.setErrorEnabled(false);
            return true;
        }
    }
}
