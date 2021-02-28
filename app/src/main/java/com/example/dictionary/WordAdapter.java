package com.example.dictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private final ArrayList<WordItem> myWordList;
    private OnItemClickListener myListener;

    public WordAdapter(ArrayList<WordItem> wordList) {

        myWordList = wordList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        myListener = listener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_items, parent, false);
        return new WordViewHolder(v, myListener);
    }

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        WordItem currentItem = myWordList.get(position);
        holder.myTextView.setText(currentItem.getWord());
    }

    @Override
    public int getItemCount() {
        return myWordList.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView myTextView;
        public Button myButton;

        public WordViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.aWord);
            myButton = itemView.findViewById(R.id.delete);

            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }
}
