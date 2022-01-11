package com.javiermarin.activities.author;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.javiermarin.activities.R;

public class
AuthorActivity extends AppCompatActivity {

    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        returnButton = findViewById(R.id.returnMenuAuthor);
        returnButton.setOnClickListener(v -> returnToMainActivity());
    }

    private void returnToMainActivity() { finish(); }


}