package com.javiermarin.examenuf2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int CAN_WE_READ = 0;

    private ImageView imageView;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
        askForPermissions();
    }

    private void addFunctionality() {
        Button guitarra = findViewById(R.id.guitarra);
        guitarra.setOnClickListener(v -> playSound("guitarra"));

        Button flauta = findViewById(R.id.flauta);
        flauta.setOnClickListener(v -> playSound("flauta"));

        Button saxofon = findViewById(R.id.saxofon);
        saxofon.setOnClickListener(v -> playSound("saxofon"));

        Button stop = findViewById(R.id.stop);
        stop.setOnClickListener(v -> stopSound());

        imageView = findViewById(R.id.imageView);
    }

    private void askForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    CAN_WE_READ);
        }
    }

    private void playSound(String image) {
        imageView.setImageResource(getResources().getIdentifier(image, "drawable", getPackageName()));

        if (mp != null) {
            mp.release();
            mp = null;
        }

        mp = MediaPlayer.create(MainActivity.this,
                getResources().getIdentifier(image, "raw", getPackageName()));
        mp.start();

        if (mp.isPlaying()) mp.setLooping(true);
    }

    private void stopSound() {
        if (mp != null) {
            mp.release();
            mp = null;
        }

        imageView.setImageResource(0);
    }
}