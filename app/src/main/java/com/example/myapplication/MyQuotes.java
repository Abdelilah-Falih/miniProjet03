package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Database.Quote;
import com.example.myapplication.Database.QuoteDao;
import com.example.myapplication.Database.QuoteDatabase;
import com.example.myapplication.QuotesAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMyQuotesBinding;

import java.util.ArrayList;
import java.util.List;

public class MyQuotes extends AppCompatActivity {
    ActivityMyQuotesBinding binding;
    View root;
    QuoteDatabase database;
    QuoteDao quoteDao;
    List<Quote> quotes;
    public QuotesAdapter adapter;
    private static Button btn_delete;
    Uri contentUri = Uri.parse("content://com.example.myapp.provider/quote");
    Cursor cursor ;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quotes);

        binding = ActivityMyQuotesBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);
       
        database = QuoteDatabase.getINSTANCE(this);
        quoteDao = database.getQao();
        btn_delete = binding.btnDelete;


        binding.includeFormule.btnAddUpdate.setOnClickListener(v->{
            addNewQuote();

        });

        binding.btnDelete.setOnClickListener(v->{
            List<Quote> quotesToDelete = adapter.selected_quotes;
            for(Quote quoteToDelete : quotesToDelete){
                getContentResolver().delete(contentUri, Integer.toString(quoteToDelete.getId()), null);
            }
            adapter.selected_quotes_counter = 0;
            MyQuotes.showSelectedQuotes(0);
            adapter.loadQuotes();
        });

        loadQuotes();
    }

    private void loadQuotes() {
        cursor = getContentResolver().query(contentUri, null, null, null, null);
        quotes = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(cursor.getColumnIndex("id"));
                String quote = cursor.getString(cursor.getColumnIndex("quote"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                quotes.add(new Quote(_id, quote, author));
                id = _id;
            }
            adapter = new QuotesAdapter(this, quotes, getSupportFragmentManager());
            binding.rvMyQuotes.setAdapter(adapter);
            binding.rvMyQuotes.setLayoutManager(new LinearLayoutManager(this));

            cursor.close();
        }
    }

    private void addNewQuote() {
        String quote_text = binding.includeFormule.etQuoteFormule.getText().toString();
        String quote_author = binding.includeFormule.etAuthorFormule.getText().toString();
//        Quote quote = new Quote(quote_text, quote_author);
//        quoteDao.addQuote(quote);
        ContentValues values = new ContentValues();
        values.put("id", ++id);
        values.put("quote", quote_text);
        values.put("author", quote_author);
        getContentResolver().insert(contentUri, values);

        id = adapter.loadQuotes();
        binding.includeFormule.etAuthorFormule.setText("");
        binding.includeFormule.etQuoteFormule.setText("");

    }

    public static void showSelectedQuotes(int selected_quotes){
        btn_delete.setText("Delete("+selected_quotes+")");
    }


}