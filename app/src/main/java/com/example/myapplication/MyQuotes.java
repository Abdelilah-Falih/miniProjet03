package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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

import java.util.List;

public class MyQuotes extends AppCompatActivity {
    ActivityMyQuotesBinding binding;
    View root;
    QuoteDatabase database;
    QuoteDao quoteDao;
    List<Quote> quotes;
    QuotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quotes);

        binding = ActivityMyQuotesBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);
       
        database = QuoteDatabase.getINSTANCE(this);
        quoteDao = database.getQao();




        loadQuotes();


        binding.includeFormule.btnAddUpdate.setOnClickListener(v->{
            addNewQuote();

        });
    }

    private void loadQuotes() {
        quotes = quoteDao.getAllQuotes();
        adapter = new QuotesAdapter(this, quotes, getSupportFragmentManager());
        binding.rvMyQuotes.setAdapter(adapter);
        binding.rvMyQuotes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addNewQuote() {
        String quote_text = binding.includeFormule.etQuoteFormule.getText().toString();
        String quote_author = binding.includeFormule.etAuthorFormule.getText().toString();
        Quote quote = new Quote(quote_text, quote_author);
        quoteDao.addQuote(quote);
        loadQuotes();
        binding.includeFormule.etAuthorFormule.setText("");
        binding.includeFormule.etQuoteFormule.setText("");

    }
}