package com.javiermarin.activities.lords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.activities.R;

import java.util.Locale;

public class LordsResultsActivity extends AppCompatActivity {

    private String users[] = {"gandalf", "frodo", "saruman"};
    private int points[] = {315, 222, 489};
    private Button returnFormLords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lords_results);

        loadResults();

        returnFormLords = findViewById(R.id.returnFormLords);
        returnFormLords.setOnClickListener(v -> returnFormLords());
    }

    private void loadResults() {
        String name = getIntent().getExtras().getString("name");

        TextView nameText = findViewById(R.id.nameTextLords);
        TextView pointsText = findViewById(R.id.pointsTextLords);

        String lordName = name;
        int lordPoints = 0;

        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(name.toLowerCase(Locale.ROOT))) {
                lordPoints = points[i];
            } else if ("".equals(name.toLowerCase(Locale.ROOT))) {
                lordName = "";
            }
        }

        if ("".equals(lordName)) {
            nameText.setText("Is anyone there?");
        } else {
            nameText.setText("Welcome my lord " + lordName + " to your kingdom.");
            pointsText.setText("Your is score is: " + lordPoints + " points.");
        }
    }

    private void returnFormLords() { finish(); }
}