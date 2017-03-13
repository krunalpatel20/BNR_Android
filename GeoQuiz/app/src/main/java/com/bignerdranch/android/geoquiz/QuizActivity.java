package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String ANSWER_KEY = "answered_quyestions";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionText;
    private Question[] questionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private boolean answeredQuestions[] = new boolean[questionBank.length];
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");

        setContentView(R.layout.activity_quiz);

        //Getting the index from the savedInstance, which is saved when the screen is rotated or activity is destroyed by OS to conserve memory
        if(savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            answeredQuestions = savedInstanceState.getBooleanArray(ANSWER_KEY);
        }

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (Button) findViewById(R.id.next_button);
        questionText = (TextView) findViewById(R.id.question_text_view);

        questionText.setText(questionBank[currentIndex].getTextResId());

        //True Button click listener
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answeredQuestions[currentIndex]) {
                    answeredQuestions[currentIndex] = true;
                    isAnswerTrue(true);
                }
            }
        });

        //False Button click listener
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answeredQuestions[currentIndex]) {
                    answeredQuestions[currentIndex] = true;
                    isAnswerTrue(false);
                }
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState called");

        outState.putInt(KEY_INDEX, currentIndex);
        outState.putBooleanArray(ANSWER_KEY, answeredQuestions);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    private void isAnswerTrue(boolean trueButtonPressed) {
        boolean answerTrue = questionBank[currentIndex].getAnswerTrue();
        int answer = (answerTrue == trueButtonPressed) ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(QuizActivity.this, answer, Toast.LENGTH_SHORT).show();
    }

}
