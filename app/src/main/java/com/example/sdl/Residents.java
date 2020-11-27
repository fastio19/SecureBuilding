package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Residents extends AppCompatActivity {
    ListView myListView;
    ArrayList<String> myArrayList = new ArrayList<>();
    DatabaseReference reference;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residents);
        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(Residents.this, android.R.layout.simple_list_item_1, myArrayList);
        myListView = (ListView) findViewById(R.id.listView);
        myListView.setAdapter(myArrayAdapter);
        Intent intent=getIntent();
        final String BuildingName=intent.getStringExtra("buildingName");
        t1=findViewById(R.id.allusers);
        t1.setText(BuildingName);
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("buildingName").getValue().equals(BuildingName)) {
                        UserHelperClass p = ds.getValue(UserHelperClass.class);
                        String temp_name = ds.child("name").getValue(String.class);
                        String temp_flatno=ds.child("flatNo").getValue(String.class);
                        myArrayList.add("Name :"+temp_name+" Flat :"+temp_flatno);
                        myArrayAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                myArrayAdapter.notifyDataSetChanged();
            }
        });
    }
}