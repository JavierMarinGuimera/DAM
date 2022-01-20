package com.javiermarin.imagesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        playButton = findViewById(R.id.playButton);
    }

    public void playGame() {
        List<String> images = randomImgs();
        img1.setImageResource(getResources().getIdentifier(randomImgs(), "drawable", getPackageName()));
        img2.setImageResource(getResources().getIdentifier(randomImgs(), "drawable", getPackageName()));
        img3.setImageResource(getResources().getIdentifier(randomImgs(), "drawable", getPackageName()));
    }

    private String randomImgs() {
        int count = 0;
        return "n" + (int) (Math.random() * 10) + 1;
    }
}