package com.javiermarin.activities.password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.javiermarin.activities.R;

public class WelcomeUserActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button returnFormPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        String username = getIntent().getExtras().getString("username");

        welcomeText = findViewById(R.id.welcomeText);

        if (username.equals("")) {
            welcomeText.setText("Welcome unnamed ;)");
        } else {
            welcomeText.setText("Welcome  " + username);
        }

        returnFormPassword = findViewById(R.id.returnFormPassword);
        returnFormPassword.setOnClickListener(v -> returnFormPassword());
    }

    private void returnFormPassword() { finish(); }
}