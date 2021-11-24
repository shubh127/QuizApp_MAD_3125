package com.example.quizapp.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.interfaces.OnOptionClickListener;
import com.example.quizapp.model.Option;

public class OptionsViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvOption;

    public OptionsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvOption = itemView.findViewById(R.id.tv_option);
    }

    public void bind(Option optionData, OnOptionClickListener listener, int position) {
        tvOption.setSelected(optionData.isSelected());
        tvOption.setText(optionData.getOptionTxt());

        tvOption.setOnClickListener(view -> listener.onOptionSelect(position));
    }
}

