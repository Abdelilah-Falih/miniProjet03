package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_quotes);

        iv_image_loaded = findViewById(R.id.iv_loadded_image);
        btn_go = findViewById(R.id.btn_go);

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
        /*quoteDao.addQuote(new Quote(1, "Be yourself; everyone else is already taken", "Oscar Wilde"));
        quoteDao.addQuote(new Quote(2, "If you tell the truth, you don't have to remember anything", "Mark Twain"));
        quoteDao.addQuote(new Quote(3, "To live is the rarest thing in the world. Most people exist, that is all", "Oscar Wilde"));
        quoteDao.addQuote(new Quote(4, "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment", "Ralph Waldo Emerson"));
        quoteDao.addQuote(new Quote(5, "Always forgive your enemies; nothing annoys them so much", "Oscar Wilde"));
        quoteDao.addQuote(new Quote(6, "Silence is golden when you can't think of a good answer", "Muhammad Ali"));
        quoteDao.addQuote(new Quote(7, "We can't solve problems by using the same kind of thinking we used when we created them", "Albert Einstein"));
        quoteDao.addQuote(new Quote(8, "Don't count the days. Make the days count", "Muhammad Ali"));
        quoteDao.addQuote(new Quote(9, "Coding like poetry should be short and concise", "Santosh Kalwar"));
        quoteDao.addQuote(new Quote(10, "Clean code always looks like it was written by someone who cares", "obert C. Martin"));
        quoteDao.addQuote(new Quote(11, "Experience is the name everyone gives to their mistakes", "Oscar Wilde"));
        quoteDao.addQuote(new Quote(12, "Of course, bad code can be cleaned up. But it’s very expensive.", "Robert C. Martin"));*/
        startActivity(new Intent(this, MyQuotes.class));
        //Toast.makeText(this, quoteDao.getAllQuotes().get(0).toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Quote added !", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, ""+quoteDao.getAllQuotes().size(), Toast.LENGTH_SHORT).show();
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
}