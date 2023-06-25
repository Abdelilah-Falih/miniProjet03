package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.Quote;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.MyViewHolder>{
    List<Quote> quotes;

    Context context;
    FragmentManager fragmentManager;
    int atrovirens = Color.rgb(13, 148, 148),
            coral = Color.rgb(255, 127, 80),
            sarcoline = Color.rgb(255, 221, 170);

    private int index = 1;
    int selected_quotes = 0;

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
            Toast.makeText(context, ""+quote.getId(), Toast.LENGTH_SHORT).show();
        });

        holder.cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selected_quotes ++;
                    MyQuotes.showSelectedQuotes(selected_quotes);
                }else{
                    selected_quotes --;
                    MyQuotes.showSelectedQuotes(selected_quotes);
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
}
