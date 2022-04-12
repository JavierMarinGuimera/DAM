package com.javiermarin.javininja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.javiermarin.javininja.game.Game;
import com.javiermarin.javininja.game.GameView;

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
        Intent gameIntent = new Intent(this, Game.class);
        startActivity(gameIntent);
    }

    private void showResults() {
        Intent gameIntent = new Intent(this, GameView.class);
        startActivity(gameIntent);
    }

    private void closeGame() {
        finish();
    }
}