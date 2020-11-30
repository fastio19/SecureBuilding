package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        recyclerView=(RecyclerView)findViewById(R.id.NoticeBoardRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        b1=(Button)findViewById(R.id.SendNotice);
        list=new ArrayList<NewsHelperClass>();
        final Intent intent=getIntent();
        final String BuildingName=intent.getStringExtra("buildingName");
        final String Name=intent.getStringExtra("name");

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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference= FirebaseDatabase.getInstance().getReference().child("users");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds:snapshot.getChildren()){
                            if(ds.child("name").getValue().equals(Name)){
                                String per=ds.child("secretary").getValue(String.class);
                                if(per.equals("Yes")){
                                    Intent intent1=new Intent(getApplicationContext(),SendNotice.class);
                                    intent1.putExtra("buildingName",BuildingName);
                                    intent1.putExtra("name",Name);
                                    startActivity(intent1);
                                    finish();
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"You don't have permission",Toast.LENGTH_SHORT).show();
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
