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

public class SendNotice extends AppCompatActivity {
    TextInputLayout t1,t2,t3;
    Button b1;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notice);
        t1=(TextInputLayout)findViewById(R.id.notice_title);
        t2=(TextInputLayout)findViewById(R.id.notice_content);
        t3=(TextInputLayout)findViewById(R.id.notice_date);
        b1=(Button)findViewById(R.id.notice_sendbtn);
        Intent intent=getIntent();
        final String BuildingName=intent.getStringExtra("buildingName");
        final String Name=intent.getStringExtra("name");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=t1.getEditText().getText().toString().trim();
                String content=t2.getEditText().getText().toString().trim();
                String date=t3.getEditText().getText().toString().trim();
                reference= FirebaseDatabase.getInstance().getReference().child("noticeboard");
                NewsHelperClass newsHelperClass=new NewsHelperClass(title,content,date);
                reference.child(BuildingName).child(title).setValue(newsHelperClass);
                Toast.makeText(SendNotice.this,"Successfully Sent!!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
