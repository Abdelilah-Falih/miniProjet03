package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.Database.Quote;

public class FragmentUpdate extends DialogFragment {
    Quote quote;
    EditText et_quote , et_author;
    Button btn_add_update;

    public FragmentUpdate(Quote quote){
        this.quote = quote;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_dialog_update_quote, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        et_quote = view.findViewById(R.id.et_quote_formule);
        et_author = view.findViewById(R.id.et_author_formule);
        btn_add_update = view.findViewById(R.id.btn_add_update);

        et_quote.setText(quote.getQuote());
        et_author.setText(quote.getAuthor());
        btn_add_update.setText("Update");
        btn_add_update.setOnClickListener(v->{
            Toast.makeText(getContext(), "Update !", Toast.LENGTH_SHORT).show();
        });
    }
}
