package com.javiermarin.examenexample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.javiermarin.examenexample.R;

public class ActivityForResult extends AppCompatActivity {

    private EditText inputResult;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(v -> submit());
    }

    private void submit() {
        inputResult = findViewById(R.id.inputResult);

        Intent data = new Intent();
        data.putExtra("result", inputResult.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}