package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Database.Quote;
import com.example.myapplication.Database.QuoteDao;
import com.example.myapplication.Database.QuoteDatabase;
import com.example.myapplication.NetworkInfo.NetworkInformation;

import java.util.Random;

public class ManageQuotes extends AppCompatActivity {
    String[] images_links;
    int x ;
    ImageView iv_image_loaded;
    Button btn_go;
    BroadcastReceiver broadcastReceiver;

    QuoteDao quoteDao;
    QuoteDatabase database;
    Uri contentUri = Uri.parse("content://com.example.myapp.provider/quote");

    int id = 0;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_quotes);

        iv_image_loaded = findViewById(R.id.iv_loadded_image);
        btn_go = findViewById(R.id.btn_go);
        cursor = getContentResolver().query(contentUri, null, null, null, null);


        images_links = getResources().getStringArray(R.array.images_links);
        x = new Random().nextInt(5);
        broadcastReceiver = new loadImageIfConnected();

        database = QuoteDatabase.getINSTANCE(this);
        quoteDao = database.getQao();

        btn_go.setOnClickListener(v->{
            addQuote();
        });




    }

    private void addQuote() {

        /*addNewQuote(new Quote("Be yourself; everyone else is already taken", "Oscar Wilde"));
        addNewQuote(new Quote("If you tell the truth, you don't have to remember anything", "Mark Twain"));
        addNewQuote(new Quote("To live is the rarest thing in the world. Most people exist, that is all", "Oscar Wilde"));
        addNewQuote(new Quote("To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment", "Ralph Waldo Emerson"));
        addNewQuote(new Quote("Always forgive your enemies; nothing annoys them so much", "Oscar Wilde"));
        addNewQuote(new Quote("Silence is golden when you can't think of a good answer", "Muhammad Ali"));
        addNewQuote(new Quote("We can't solve problems by using the same kind of thinking we used when we created them", "Albert Einstein"));
        addNewQuote(new Quote("Don't count the days. Make the days count", "Muhammad Ali"));
        addNewQuote(new Quote("Coding like poetry should be short and concise", "Santosh Kalwar"));
        addNewQuote(new Quote( "Clean code always looks like it was written by someone who cares", "obert C. Martin"));
        addNewQuote(new Quote( "Experience is the name everyone gives to their mistakes", "Oscar Wilde"));
        addNewQuote(new Quote("Of course, bad code can be cleaned up. But it’s very expensive.", "Robert C. Martin"));
         */
        startActivity(new Intent(this, MyQuotes.class));
    }


    void internetStatus(){
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onResume() {
        super.onResume();
        internetStatus();
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

    private void addNewQuote(Quote quote) {
        ContentValues values = new ContentValues();
        values.put("id", ++id);
        values.put("quote", quote.getQuote());
        values.put("author", quote.getAuthor());
        getContentResolver().insert(contentUri, values);


    }
}