package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    View root;

    ArrayList<Food> foods = new ArrayList<>();
    int[] imagesRes = {R.drawable.tacos, R.drawable.denner, R.drawable.breakfast, R.drawable.lunch,};
    String[] food_names = {"Tacos", "Dinner", "Breakfast", "Lunch"};
    String[] food_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);

        food_desc = getResources().getStringArray(R.array.food_desc);


        for (int i = 0; i < imagesRes.length; i++) {
            foods.add(new Food(imagesRes[i], food_names[i], food_desc[i]));
        }

        FoodPagerAdapter adapter = new FoodPagerAdapter(foods);
        binding.vPager.setAdapter(adapter);
        binding.vPager.setClipToPadding(false);
        binding.vPager.setClipChildren(false);
        binding.vPager.setOffscreenPageLimit(2);
        binding.vPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);





    }
}