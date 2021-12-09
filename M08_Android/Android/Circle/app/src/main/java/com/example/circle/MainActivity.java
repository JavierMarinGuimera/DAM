package com.example.circle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText number1;
    private Button calculateButton;
    private Button resetButton;
    private TextView operationResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        operationResult = findViewById(R.id.operationResult);

        calculateButton = findViewById(R.id.calculateOperation);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeOperation(number1, operationResult);
            }
        });

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {resetValues(number1);}
        });
    }



    public void makeOperation(EditText number1, TextView operationResult) {
        int val1 = (TextUtils.isEmpty(number1.getText().toString()))
                ? 0 : Integer.parseInt(number1.getText().toString());

        if (val1 == 0) {
            number1.setText(Integer.toString(val1));
        }

        double res = Math.PI * (Math.pow(val1, 2));

        operationResult.setText(String.format(new Locale("es", "ES"),"%,.2f", res));
    }

    public void resetValues(EditText number1) {
        number1.setText("");
        operationResult.setText("");
    }
}