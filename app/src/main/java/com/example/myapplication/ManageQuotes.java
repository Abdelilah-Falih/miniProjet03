package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.NetworkInfo.NetworkInformation;

import java.util.Random;

public class ManageQuotes extends AppCompatActivity {
    String[] images_links;
    int x ;
    ImageView iv_image_loaded;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_quotes);

        iv_image_loaded = findViewById(R.id.iv_loadded_image);

        images_links = getResources().getStringArray(R.array.images_links);
        x = new Random().nextInt(5);
        broadcastReceiver = new loadImageIfConnected();
        internetStatus();
    }

    void internetStatus(){
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void loadImage() {
        Glide.with(this)
                .load(images_links[x])
                .into(iv_image_loaded);
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }




    class loadImageIfConnected extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(NetworkInformation.isConnected(context)){
                loadImage();
            }else {
                iv_image_loaded.setImageResource(R.drawable.image_not_available);
            }
        }
    }
}