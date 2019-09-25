package com.mynuex.surveyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Imports to pass data between ConfigureQuestion and MainActivity
import static com.mynuex.surveyapp.ConfigureQuestion.EXTRA_NEW_QUESTION;
import static com.mynuex.surveyapp.ConfigureQuestion.EXTRA_OPTION_ONE;
import static com.mynuex.surveyapp.ConfigureQuestion.EXTRA_OPTION_TWO;

public class MainActivity extends AppCompatActivity {

    // request codes
    private static final int RESULTS_ACTIVITY_REQUEST_CODE = 0;
    private static final int NEW_QUESTION_REQUEST_CODE = 1;
    // Key for storing data
    private static final String TAG = "QuizActivity";
    private static final String YES_INDEX = "yes votes";
    private static final String NO_INDEX = "no votes";

    // Variables
    Button mYesButton;
    Button mNoButton;
    Button mResetButton;
    Button mResultsButton;
    Button mEnterNewQuestion;
    TextView mSurveyQuestion;
    TextView mYesCount;
    TextView mNoCount;

    private int yesVoteCount = 0;
    private int noVoteCount = 0;
    private int resetVotes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use res ID to retrieve inflated objects and assign to variables
        mYesButton = findViewById(R.id.yes_button);
        mNoButton = findViewById(R.id.no_button);
        mResetButton = findViewById(R.id.reset_survey_button);
        mResultsButton = findViewById(R.id.results_button);
        mEnterNewQuestion = findViewById(R.id.enter_new_question);
        mSurveyQuestion = findViewById(R.id.survey_question);
        mYesCount = findViewById(R.id.yes_count);
        mNoCount = findViewById(R.id.no_count);

        if (savedInstanceState != null) { // Saving data
            yesVoteCount = savedInstanceState.getInt(YES_INDEX, yesVoteCount);
            noVoteCount = savedInstanceState.getInt(NO_INDEX, noVoteCount);

            mYesCount.setText(String.valueOf(yesVoteCount));
            mNoCount.setText(String.valueOf(noVoteCount));
        }
        // Listener method for variables
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

        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voteActivity = new Intent(); // create a new Intent object
                voteActivity.setClass(MainActivity.this, ResultsActivity.class); // set the class to VoteActivity and to call another activity
                // set the parameters to send to the VoteActivity
                voteActivity.putExtra("yesVote", yesVoteCount);
                voteActivity.putExtra("noVote",noVoteCount);

                startActivityForResult(voteActivity, RESULTS_ACTIVITY_REQUEST_CODE); // call the voteActivity , this will invoke the VoteActivity
            }
        });

        mEnterNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // create intent and for passing data to another activity/class
                Intent configureSurveyIntent = new Intent(MainActivity.this, ConfigureQuestion.class);
                // Intent caller to another activity
                startActivityForResult(configureSurveyIntent, NEW_QUESTION_REQUEST_CODE);
            }
        });
        // Resetting vote count
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetVotes();
            }
        });
    }

    @Override // Override method to clear or continue votes
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULTS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            boolean resetIsClicked = data.getBooleanExtra(ResultsActivity.EXTRA_RESULT_BUTTON, true);
            if (resetIsClicked) {
                resetVotes();
                Toast.makeText(this, "Votes reset to 0", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Survey continues", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == NEW_QUESTION_REQUEST_CODE && resultCode == RESULT_OK) {
            String newQuestion = data.getStringExtra(ConfigureQuestion.EXTRA_NEW_QUESTION);
            String optionOne = data.getStringExtra(ConfigureQuestion.EXTRA_OPTION_ONE);
            String optionTwo = data.getStringExtra(ConfigureQuestion.EXTRA_OPTION_TWO);

            mSurveyQuestion.setText(newQuestion);
            mYesButton.setText(optionOne);
            mNoButton.setText(optionTwo);
            resetVotes();
            Toast.makeText(MainActivity.this, "New question saved", Toast.LENGTH_LONG).show();
        }
    }

    private void resetVotes() {
        mYesCount.setText(String.valueOf(resetVotes)); //Resetting votes to zero
        mNoCount.setText(String.valueOf(resetVotes));
        yesVoteCount = 0; // Resetting variable back to zero
        noVoteCount = 0;
    }

    // Add vote when button is pressed
    private void addVote() {
        if (mYesButton.isPressed()) {
            yesVoteCount++;
            mYesCount.setText(String.valueOf(yesVoteCount));
        } else if (mNoButton.isPressed()) {
            noVoteCount++;
            mNoCount.setText(String.valueOf(noVoteCount));
        }
    }
    // Data save upon rotation
    @Override
    protected void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putInt(YES_INDEX, yesVoteCount);
        outBundle.putInt(NO_INDEX, noVoteCount);
    }
    @Override // Logging messages on activity
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
