package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class ManageQuotes extends AppCompatActivity {
    String[] images_links;
    int x ;
    ImageView iv_image_loaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_quotes);

        iv_image_loaded = findViewById(R.id.iv_loadded_image);

        images_links = getResources().getStringArray(R.array.images_links);
        x = new Random().nextInt(5);

        Glide.with(this)
                .load(images_links[x])
                .into(iv_image_loaded);



    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}