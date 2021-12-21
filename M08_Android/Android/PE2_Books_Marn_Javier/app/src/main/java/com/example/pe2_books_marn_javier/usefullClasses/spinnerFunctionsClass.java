package com.example.pe2_books_marn_javier.usefullClasses;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class spinnerFunctionsClass {

    private spinnerFunctionsClass() {}

    public static void initSpinner(Spinner spinner, List<String> data, Context context) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, spinner.getItemAtPosition(position).toString() + " selected.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
