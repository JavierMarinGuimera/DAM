package com.javiermarin.linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    public static int buttons = 0;

    private LinearLayout linearLayout;
    private ScrollView scrollView;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        createNewButton();

        scrollView = findViewById(R.id.scrollView);
        scrollView.getBottomEdgeEffectColor();

        removeButton = findViewById(R.id.removeButtons);
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removeButton();
            }
        });
    }

    public void createNewButton () {
        MaterialButton button = new MaterialButton(this);
        button.setText(Integer.toString(buttons++));
        button.setBackgroundColor(Color.parseColor("#30A0A0"));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createNewButton();
            }
        });

        linearLayout.addView(button);
    }

    public void removeButton() {
        linearLayout.removeAllViews();
        buttons = 0;
        createNewButton();
    }
}