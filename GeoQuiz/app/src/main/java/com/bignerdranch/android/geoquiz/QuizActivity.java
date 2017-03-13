package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionText;
    private Question[] questionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (ImageButton) findViewById(R.id.next_button);
        prevButton = (ImageButton) findViewById(R.id.prev_button);
        questionText = (TextView) findViewById(R.id.question_text_view);


        questionText.setText(questionBank[currentIndex].getTextResId());

        //True Button click listener
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswerTrue(true);
            }
        });

        //False Button click listener
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswerTrue(false);
            }
        });

        //Next Button click listener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex+1) % questionBank.length;
                questionText.setText(questionBank[currentIndex].getTextResId());
            }
        });

        //Text View listener to go to the next question
        questionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex+1) % questionBank.length;
                questionText.setText(questionBank[currentIndex].getTextResId());
            }
        });

        //Text View listener to go to the next question
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex-1);
                currentIndex = currentIndex < 0 ? questionBank.length - 1 : currentIndex;
                questionText.setText(questionBank[currentIndex].getTextResId());
            }
        });
    }

    private void isAnswerTrue(boolean trueButtonPressed) {
        boolean answerTrue = questionBank[currentIndex].getAnswerTrue();
        int answer = (answerTrue == trueButtonPressed) ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(QuizActivity.this, answer, Toast.LENGTH_SHORT).show();
    }

}
