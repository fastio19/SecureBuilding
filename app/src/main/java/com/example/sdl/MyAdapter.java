package com.example.sdl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserHelperClass> profiles;
    String guest;
    public MyAdapter(Context c, ArrayList<UserHelperClass> p){
        context=c;
        profiles=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.flatno.setText(profiles.get(position).getFlatNo());
        holder.onClick(position);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,flatno;
        Button request;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.cardview_names);
            flatno=(TextView) itemView.findViewById(R.id.cardview_flatno);
            request=(Button) itemView.findViewById(R.id.request);
        }
        public void onClick(int position){
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String owner=name.getText().toString();
                    String Flatno=flatno.getText().toString();

                    Intent intent=new Intent(context,SendRequest.class);
                    intent.putExtra("owner",owner);
                    intent.putExtra("flatno",Flatno);
                    context.startActivity(intent);
                    request.setText("Pending");
                    request.setEnabled(false);
                }
            });
        }
    }
}
