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

        QuotesAdapter adapter = new QuotesAdapter(this, quotes, getSupportFragmentManager());

        binding.rvMyQuotes.setAdapter(adapter);
        binding.rvMyQuotes.setLayoutManager(new LinearLayoutManager(this));


        binding.includeFormule.btnAddUpdate.setOnClickListener(v->{
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
        });
    }
}