package com.javiermarin.imagesgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

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
    private ImageView img1;
    private Button playAgainButton;
    private String correctImgString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        imagesList[0] = findViewById(R.id.img1);
        imagesList[1] = findViewById(R.id.img2);
        imagesList[2] = findViewById(R.id.img3);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> playGame());

        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(v -> playRandomSound(correctImgString));
        playAgainButton.setEnabled(false);
    }

    public void playGame() {
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
        playRandomSound(correctImgString);
    }

    private List<String> randomImages() {
        int count = 0;
        List<String> images = new ArrayList<>();
        while (count != 3) {
            int numImg = (int) (Math.random() * 10) + 1;
            String newImg = "n" + (numImg < 10 ? "0" : "") + numImg;
            System.out.println(newImg);
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

    private void playRandomSound(String image) {
        MediaPlayer mp =
                MediaPlayer.create(MainActivity.this,
                        getResources().getIdentifier(image, "raw", getPackageName()));
        mp.start();
    }

    private void playAnswerSound(String answer) {
        MediaPlayer mp =
                MediaPlayer.create(MainActivity.this,
                        getResources().getIdentifier(answer, "raw", getPackageName()));
        mp.start();
    }
}