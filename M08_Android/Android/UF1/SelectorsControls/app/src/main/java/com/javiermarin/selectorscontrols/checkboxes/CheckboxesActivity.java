package com.javiermarin.selectorscontrols.checkboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.selectorscontrols.R;

public class CheckboxesActivity extends AppCompatActivity {

    private EditText checkboxFirstInput;
    private EditText checkboxSecondInput;
    private CheckBox checkboxAdd;
    private CheckBox checkboxSubstract;
    private TextView checkboxResultText;
    private Button checkboxCalculateButton;
    private Button checkboxResetButton;
    private Button checkboxCancelButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_checkboxes);

        checkboxFirstInput = findViewById(R.id.checkboxFirstInput);
        checkboxSecondInput = findViewById(R.id.checkboxSecondInput);

        checkboxAdd = findViewById(R.id.checkboxAdd);
        checkboxSubstract = findViewById(R.id.checkboxSubstract);

        checkboxCalculateButton = findViewById(R.id.checkboxCalculateButton);
        checkboxCalculateButton.setOnClickListener(v -> calculate());

        checkboxResetButton = findViewById(R.id.checkboxResetButton);
        checkboxResetButton.setOnClickListener(v -> reset());

        checkboxCancelButton = findViewById(R.id.checkboxCancelButton);
        checkboxCancelButton.setOnClickListener(v -> returnMenu());
    }

    private void calculate() {

        if (!checkboxAdd.isChecked() && !checkboxSubstract.isChecked()) {
            Toast.makeText(this, "Selecciona una opción al menos", Toast.LENGTH_SHORT).show();
        } else if (!"".equals(checkboxFirstInput.getText().toString()) && !"".equals(checkboxSecondInput.getText().toString())) {
            int[] res = {Integer.MIN_VALUE, Integer.MIN_VALUE};
            if (checkboxAdd.isChecked()) {
                res[0] = Integer.parseInt(checkboxFirstInput.getText().toString()) + Integer.parseInt(checkboxSecondInput.getText().toString());
            }

            if (checkboxSubstract.isChecked()){
                res[1] = Integer.parseInt(checkboxFirstInput.getText().toString()) - Integer.parseInt(checkboxSecondInput.getText().toString());
            }
            checkboxResultText = findViewById(R.id.checkboxResultText);
            checkboxResultText.setText((checkboxAdd.isChecked() ? "Add: " + res[0] : "") + (checkboxAdd.isChecked() && checkboxSubstract.isChecked() ? "\n" : "") + (checkboxSubstract.isChecked() ? "Substract: " + res[1] : ""));
        } else {
            Toast.makeText(this, "Debes introducir 2 números!", Toast.LENGTH_SHORT).show();
        }

    }

    private void reset() {
        checkboxFirstInput.setText("");
        checkboxSecondInput.setText("");
        checkboxAdd.setChecked(false);
        checkboxSubstract.setChecked(false);
        checkboxResultText.setText("");
    }

    private void returnMenu() {
        finish();
    }
}