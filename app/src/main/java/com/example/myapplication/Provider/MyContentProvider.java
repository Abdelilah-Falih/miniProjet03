package com.example.myapplication.Provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.net.Uri;
import android.widget.Toast;

import com.example.myapplication.Database.Quote;
import com.example.myapplication.Database.QuoteDao;
import com.example.myapplication.Database.QuoteDatabase;

import java.util.List;

public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.myapp.provider";
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUOTE = 1;

    static {
        URI_MATCHER.addURI(AUTHORITY, "quote", QUOTE);
    }

    private QuoteDatabase quoteDatabase;

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        quoteDatabase = QuoteDatabase.getINSTANCE(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (URI_MATCHER.match(uri)) {
            case QUOTE:
                // Use the Room DAO to insert the quote
                QuoteDao quoteDao = quoteDatabase.getQao();
                Quote newQuote = new Quote();
                newQuote.setId(values.getAsInteger("id"));
                newQuote.setQuote(values.getAsString("quote"));
                newQuote.setAuthor(values.getAsString("author"));
                long id = quoteDao.addQuote(newQuote);
                if (id > 0) {
                    Uri insertedUri = ContentUris.withAppendedId(uri, id);
                    getContext().getContentResolver().notifyChange(insertedUri, null);
                    return insertedUri;
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (URI_MATCHER.match(uri)) {
            case QUOTE:
                // Use the Room DAO to update the quote
                QuoteDao quoteDao = quoteDatabase.getQao();
                Quote updatedQuote = new Quote();
                updatedQuote.setId(values.getAsInteger("id"));
                updatedQuote.setQuote(values.getAsString("quote"));
                updatedQuote.setAuthor(values.getAsString("author"));
                quoteDao.updateQuote(updatedQuote);
                return 1; // Return the number of rows affected
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (URI_MATCHER.match(uri)) {
            case QUOTE:
                // Use the Room DAO to delete the quote
                int id = Integer.parseInt(selection);
                QuoteDao quoteDao = quoteDatabase.getQao();
                quoteDao.deleteQuoteById(id);
                return 1; // Return the number of rows affected
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case QUOTE:
                return "vnd.android.cursor.dir/vnd.com.example.myapp.provider.quote";
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (URI_MATCHER.match(uri)) {
            case QUOTE:
                // Use the Room DAO to query the quotes
                QuoteDao quoteDao = quoteDatabase.getQao();
                List<Quote> quotes = quoteDao.getAllQuotes();
                MatrixCursor cursor = new MatrixCursor(new String[]{"id", "quote", "author"});
                for (Quote quote : quotes) {
                    cursor.addRow(new Object[]{quote.getId(), quote.getQuote(), quote.getAuthor()});
                }
                return cursor;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}
