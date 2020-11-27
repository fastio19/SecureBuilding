package com.example.sdl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        public void onClick(int position) {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Accept is clicked",Toast.LENGTH_SHORT).show();

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Decline is clicked",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
