package com.example.quizapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.quizapp.R;
import com.example.quizapp.enums.QuestionStatus;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private int unattemptedCount = 0;
    private int correctCount = 0;
    private int incorrectCount = 0;
    private TextView tvScore;
    private TextView tvUnattemptedScore;
    private TextView tvCorrectScore;
    private TextView tvIncorrectScore;
    private TextView tvSummaryMessage;
    private AppCompatButton btnRetakeTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_result);

        getDataFromIntent();
        initViews();
        setDataToViews();
        setListeners();
    }

    private void getDataFromIntent() {
        if (getIntent() != null) {
            unattemptedCount = getIntent().getIntExtra(QuestionStatus.UNATTEMPTED.name(),
                    0);
            correctCount = getIntent().getIntExtra(QuestionStatus.CORRECT.name(),
                    0);
            incorrectCount = getIntent().getIntExtra(QuestionStatus.INCORRECT.name(),
                    0);
        }
    }

    private void initViews() {
        tvScore = findViewById(R.id.tv_score);
        tvUnattemptedScore = findViewById(R.id.tv_unattempted_count);
        tvCorrectScore = findViewById(R.id.tv_correct_count);
        tvIncorrectScore = findViewById(R.id.tv_incorrect_count);
        tvSummaryMessage = findViewById(R.id.tv_final_msg);
        btnRetakeTest = findViewById(R.id.btn_restart);
    }

    private void setDataToViews() {
        tvScore.setText(String.format(Locale.getDefault(), "%d / 5", correctCount));
        tvUnattemptedScore.setText(String.format(Locale.getDefault(),
                "%d Question(s)", unattemptedCount));
        tvCorrectScore.setText(String.format(Locale.getDefault(),
                "%d Question(s)", correctCount));
        tvIncorrectScore.setText(String.format(Locale.getDefault(),
                "%d Question(s)", incorrectCount));
        tvSummaryMessage.setText(getSummaryMessage(correctCount));
    }

    private void setListeners() {
        btnRetakeTest.setOnClickListener(this);
    }

    private String getSummaryMessage(int correctCount) {
        switch (correctCount) {
            case 3:
                return getString(R.string.good_job);
            case 4:
                return getString(R.string.excellent_work);
            case 5:
                return getString(R.string.genius);
            default:
                btnRetakeTest.setVisibility(View.VISIBLE);
                return getString(R.string.try_again);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_restart) {
            finish();
        }
    }
}