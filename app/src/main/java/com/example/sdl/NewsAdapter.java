package com.example.sdl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    List<NewsHelperClass> mData;

    public NewsAdapter(Context context, List<NewsHelperClass> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout= LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.content.setText(mData.get(position).getContent());
        holder.date.setText(mData.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title,content,date;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.NoticeTitle);
            content=itemView.findViewById(R.id.NoticeDescription);
            date=itemView.findViewById(R.id.NoticeDate);
        }
    }
}
