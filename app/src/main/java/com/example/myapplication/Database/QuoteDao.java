package com.example.myapplication.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuoteDao {

    @Insert
    void addQuote(Quote quote);

    @Update
    void updateQuote(Quote quote);

    @Delete
    void deleteQuote(Quote quote);

    @Query("select * from Quote")
    List<Quote> getAllQuotes();
}
