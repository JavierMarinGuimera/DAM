package com.javiermarin.selectorscontrols.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.selectorscontrols.R;
import com.javiermarin.selectorscontrols.data.AmericanCountries;

public class SpinnerAcitivity extends AppCompatActivity {

    private TextView spinnerResultText;
    private Spinner spinner;
    private Button spinnerCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_acitivity);

        spinnerResultText = findViewById(R.id.spinnerResultText);

        createSpinnerList();

        spinnerCancelButton = findViewById(R.id.spinnerCancelButton);
        spinnerCancelButton.setOnClickListener(v -> returnMenu());
    }

    private void createSpinnerList() {
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, AmericanCountries.COUNTRIES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemClick(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerResultText.setText("hola");
            }
        });
    }

    private void spinnerItemClick(int position) {
        int area = AmericanCountries.AREAS.get(position);
        spinnerResultText.setText("La extensión de " + spinner.getSelectedItem().toString() + " es de: " + AmericanCountries.convertDistance(area) + " km2");
    }

    private void returnMenu() {
        finish();
    }
}