package com.javiermarin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.javiermarin.activities.author.AuthorActivity;
import com.javiermarin.activities.lords.LordsMainActivity;
import com.javiermarin.activities.name_age.NameAgeMainActivity;
import com.javiermarin.activities.password.PasswordMainActivity;

public class MainActivity extends AppCompatActivity {

    private Button openAuthor;
    private Button openPassword;
    private Button openLords;
    private Button openNameAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openAuthor = findViewById(R.id.openAuthor);
        openAuthor.setOnClickListener(v -> loadActivity("author"));

        openPassword = findViewById(R.id.openPassword);
        openPassword.setOnClickListener(v -> loadActivity("password"));

        openLords = findViewById(R.id.openLords);
        openLords.setOnClickListener(v -> loadActivity("lords"));

        openNameAge = findViewById(R.id.openNameAge);
        openNameAge.setOnClickListener(v -> loadActivity("nameAge"));
    }

    private void loadActivity(String str) {
        Intent intent;
        intent = new Intent(this, MainActivity.class);

        switch (str) {
            case "author":
                intent = new Intent(this, AuthorActivity.class);
                break;
            case "password":
                intent = new Intent(this, PasswordMainActivity.class);
                break;
            case "lords":
                intent = new Intent(this, LordsMainActivity.class);
                break;
            case "nameAge":
                intent = new Intent(this, NameAgeMainActivity.class);
                break;
        }

        startActivity(intent);
    }

}