package com.javiermarin.imagesgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final ImageView[] imagesList = new ImageView[3];

    private ConstraintLayout layout;
    private Toolbar toolbar;
    private Button playAgainButton;
    private String correctImgString;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();

    }

    private void addFunctionality() {
        layout = findViewById(R.id.layout);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        imagesList[0] = findViewById(R.id.img1);
        imagesList[1] = findViewById(R.id.img2);
        imagesList[2] = findViewById(R.id.img3);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> playGame());

        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(v -> playImgSound(correctImgString));
        playAgainButton.setEnabled(false);
    }

    public void playGame() {
        if (mp != null && !mp.isPlaying()) {
            playAgainButton.setEnabled(true);
            List<String> images = randomImages();
            int correctImgInt = (int) (Math.random() * 3);
            for (int i = 0; i < imagesList.length; i++) {
                imagesList[i].setImageResource(getResources().getIdentifier(images.get(i), "drawable", getPackageName()));
                if ((i) == correctImgInt) {
                    imagesList[i].setOnClickListener(v -> playAnswerSound("win"));
                } else {
                    imagesList[i].setOnClickListener(v -> playAnswerSound("fail"));
                }
            }

            correctImgString = images.get(correctImgInt);
            playImgSound(correctImgString);
        }
    }

    private void changeBackground(String answer) {
        if (answer.equals("win")) {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else if (answer.equals("fail")) {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        } else {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        }
    }

    private List<String> randomImages() {
        int count = 0;
        List<String> images = new ArrayList<>();
        while (count != 3) {
            int numImg = (int) (Math.random() * 10) + 1;
            String newImg = "n" + (numImg < 10 ? "0" : "") + numImg;
            if (!imageIn(images, newImg)) {
                images.add(newImg);
                count++;
            }
        }
        return images;
    }

    private boolean imageIn(List<String> images, String newImg) {
        for (String img : images) {
            if (img.equals(newImg)) {
                return true;
            }
        }
        return false;
    }

    private void playImgSound(String image) {
        changeBackground("start");
        if (mp == null || !mp.isPlaying()) {
            if (mp != null) {
                mp.release();
            }
            mp = MediaPlayer.create(MainActivity.this,
                    getResources().getIdentifier(image, "raw", getPackageName()));
            mp.start();
        }
    }

    private void playAnswerSound(String answer) {
        if (answer.equals("win")) {
            changeBackground("win");
        } else {
            changeBackground("fail");
        }
        MediaPlayer mp =
                MediaPlayer.create(MainActivity.this,
                        getResources().getIdentifier(answer, "raw", getPackageName()));
        if (!mp.isPlaying()) {
            mp.start();
        }
    }
}