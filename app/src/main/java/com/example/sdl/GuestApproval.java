package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GuestApproval extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<UserHelperClass> list;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_approval);
        recyclerView=(RecyclerView)findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<UserHelperClass>();
        final Intent intent=getIntent();
        final String BuildingName=intent.getStringExtra("buildingName");
        reference= FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    if(ds.child("buildingName").getValue().equals(BuildingName)){
                        UserHelperClass p=ds.getValue(UserHelperClass.class);
                        list.add(p);
                    }
                }
                adapter=new MyAdapter(GuestApproval.this,list);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Oops something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
