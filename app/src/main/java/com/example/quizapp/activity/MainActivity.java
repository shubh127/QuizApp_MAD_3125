package com.example.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.quizapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btnStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        initViews();
        addListeners();
    }

    private void initViews() {
        btnStartQuiz = findViewById(R.id.btn_start_quiz);
    }


    private void addListeners() {
        btnStartQuiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start_quiz) {
            startQuiz();
        }
    }

    private void startQuiz() {
        startActivity(new Intent(this, QuizActivity.class));
    }
}