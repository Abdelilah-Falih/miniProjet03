package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    View root;

    String[] introduction_texts = {"Hi", "This app Let You Manage Your Quotes", "Enjoy .."};
    SharedPreferences sharedPreferences;
    boolean skipped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);

        sharedPreferences = getSharedPreferences("skip_tutorial", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("skipped", false)){
            skipped = true;
            startNewActivity();
        }



        IntroductionPagerAdapter adapter = new IntroductionPagerAdapter(introduction_texts);
        binding.vPager.setAdapter(adapter);
        binding.vPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        binding.btnNext.setOnClickListener(v->{
            setSkipped();
            startNewActivity();
        });


    }

    private void setSkipped() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("skipped", true);
        editor.apply();
        skipped = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!skipped){
            setSkipped();
        }
    }

    private void startNewActivity() {
        startActivity(new Intent(this, ManageQuotes.class));
    }
}