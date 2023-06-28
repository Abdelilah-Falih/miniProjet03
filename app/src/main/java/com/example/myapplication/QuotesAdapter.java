package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.MyViewHolder>{
    List<Quote> quotes;

    Context context;
    FragmentManager fragmentManager;
    int atrovirens = Color.rgb(13, 148, 148),
            coral = Color.rgb(255, 127, 80),
            sarcoline = Color.rgb(255, 221, 170);

    private int index = 1;
    int selected_quotes_counter = 0;
    List<Quote> selected_quotes = new ArrayList<>();
    Uri contentUri = Uri.parse("content://com.example.myapp.provider/quote");
    Cursor cursor;


    public QuotesAdapter(Context context, List<Quote> quotes, FragmentManager fragmentManager){
        this.context = context;
        this.quotes = quotes;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public QuotesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quote_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesAdapter.MyViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.tv_quote.setText(quote.getQuote());
        holder.tv_author.setText(quote.getAuthor());

        holder.btn_update.setOnClickListener(v->{
            FragmentUpdate fragmentUpdate = new FragmentUpdate(quote, this);
            fragmentUpdate.show(fragmentManager, "TAG");
        });

        holder.cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selected_quotes_counter++;
                    selected_quotes.add(quote);
                    MyQuotes.showSelectedQuotes(selected_quotes_counter);
                }else{
                    selected_quotes_counter--;
                    selected_quotes.remove(quote);
                    MyQuotes.showSelectedQuotes(selected_quotes_counter);
                }
            }
        });

        if(index ==1){
            holder.itemView.setBackgroundColor(atrovirens);
        }else if (index == 2){
            holder.itemView.setBackgroundColor(coral);
        }else if(index ==3) holder.itemView.setBackgroundColor(sarcoline);
        incrementIndex();
    }

    private void incrementIndex() {
        if(index <3) index ++;
        else index = 1;
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_quote, tv_author;
        Button btn_update;
        CheckBox cb_select;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_quote = itemView.findViewById(R.id.et_quote_formule);
            tv_author = itemView.findViewById(R.id.et_author_formule);
            btn_update = itemView.findViewById(R.id.btn_update);
            cb_select = itemView.findViewById(R.id.cb_select);
        }
    }
    public int loadQuotes() {
        int id =0;
        cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        quotes = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(cursor.getColumnIndex("id"));
                String quote = cursor.getString(cursor.getColumnIndex("quote"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                quotes.add(new Quote(_id, quote, author));
                id = _id;
            }
            cursor.close();
        }
        MyWorker.quotes = (ArrayList<Quote>) quotes;
        notifyDataSetChanged();
        return id;
    }
}
