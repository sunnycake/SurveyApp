package com.mynuex.surveyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String YES_INDEX = "yes votes";
    private static final String NO_INDEX = "no votes";

    Button mYesButton;
    Button mNoButton;
    Button mResetButton;
    TextView mSurveyQuestion;
    TextView mYesCount;
    TextView mNoCount;

    private int yesVoteCount = 0;
    private int noVoteCount = 0;
    private int resetVotes = 0;

    @Override
    protected void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putInt(YES_INDEX, yesVoteCount);
        outBundle.putInt(NO_INDEX, noVoteCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mYesButton = findViewById(R.id.yes_button);
        mNoButton = findViewById(R.id.no_button);
        mResetButton = findViewById(R.id.reset_button);
        mSurveyQuestion = findViewById(R.id.survey_question);
        mYesCount = findViewById(R.id.yes_count);
        mNoCount = findViewById(R.id.no_count);

        if (savedInstanceState != null) {
            yesVoteCount = savedInstanceState.getInt(YES_INDEX, yesVoteCount);
            noVoteCount = savedInstanceState.getInt(NO_INDEX, noVoteCount);

            mYesCount.setText(String.valueOf(yesVoteCount));
            mNoCount.setText(String.valueOf(noVoteCount));
        }

        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVote();
            }
        });

        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVote();
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYesCount.setText(String.valueOf(resetVotes)); //Resetting votes to zero
                mNoCount.setText(String.valueOf(resetVotes));
                yesVoteCount = 0; // Resetting variable back to zero
                noVoteCount = 0;
            }
        });
    }

    private void addVote() {
        if (mYesButton.isPressed()) {
            yesVoteCount++;
            mYesCount.setText(String.valueOf(yesVoteCount)); // Adding votes
        } else if (mNoButton.isPressed()) {
            noVoteCount++;
            mNoCount.setText(String.valueOf(noVoteCount));
        }

    }
}
