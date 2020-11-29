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

public class AcceptGuest extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<RequestHelperClass> list;
    MyAdapter2 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_guest);
        recyclerView=(RecyclerView)findViewById(R.id.recycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<RequestHelperClass>();
        Intent intent=getIntent();
        final String owner=intent.getStringExtra("name");
        reference=FirebaseDatabase.getInstance().getReference().child("requests");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    if(ds.child("owner").getValue().equals(owner) && ds.child("status").getValue().equals("Pending")){
                        RequestHelperClass p=ds.getValue(RequestHelperClass.class);
                        list.add(p);
                    }
                }
                adapter=new MyAdapter2(AcceptGuest.this,list);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AcceptGuest.this,"OOps something is wrong!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
