package com.javiermarin.mp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAN_WE_READ = 0;
    private Toolbar toolbarTop;
    private ListView musicList;

    private ImageButton playSongBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
        askForPermissions();
    }

    private void addFunctionality() {
        toolbarTop = findViewById(R.id.toolbarTop);
        toolbarTop.setTitle("Música");
        setSupportActionBar(toolbarTop);

        musicList = findViewById(R.id.musicList);
        loadMusic();

        playSongBtn = findViewById(R.id.playSongBtn);
        playSongBtn.setOnClickListener(v -> playSong());
    }

    private void askForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CAN_WE_READ);
            } else {
                Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CAN_WE_READ);
            }
        }
    }

    // TODO
    private void loadMusic() {

    }

    public void playSong() {
        Uri song = Uri.parse(Environment.getExternalStorageDirectory().getPath() +
                "/Music/" + "01_Uno-1.mp3");

        Toast.makeText(this, song.getPath(), Toast.LENGTH_LONG).show();
        MediaPlayer mp = MediaPlayer.create(this, song);

        Toast.makeText(this, "Playing...", Toast.LENGTH_SHORT).show();
        mp.start();
    }
}