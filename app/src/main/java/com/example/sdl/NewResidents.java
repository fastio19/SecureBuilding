package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewResidents extends AppCompatActivity {
    RecyclerView recyclerView;
    ResidentsAdapter residentsAdapter;
    DatabaseReference reference;
    ArrayList<UserHelperClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_residents);
        recyclerView=(RecyclerView)findViewById(R.id.ResidentsRecyclerView);
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
                residentsAdapter=new ResidentsAdapter(NewResidents.this,list);
                recyclerView.setAdapter(residentsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Oops something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}