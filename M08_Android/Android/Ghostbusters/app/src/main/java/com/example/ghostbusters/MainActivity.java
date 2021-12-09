package com.example.ghostbusters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ghostbusters.game.MainGameActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private Button enterGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        inputName = findViewById(R.id.inputName);

        enterGameButton = findViewById(R.id.enterGameButton);
        enterGameButton.setOnClickListener(v -> startGame());
    }

    private void startGame() {
        String name = inputName.getText().toString();

        if (!"".equals(name)) {
            Intent intent = new Intent(this, MainGameActivity.class);
            intent.putExtra("player", name);
            startActivity(intent);
        } else {
            Toast.makeText(this, "The name cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }
}