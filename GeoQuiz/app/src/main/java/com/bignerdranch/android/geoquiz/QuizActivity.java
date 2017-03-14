package com.bignerdranch.android.geoquiz;

import android.content.Intent;
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
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;
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
    private boolean cheater = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");

        setContentView(R.layout.activity_quiz);

        //Getting the index from the savedInstance, which is saved when the screen is rotated or activity is destroyed by OS to conserve memory
        if(savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (Button) findViewById(R.id.next_button);
        cheatButton = (Button) findViewById(R.id.cheat_button);
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
                cheater = false;
                questionText.setText(questionBank[currentIndex].getTextResId());
            }
        });

        //Cheat Button click listener
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CheatActivity.newIntent(QuizActivity.this, questionBank[currentIndex].getAnswerTrue());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_CODE_CHEAT) {
            if(data == null) {
                return;
            }

            cheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private void isAnswerTrue(boolean trueButtonPressed) {
        boolean answerTrue = questionBank[currentIndex].getAnswerTrue();
        int answer = 0;
        if(cheater) {
            answer = R.string.judgment_toast;
        }
        else {
            answer = (answerTrue == trueButtonPressed) ? R.string.correct_toast : R.string.incorrect_toast;
        }
        
        Toast.makeText(QuizActivity.this, answer, Toast.LENGTH_SHORT).show();
    }

}
