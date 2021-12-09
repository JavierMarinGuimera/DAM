package com.javiermarin.examenexample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.examenexample.activities.ActivityForResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final List<String> ARRAY_NUMS = new ArrayList<String>();

    static {
        ARRAY_NUMS.add("1");
        ARRAY_NUMS.add("2");
        ARRAY_NUMS.add("3");
    }

    private Button openActivity;
    private Button openActivityForResult;
    private TextView resultActivity;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    printResults(data);
                } else {
                    resultActivity.setText("");
                }
            });
    private ListView listView;
    private TextView listViewResult;
    private Spinner spinner;
    private TextView spinnerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openActivity = findViewById(R.id.openActivity);
        openActivity.setOnClickListener(v -> openActivity());

        openActivityForResult = findViewById(R.id.openActivityForResult);
        openActivityForResult.setOnClickListener(v -> openActivityForResult());

        resultActivity = findViewById(R.id.resultActivity);

        listViewResult = findViewById(R.id.listViewResult);

        createItemList();

        spinnerResult = findViewById(R.id.spinnerResult);

        createSpinner();
    }

    private void openActivity() {
        Toast.makeText(this, "Actividad lanzada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openActivityForResult() {
        Toast.makeText(this, "Actividad para resultado lanzada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActivityForResult.class);
        activityResultLauncher.launch(intent);
    }

    private void printResults(Intent data) {
        String result = data.getExtras().getString("result");

        if ("".equals(result)) {
            resultActivity.setText("¡No has introducido nada.");
        } else {
            resultActivity.setText("Has introducido: " + result);
        }
    }

    private void createItemList() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, ARRAY_NUMS);

        listView =
                findViewById(R.id.listView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> listViewClick(i));
    }

    private void listViewClick(int position) {
        listViewResult.setText("Has seleccionado: " + listView.getItemAtPosition(position).toString());
    }

    private void createSpinner() {
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.ARRAY_NUMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void spinnerItemClick() {
        spinnerResult.setText("Has pulsado:  " + spinner.getSelectedItem().toString());
    }
}