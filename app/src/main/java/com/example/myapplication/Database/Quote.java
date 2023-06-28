package com.example.myapplication.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Quote implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    private String quote;

    private String author;

    //region getters & setters


    public int getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    //endregion


    @Ignore
    public Quote(){}
    @Ignore
    public Quote(String quote, String author){
        setQuote(quote);
        setAuthor(author);
    }
    public Quote(int id,String quote, String author) {
        setId(id);
        setQuote(quote);
        setAuthor(author);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
