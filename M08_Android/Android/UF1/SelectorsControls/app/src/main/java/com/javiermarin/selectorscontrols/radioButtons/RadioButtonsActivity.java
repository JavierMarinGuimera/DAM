package com.javiermarin.selectorscontrols.radioButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.selectorscontrols.R;

public class RadioButtonsActivity extends AppCompatActivity {

    private EditText radioFirstInput;
    private EditText radioSecondInput;
    private RadioGroup radioGroup;
    private RadioButton radioButtonAdd;
    private TextView radioResultText;
    private Button radioCalculateButton;
    private Button radioResetButton;
    private Button radioCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_radio_buttons);

        radioFirstInput = findViewById(R.id.radioFirstInput);
        radioSecondInput = findViewById(R.id.radioSecondInput);

        radioGroup = findViewById(R.id.radioGroup);

        radioCalculateButton = findViewById(R.id.radioCalculateButton);
        radioCalculateButton.setOnClickListener(v -> calculate());

        radioResetButton = findViewById(R.id.radioResetButton);
        radioResetButton.setOnClickListener(v -> reset());

        radioCancelButton = findViewById(R.id.radioCancelButton);
        radioCancelButton.setOnClickListener(v -> returnMenu());
    }

    private void calculate() {

        int radioChecked = radioGroup.getCheckedRadioButtonId();

        if (radioChecked == -1) {
            Toast.makeText(this, "Selecciona una opción al menos", Toast.LENGTH_SHORT).show();
        } else if (!"".equals(radioFirstInput.getText().toString()) && !"".equals(radioSecondInput.getText().toString())) {
            int res = 0;
            radioButtonAdd = findViewById(R.id.radioButtonAdd);
            if (radioChecked == radioButtonAdd.getId()) {
                res = Integer.parseInt(radioFirstInput.getText().toString()) + Integer.parseInt(radioSecondInput.getText().toString());
            } else {
                res = Integer.parseInt(radioFirstInput.getText().toString()) - Integer.parseInt(radioSecondInput.getText().toString());
            }
            radioResultText = findViewById(R.id.radioResultText);
            radioResultText.setText("Result: " + res);
        } else {
            Toast.makeText(this, "Debes introducir 2 números!", Toast.LENGTH_SHORT).show();
        }

    }

    private void reset() {
        radioFirstInput.setText("");
        radioSecondInput.setText("");
        radioGroup.clearCheck();
    }

    private void returnMenu() {
        finish();
    }
}