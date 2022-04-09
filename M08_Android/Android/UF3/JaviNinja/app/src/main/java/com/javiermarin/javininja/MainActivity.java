package com.javiermarin.javininja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> startGame());

        Button resultsButton = findViewById(R.id.resultsButton);
        resultsButton.setOnClickListener(v -> showResults());

        Button leaveButton = findViewById(R.id.leaveButton);
        leaveButton.setOnClickListener(v -> closeGame());
    }

    private void startGame() {

    }

    private void showResults() {

    }

    private void closeGame() {
        finish();
    }
}