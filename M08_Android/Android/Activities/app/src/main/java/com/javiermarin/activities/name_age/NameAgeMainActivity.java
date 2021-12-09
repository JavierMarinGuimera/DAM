package com.javiermarin.activities.name_age;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.activities.R;
import com.javiermarin.activities.author.AuthorActivity;

public class NameAgeMainActivity extends AppCompatActivity {

    private Button nameButton;
    private Button ageButton;
    private TextView answerResult;
    private Button returnButton;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    printResults(data);
                } else {
                    answerResult.setText("");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_age_main);

        nameButton = findViewById(R.id.nameButton);
        nameButton.setOnClickListener(v -> launchActivity("name"));

        ageButton = findViewById(R.id.ageButton);
        ageButton.setOnClickListener(v -> launchActivity("age"));

        answerResult = findViewById(R.id.answerResult);

        returnButton = findViewById(R.id.returnMainNameAge);
        returnButton.setOnClickListener(v -> returnToMainActivity());
    }

    public void launchActivity(String question) {
        Intent intent = new Intent(this, NameAgeAnswerActivity.class);
        intent.putExtra("question", question);
        activityResultLauncher.launch(intent);
    }

    public void printResults(Intent data) {
        String selectedQuestion = data.getExtras().getString("selectedQuestion");
        String finalAnswer = data.getExtras().getString("finalAnswer");

        if ("".equals(finalAnswer)) {
            answerResult.setText("Okay, maybe you dont have " + selectedQuestion + " ;)");
        } else {
            answerResult.setText("The " + selectedQuestion + " entered is " + finalAnswer);
        }
    }

    private void returnToMainActivity() { finish(); }
}