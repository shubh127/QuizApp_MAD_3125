package com.example.quizapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quizapp.fragment.QuestionPagerFragment;
import com.example.quizapp.model.Question;

import java.util.List;

public class QuestionPagerAdapter extends FragmentStateAdapter {
    private final List<Question> questions;

    public QuestionPagerAdapter(FragmentActivity fa, List<Question> questions) {
        super(fa);
        this.questions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new QuestionPagerFragment(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public List<Question> getQuestions() {
        return questions;
    }
}