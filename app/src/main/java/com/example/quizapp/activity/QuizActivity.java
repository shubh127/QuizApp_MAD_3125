package com.example.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.quizapp.R;
import com.example.quizapp.adapter.QuestionPagerAdapter;
import com.example.quizapp.enums.QuestionStatus;
import com.example.quizapp.model.Option;
import com.example.quizapp.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager2 viewPager;
    private TextView tvQuestionNumber;
    private TextView tvNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initViews();
        applyListeners();
        List<Question> questions = populateData();
        List<Question> randomQuestionsForQuiz = getRandomQuestionsForQuiz(questions);
        configViews(randomQuestionsForQuiz);
    }

    private void initViews() {
        viewPager = findViewById(R.id.pager);
        tvNext = findViewById(R.id.tv_next);
    }

    private void applyListeners() {
        tvNext.setOnClickListener(this);
    }

    private void configViews(List<Question> questions) {
        viewPager.setAdapter(new QuestionPagerAdapter(this, questions));
        tvQuestionNumber = findViewById(R.id.tv_question_number);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                tvQuestionNumber.setText(String.format(Locale.getDefault(),
                        "Question %d", position + 1));
                if (position == 4) {
                    tvNext.setText(getString(R.string.submit));
                } else {
                    tvNext.setText(getString(R.string.next));
                }
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private List<Question> populateData() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "How can you check your current git version?",
                Arrays.asList(new Option("git --v", false),
                        new Option("git --version", false),
                        new Option("git --option", false),
                        new Option("git --current", false)),
                1));

        questions.add(new Question(
                "Which of the following is true you when you use the following " +
                        "command?\n\ngit add -A",
                Arrays.asList(new Option("All new and updated files are staged",
                                false),
                        new Option("Files are staged in alphabetical order",
                                false),
                        new Option("All new files are staged", false),
                        new Option("Only updated files are staged", false)),
                0));

        questions.add(new Question(
                "What will the following command print to the Terminal?\n\ngit" +
                        " remote -v",
                Arrays.asList(new Option("A list of remote repositories and their URLs",
                                false),
                        new Option("The current git version you're running, false)",
                                false),
                        new Option("An inline editor for modifying remote repositories",
                                false),
                        new Option("The last 5 git versions you've installed",
                                false)),
                1));

        questions.add(new Question(
                "What does the following command do to the git repository?\n\ngit" +
                        " reset --soft HEAD^",
                Arrays.asList(new Option("It deletes all previous commits and reset the " +
                                "repository history back to its initial state.", false),
                        new Option("It resets the working branch to the first commit.",
                                false),
                        new Option("It keeps the HEAD at the current commit, but clears " +
                                "all previous commits.", false),
                        new Option("It sets HEAD to the previous commit and leaves" +
                                " changes from the undone commit in the stage/index.",
                                false)),
                3));

        questions.add(new Question(
                "You find a bug in your project, but can't locate where it was" +
                        " introduced in the commit history. How would you diagnose this problem?",
                Arrays.asList(new Option("Manually backtrack through your commit history.",
                                false),
                        new Option("Use git search -diff to compare all commits in your" +
                                " repository history.", false),
                        new Option("Run a git rebase to find the buggy commit.",
                                false),
                        new Option("Use git bisect to compare the buggy commit to an" +
                                " early commit that works as expected.", false)),
                3));

        questions.add(new Question(
                "Why would you use a pre-receive hook in your remote repository?",
                Arrays.asList(new Option("You wouldn't, you would use it in the local" +
                                " repository", false),
                        new Option("To execute a script when a remote receives a push" +
                                " that is triggered before any refs are updated", false),
                        new Option("To delete the last 10 commits and reset the HEAD",
                                false),
                        new Option("In order to locally cache the last 10 commits",
                                false)),
                1));

        questions.add(new Question(
                "What option can you use to apply git configurations across" +
                        " your entire git environment?",
                Arrays.asList(new Option("--all", false),
                        new Option("--master", false),
                        new Option("--global", false),
                        new Option("--update", false)),
                0));

        questions.add(new Question(
                "How could you squash multiple commits together without using git" +
                        " merge --squash?",
                Arrays.asList(new Option("Caching", false),
                        new Option("You can't. git merge --squash is the only git" +
                                " command for that operation.", false),
                        new Option("Rebasing", false),
                        new Option("Reflogging", false)),
                2));

        questions.add(new Question(
                "Where are files stored before they are committed to the local" +
                        " repository?",
                Arrays.asList(new Option("Saved files", false),
                        new Option("git documents", false),
                        new Option("Staging area", false),
                        new Option("git cache", false)),
                2));

        questions.add(new Question(
                "How would you create a custom shortcut or command across your git" +
                        " environment?",
                Arrays.asList(new Option("Run git hotfix with the shortcut name.",
                                false),
                        new Option("Assign a shortcut or command using git options file.",
                                false),
                        new Option("Use the git custom-key command.",
                                false),
                        new Option("Create an alias in the git config file.",
                                false)),
                3));

        return questions;
    }


    private List<Question> getRandomQuestionsForQuiz(List<Question> questions) {
        List<Question> randQuestions = new ArrayList<>();
        Random ran = new Random();
        for (int n = 0; n < 5; n++) {
            int randomIndex = ran.nextInt(5) + 1;
            randQuestions.add(questions.get(randomIndex));
            questions.remove(randomIndex);
        }
        return randQuestions;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_next) {
            handleNextClick();
        }
    }

    private void handleNextClick() {
        if (viewPager.getCurrentItem() == 4) {
            calculateResult();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    private void calculateResult() {
        int unattempted = 0;
        int correct = 0;
        int incorrect = 0;
        if (viewPager.getAdapter() != null) {
            List<Question> questions = ((QuestionPagerAdapter) viewPager.getAdapter()).getQuestions();
            for (Question question : questions) {
                boolean isAttempted = false;
                boolean isCorrect = false;
                int index = 0;
                for (Option option : question.getOptions()) {
                    if (option.isSelected()) {
                        isAttempted = true;
                        if (index == question.getCorrectOptionIndex()) {
                            isCorrect = true;
                        }
                    }
                    index++;
                }
                if (!isAttempted) {
                    unattempted++;
                } else if (isCorrect) {
                    correct++;
                } else incorrect = incorrect + 1;
            }
        }

        moveToResultScreen(unattempted, correct, incorrect);
    }

    private void moveToResultScreen(int unattempted, int correct, int incorrect) {
        Intent resultActivity = new Intent(this, ResultActivity.class);
        resultActivity.putExtra(QuestionStatus.UNATTEMPTED.name(), unattempted);
        resultActivity.putExtra(QuestionStatus.CORRECT.name(), correct);
        resultActivity.putExtra(QuestionStatus.INCORRECT.name(), incorrect);
        startActivity(resultActivity);
        finish();
    }
}