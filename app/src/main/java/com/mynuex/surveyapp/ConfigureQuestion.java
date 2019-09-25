package com.mynuex.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfigureQuestion extends AppCompatActivity {
    // Key for storing data
    public static final String EXTRA_NEW_QUESTION = "com.android.mynuex.surveyapp.extra_new_question";
    public static final String EXTRA_OPTION_ONE = "com.android.mynuex.surveyapp.extra_option_one";
    public static final String EXTRA_OPTION_TWO = "com.android.mynuex.surveyapp.extra_option_two";
    // Variables
    EditText mNewQuestion;
    EditText mOptionOne;
    EditText mOptionTwo;
    Button mSaveNewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_question);
        // Use res ID to retrieve inflated objects and assign to variables
        mNewQuestion = findViewById(R.id.new_question);
        mOptionOne = findViewById(R.id.option_one);
        mOptionTwo = findViewById(R.id.option_two);
        mSaveNewQuestion = findViewById(R.id.save_new_question);

        mSaveNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Getting text and setting them to string values to be passed on
                String mNewEnteredQuestion = mNewQuestion.getText().toString();
                String mNewOptionOne = mOptionOne.getText().toString();
                String mNewOptionTwo = mOptionTwo.getText().toString();
                // Create new intent to call back to main activity
                Intent intent = new Intent(ConfigureQuestion.this, MainActivity.class);
                intent.putExtra(EXTRA_NEW_QUESTION, mNewEnteredQuestion);
                intent.putExtra(EXTRA_OPTION_ONE, mNewOptionOne);
                intent.putExtra(EXTRA_OPTION_TWO, mNewOptionTwo);
                setResult(RESULT_OK, intent);
                finish(); // Ending  Activity and returning to main
            }
        });

    }
}
