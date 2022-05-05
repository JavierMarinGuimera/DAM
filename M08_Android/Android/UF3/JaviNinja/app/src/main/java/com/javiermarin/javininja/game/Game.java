package com.javiermarin.javininja.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.javiermarin.javininja.R;
import com.javiermarin.javininja.songs.SongManager;

public class Game extends AppCompatActivity {

    private GameView gameView;
    private SongManager gameSongManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = findViewById(R.id.gameView);
        gameView.setGameActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gameSongManager == null) {
            gameSongManager = new SongManager(this, R.raw.battle_songs, true);
            gameSongManager.start();
        } else {
            gameSongManager.restartSong();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameSongManager.stopPlaying();
        gameView.saveResults();
    }
}