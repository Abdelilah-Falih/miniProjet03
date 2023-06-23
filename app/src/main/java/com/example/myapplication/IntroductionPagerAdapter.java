package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IntroductionPagerAdapter extends RecyclerView.Adapter<IntroductionPagerAdapter.ViewHolder> {
    String[] introduction_texts;

    public IntroductionPagerAdapter(String[] introduction_texts) {
        this.introduction_texts = introduction_texts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_item_introduction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_description.setText(introduction_texts[position]);
    }

    @Override
    public int getItemCount() {
        return introduction_texts.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
}
