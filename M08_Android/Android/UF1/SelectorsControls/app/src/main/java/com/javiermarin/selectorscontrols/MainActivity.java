package com.javiermarin.selectorscontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.javiermarin.selectorscontrols.checkboxes.CheckboxesActivity;
import com.javiermarin.selectorscontrols.listView.ListViewActivity;
import com.javiermarin.selectorscontrols.radioButtons.RadioButtonsActivity;
import com.javiermarin.selectorscontrols.spinner.SpinnerAcitivity;

public class MainActivity extends AppCompatActivity {

    private Button openRadioButtons;
    private Button openCheckboxes;
    private Button openListView;
    private Button openSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openRadioButtons = findViewById(R.id.openRadioButtons);
        openRadioButtons.setOnClickListener(view -> loadActivity("radioButtons"));

        openCheckboxes = findViewById(R.id.openCheckboxes);
        openCheckboxes.setOnClickListener(view -> loadActivity("checkboxes"));

        openListView = findViewById(R.id.openListView);
        openListView.setOnClickListener(view -> loadActivity("listView"));

        openSpinner = findViewById(R.id.openSpinner);
        openSpinner.setOnClickListener(view -> loadActivity("spinner"));
    }

    private void loadActivity(String activity) {
        Intent intent = new Intent(this, MainActivity.class);

        switch (activity) {
            case "radioButtons":
                intent = new Intent(this, RadioButtonsActivity.class);
                break;

            case "checkboxes":
                intent = new Intent(this, CheckboxesActivity.class);
                break;

            case "listView":
                intent = new Intent(this, ListViewActivity.class);
                break;

            case "spinner":
                intent = new Intent(this, SpinnerAcitivity.class);
                break;

            default:
                Toast.makeText(this, "Algo ha salido mal", Toast.LENGTH_SHORT);
                break;
        }

        startActivity(intent);
    }
}