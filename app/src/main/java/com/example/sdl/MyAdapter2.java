package com.example.sdl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {
    Context context;
    ArrayList<RequestHelperClass> profiles;
    public MyAdapter2(Context c,ArrayList<RequestHelperClass> p){
        context=c;
        profiles=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.guestName.setText(profiles.get(position).getGuest());
        holder.onClick(position);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView guestName;
        Button b1,b2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName=(TextView) itemView.findViewById(R.id.cardview2_names);
            b1=(Button) itemView.findViewById(R.id.AcceptBtn);
            b2=(Button) itemView.findViewById(R.id.DeclineBtn);
        }

        public void onClick(final int position) {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Accepted",Toast.LENGTH_SHORT).show();
                    final String guest=guestName.getText().toString();
                    DatabaseReference reference;
                    reference=FirebaseDatabase.getInstance().getReference().child("requests");
                    profiles.remove(position);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds:snapshot.getChildren()){
                                if(ds.child("guest").getValue().equals(guest)){
                                    ds.getRef().child("status").setValue("Accepted");

                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("my info",error.getMessage());
                        }
                    });

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String guest=guestName.getText().toString();
                    DatabaseReference reference;
                    Toast.makeText(context,"Declined",Toast.LENGTH_SHORT).show();
                    profiles.remove(position);
                    reference=FirebaseDatabase.getInstance().getReference().child("requests");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds:snapshot.getChildren()){
                                if(ds.child("guest").getValue().equals(guest)){
                                    ds.getRef().child("status").setValue("Rejected");
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("my info",error.getMessage());
                        }
                    });

                }
            });

        }
    }
}