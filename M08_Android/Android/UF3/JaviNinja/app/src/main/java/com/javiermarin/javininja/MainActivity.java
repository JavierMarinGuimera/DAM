package com.javiermarin.javininja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.javiermarin.javininja.dialog.DialogManager;
import com.javiermarin.javininja.game.Game;
import com.javiermarin.javininja.game.GameView;
import com.javiermarin.javininja.songs.SongManager;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFERENCES = "com-javiermarin.javininja";

    public static String userName = "";

    private SongManager songManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (songManager != null) {
            if (!songManager.isPlaying()) {
                songManager.startSong();
            } else {
                songManager.restartSong();
            }
        }

        if (songManager == null) {
            songManager = new SongManager(this, R.raw.main_song, true);
            songManager.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        songManager.pauseSong();
    }

    private void addFunctionality() {
        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> startGame());

        Button resultsButton = findViewById(R.id.resultsButton);
        resultsButton.setOnClickListener(v -> showResults());

        Button leaveButton = findViewById(R.id.leaveButton);
        leaveButton.setOnClickListener(v -> this.finish());

    }

    private void startGame() {
        if (!userName.equals("")) {
            Intent gameIntent = new Intent(this, Game.class);
            startActivity(gameIntent);
        } else {
            DialogManager.inputDialog(this);
        }
    }

    private void showResults() {
        Intent gameIntent = new Intent(this, ResultsActivity.class);
        startActivity(gameIntent);
    }
}