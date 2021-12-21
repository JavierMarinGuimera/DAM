package com.example.pe2_books_marn_javier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public EditText input;
    public Spinner spinner;
    public RadioGroup radioGroup;
    public RadioButton radioButton;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.accelerate);

        addFunctionality();
    }

    private void addFunctionality() {

    }
}