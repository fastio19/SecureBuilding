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
import java.util.List;

public class NoticeBoard extends AppCompatActivity {
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    DatabaseReference reference;
    ArrayList<NewsHelperClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        recyclerView=(RecyclerView)findViewById(R.id.NoticeBoardRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<NewsHelperClass>();
        final Intent intent=getIntent();
        final String BuildingName=intent.getStringExtra("buildingName");
        reference= FirebaseDatabase.getInstance().getReference().child("noticeboard").child(BuildingName);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                        NewsHelperClass p=ds.getValue(NewsHelperClass.class);
                        list.add(p);

                }
                newsAdapter=new NewsAdapter(NoticeBoard.this,list);
                recyclerView.setAdapter(newsAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Oops something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
