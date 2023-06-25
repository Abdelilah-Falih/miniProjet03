package com.example.myapplication.Database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quotes);

        binding = ActivityMyQuotesBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);
        database = QuoteDatabase.getINSTANCE(this);
        quoteDao = database.getQao();


        quotes = quoteDao.getAllQuotes();

        QuotesAdapter adapter = new QuotesAdapter(this, quotes);

        binding.rvMyQuotes.setAdapter(adapter);
        binding.rvMyQuotes.setLayoutManager(new LinearLayoutManager(this));

    }
}