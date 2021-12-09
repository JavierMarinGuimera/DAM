package com.javiermarin.activities.lords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.javiermarin.activities.R;
import com.javiermarin.activities.password.WelcomeUserActivity;

public class LordsMainActivity extends AppCompatActivity {

    private static final int CHECK_VALUES_ID = 0;

    private Button checkName;
    private EditText nameInput;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lords_main);

        returnButton = findViewById(R.id.returnMenuLords);
        returnButton.setOnClickListener(v -> returnToMainActivity());

        checkName = findViewById(R.id.sayName);
        checkName.setOnClickListener(v -> checkValues());
    }

    private void checkValues() {
        nameInput = findViewById(R.id.nameInput);

        Intent intent = new Intent(this, LordsResultsActivity.class);
        intent.putExtra("name", nameInput.getText().toString());
        startActivity(intent);
    }

    private void returnToMainActivity() { finish(); }
}