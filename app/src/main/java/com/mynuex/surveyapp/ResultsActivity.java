package com.mynuex.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultsActivity extends AppCompatActivity {
    // Key for storing data
    public static final String EXTRA_RESULT_BUTTON = "com.android.mynuex.surveyapp.extra_result_button";
    // Variables
    TextView mYesVoteCount;
    TextView mNoVoteCount;

    int yesVoteCount;
    int noVoteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mYesVoteCount = findViewById(R.id.yes_count_view);
        mNoVoteCount = findViewById(R.id.no_count_view);

        Intent caller = getIntent(); // Intent caller
        yesVoteCount = caller.getIntExtra("yesVote", 0);  // set yesVote parameter,
        noVoteCount = caller.getIntExtra("noVote", 0);

        mYesVoteCount.setText(String.valueOf(yesVoteCount));
        mNoVoteCount.setText(String.valueOf(noVoteCount));

        Button mResetButton = findViewById(R.id.reset_survey_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_RESULT_BUTTON, true);
                setResult(RESULT_OK, returnIntent);
                finish(); // Ending  Activity and returning to main
            }
        });

        Button mContinueSurveyButton = findViewById(R.id.continue_survey_button);
        mContinueSurveyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_RESULT_BUTTON, false);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}