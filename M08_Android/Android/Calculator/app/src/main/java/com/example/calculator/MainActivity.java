package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText number1;
    private EditText number2;
    private Button calculateButton;
    private TextView operationResult;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        operationResult = findViewById(R.id.operationResult);

        calculateButton = findViewById(R.id.calculateOperation);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeOperation(number1, number2, operationResult);
            }
        });
    }



    public void makeOperation(EditText number1, EditText number2, TextView operationResult) {
        errorText = findViewById(R.id.errorText);
        errorText.setText(number1.getText().toString());

        int val1;
        int val2;

        val1 = (number1.getText().toString() != "") ? Integer.parseInt(number1.getText().toString()) : 0;
        val2 = (number2.getText().toString() != "") ? Integer.parseInt(number2.getText().toString()) : 0;
        int res = val1 + val2;

        operationResult.setText(String.format(new Locale("es", "ES"),"%,d", res));
    }
}