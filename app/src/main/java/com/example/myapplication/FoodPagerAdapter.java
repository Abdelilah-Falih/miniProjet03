package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodPagerAdapter extends RecyclerView.Adapter<FoodPagerAdapter.ViewHolder> {
    ArrayList<Food> foods ;

    public FoodPagerAdapter(ArrayList<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_item_foods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.iv_food.setImageResource(food.getImageRes());
        holder.tv_name.setText(food.getName());
        holder.tv_description.setText(food.getDescription());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_food;
        TextView tv_name, tv_description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_food = itemView.findViewById(R.id.iv_food);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
}
