package com.javiermarin.javininja;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.javiermarin.javininja.dialog.DialogManager;
import com.javiermarin.javininja.game.Game;
import com.javiermarin.javininja.game.GameView;
import com.javiermarin.javininja.preferences.PreferenciasActivity;
import com.javiermarin.javininja.songs.SongManager;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences sp;
    private static boolean isPreferenceActivityLoading = false;

    private boolean isMenuOpen = false;
    private ImageButton openCloseButton;
    private LinearLayout menuLayout;
    private boolean isAnimating = false;
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
        if (isPreferenceActivityLoading) {
            isPreferenceActivityLoading = false;
            checkPreferences();
            return;
        }

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
        if (!isPreferenceActivityLoading) {
            songManager.pauseSong();
        }
    }

    /**
     * Métodos propios
     */

    private void addFunctionality() {
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> startGame());

        Button resultsButton = findViewById(R.id.resultsButton);
        resultsButton.setOnClickListener(v -> showResults());

        Button leaveButton = findViewById(R.id.leaveButton);
        leaveButton.setOnClickListener(v -> this.finish());

        openCloseButton = findViewById(R.id.openCloseButton);
        openCloseButton.setOnClickListener(v -> showHideMenu());

        menuLayout = findViewById(R.id.menuLayout);

        Button infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(v -> startActivity(new Intent(this, InfoActivity.class)));

        Button configButton = findViewById(R.id.configButton);
        configButton.setOnClickListener(v -> {
            isPreferenceActivityLoading = true;
            startActivity(new Intent(this, PreferenciasActivity.class));
        });
    }

    private void startGame() {
        if (!sp.getString("username", "").equals("")) {
            startActivity(new Intent(this, Game.class));
        } else {
            Toast.makeText(this, "Introduce your username", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, PreferenciasActivity.class));
        }
    }

    private void showResults() {
        Intent gameIntent = new Intent(this, ResultsActivity.class);
        startActivity(gameIntent);
    }

    private void checkPreferences() {
        songManager.setVolume(!sp.getBoolean("musicMuted", false));
    }

    /**
     * Animación menú
     */

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showHideMenu() {
        if (isMenuOpen) {
            openCloseButton.setImageDrawable(getDrawable(R.drawable.ic_menuicon));

            openCloseMenuAnimation(0f, -500f);

            isMenuOpen = false;
        } else {
            openCloseButton.setImageDrawable(getDrawable(R.drawable.ic_crossicon));
            openCloseMenuAnimation(-500f, 0f);
            isMenuOpen = true;
        }
    }

    private void openCloseMenuAnimation(float startWidth, float finalWidth) {
        if (!isAnimating) {
            isAnimating = true;
            ValueAnimator animator = ValueAnimator.ofFloat(startWidth, finalWidth);
            animator.setDuration(1000);

            animator.addUpdateListener(valueAnimator -> {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                menuLayout.setX(animatedValue);
                if (animatedValue == finalWidth) {
                    isAnimating = false;
                }
            });

            animator.start();
        }
    }
}