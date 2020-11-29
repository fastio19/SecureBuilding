package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckStatus extends AppCompatActivity {
    ListView myListView;
    ArrayList<String> myArrayList = new ArrayList<>();
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);
        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(CheckStatus.this, android.R.layout.simple_list_item_1, myArrayList);
        myListView = (ListView) findViewById(R.id.CheckStatus_listView);
        myListView.setAdapter(myArrayAdapter);
        reference = FirebaseDatabase.getInstance().getReference().child("requests");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String temp_guestname = ds.child("guest").getValue(String.class);
                    String temp_owner = ds.child("owner").getValue(String.class);
                    String temp_status = ds.child("status").getValue(String.class);
                    Log.i("Check Status :", temp_guestname);
                    Log.i("Check Status :", temp_owner);
                    Log.i("Check Status :", temp_status);
                    myArrayList.add(temp_guestname + "     Status :" + temp_status);
                    myArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                myArrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
