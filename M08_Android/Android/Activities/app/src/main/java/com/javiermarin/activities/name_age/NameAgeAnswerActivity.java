package com.javiermarin.activities.name_age;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.javiermarin.activities.R;

public class NameAgeAnswerActivity extends AppCompatActivity {

    private TextView question;
    private EditText answer;
    private Button submitButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_age_answer);

        String selectedQuestion = getIntent().getExtras().getString("question");

        question = findViewById(R.id.questionInput);
        question.setText(question.getText() + " " + selectedQuestion + ":");

        answer = findViewById(R.id.answerInput);

        if (selectedQuestion.equals("age")) {
            answer.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> launchActivity("submit", selectedQuestion));

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> launchActivity("cancel", selectedQuestion));

    }

    private void launchActivity(String option, String selectedQuestion) {
        if (option.equals("submit")) {
            Intent data = new Intent();
            data.putExtra("finalAnswer", answer.getText().toString());
            data.putExtra("selectedQuestion", selectedQuestion);
            setResult(RESULT_OK, data);
            finish();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}