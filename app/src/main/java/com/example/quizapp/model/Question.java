package com.example.quizapp.model;

import java.util.List;

public class Question {
    private final String questionStatement;
    private final List<Option> options;
    private final int correctOptionIndex;

    public Question(String questionStatement, List<Option> options, int correctOptionIndex) {
        this.questionStatement = questionStatement;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public List<Option> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}
