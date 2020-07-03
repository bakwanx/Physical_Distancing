package com.example.physicaldistancing.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.physicaldistancing.R;
import com.example.physicaldistancing.model.NewsModel;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterHolder> {

    private ArrayList<NewsModel> mData = new ArrayList<>();

    public  void setNews (ArrayList<NewsModel> news){
        mData.clear();
        mData.addAll(news);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new NewsAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsAdapterHolder extends RecyclerView.ViewHolder {
        ImageView ImgNews_view;
        TextView title_view, content_view, publisdedAt_view, name;

        public NewsAdapterHolder(@NonNull View itemView) {
            super(itemView);

            ImgNews_view = itemView.findViewById(R.id.image_view);
            title_view = itemView.findViewById(R.id.judul_view);
            content_view = itemView.findViewById(R.id.content_view);
            publisdedAt_view = itemView.findViewById(R.id.publishedAt_views);
            name = itemView.findViewById(R.id.name_view);
        }

        void bind(NewsModel newsModel){


            Glide.with(itemView.getContext())
                    .load(newsModel.getUrlImage())
                    .into(ImgNews_view);
            title_view.setText(newsModel.getTitle());
            content_view.setText(newsModel.getContent());
            name.setText(newsModel.getName());
            publisdedAt_view.setText(newsModel.getPublisedAt());

        }
    }
}
