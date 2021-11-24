package com.example.quizapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.OptionsAdapter;
import com.example.quizapp.interfaces.OnOptionClickListener;
import com.example.quizapp.model.Question;

public class QuestionPagerFragment extends Fragment implements OnOptionClickListener {
    private final Question questionData;
    private TextView tvQuestionStatement;
    private RecyclerView rvOptions;

    public QuestionPagerFragment(Question questionData) {
        this.questionData = questionData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setDataOnUI();
    }

    private void initViews(View view) {
        tvQuestionStatement = view.findViewById(R.id.tv_question_statement);
        rvOptions = view.findViewById(R.id.rv_options);
    }

    private void setDataOnUI() {
        tvQuestionStatement.setText(questionData.getQuestionStatement());
        rvOptions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOptions.setAdapter(new OptionsAdapter(questionData.getOptions(), this));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onOptionSelect(int index) {
        for (int i = 0; i < questionData.getOptions().size(); i++) {
            if (index == i) {
                questionData.getOptions().get(i).setSelected(
                        !questionData.getOptions().get(i).isSelected());
            } else {
                questionData.getOptions().get(i).setSelected(false);
            }
        }
        if (rvOptions.getAdapter() != null) {
            rvOptions.getAdapter().notifyDataSetChanged();
        }
    }
}