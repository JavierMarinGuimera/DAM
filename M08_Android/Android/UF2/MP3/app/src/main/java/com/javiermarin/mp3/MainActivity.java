package com.javiermarin.mp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarTop;
    private ListView musicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        toolbarTop = findViewById(R.id.toolbarTop);
        toolbarTop.setTitle("Música");
        setSupportActionBar(toolbarTop);

        musicList = findViewById(R.id.musicList);
        loadMusic();
    }

    // TODO
    private void loadMusic() {
    }
}