package com.javiermarin.activities.password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.javiermarin.activities.R;

public class PasswordMainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button check;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_main);

        check = findViewById(R.id.checkResults);
        check.setOnClickListener(v -> check());

        returnButton = findViewById(R.id.returnMenuPassword);
        returnButton.setOnClickListener(v -> returnToMainActivity());
    }

    private void check() {
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);

        if (password.getText().toString().equals("abc123")) {
            Intent intent = new Intent(this, WelcomeUserActivity.class);
            intent.putExtra("username", username.getText().toString());
            startActivity(intent);
        } else {
            findViewById(R.id.errMessage).setVisibility(View.VISIBLE);
        }
    }

    private void returnToMainActivity() { finish(); }
}