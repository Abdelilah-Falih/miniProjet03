package com.example.myapplication.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuoteDao {

    @Insert
    long addQuote(Quote quote);

    @Update
    void updateQuote(Quote quote);

    @Delete
    void deleteQuote(Quote quote);

    @Query("select * from Quote")
    List<Quote> getAllQuotes();

    @Query("delete from quote where id =:parseId")
    void deleteQuoteById(long parseId);
}
