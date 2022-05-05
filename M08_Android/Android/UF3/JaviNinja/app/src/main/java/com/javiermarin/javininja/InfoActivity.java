package com.javiermarin.javininja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button returnMainActivity = findViewById(R.id.returnMainActivity);
        returnMainActivity.setOnClickListener(v -> finish());
    }
}