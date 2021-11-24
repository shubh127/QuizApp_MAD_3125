package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.interfaces.OnOptionClickListener;
import com.example.quizapp.model.Option;
import com.example.quizapp.viewholder.OptionsViewHolder;

import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsViewHolder> {
    private final List<Option> options;
    private final OnOptionClickListener listener;

    public OptionsAdapter(List<Option> options, OnOptionClickListener listener) {
        this.options = options;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.options_child_view, parent, false);
        return new OptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {
        holder.bind(options.get(position), listener, position);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

}
