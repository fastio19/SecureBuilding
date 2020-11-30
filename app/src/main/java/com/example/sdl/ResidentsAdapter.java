package com.example.sdl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResidentsAdapter extends RecyclerView.Adapter<ResidentsAdapter.ResidentsViewHolder>  {

    Context context;
    List<UserHelperClass> mData;

    public ResidentsAdapter(Context context, List<UserHelperClass> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ResidentsAdapter.ResidentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout= LayoutInflater.from(context).inflate(R.layout.flatmates,parent,false);
        return new ResidentsAdapter.ResidentsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentsAdapter.ResidentsViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        holder.mobileNo.setText(mData.get(position).getPhoneNo());
        holder.flatNo.setText(mData.get(position).getFlatNo());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ResidentsViewHolder extends RecyclerView.ViewHolder{
        TextView name,mobileNo,flatNo;
        public ResidentsViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.ResidentsName);
            mobileNo=itemView.findViewById(R.id.ResidentsMobileNo);
            flatNo=itemView.findViewById(R.id.ResidentsFlatNo);
        }
    }
}
