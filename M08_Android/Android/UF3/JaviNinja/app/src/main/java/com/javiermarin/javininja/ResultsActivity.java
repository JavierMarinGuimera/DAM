package com.javiermarin.javininja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {
    private static final int MAX_VALUES = 5;

    private ListView scoresListNames;
    private ListView scoresListValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        addFunctionality();
    }

    private void addFunctionality() {
        scoresListNames = findViewById(R.id.scoresListNames);
        scoresListValues = findViewById(R.id.scoresListValues);

        HashMap<String, Integer> lastScores = getScores();

        putValuesOnLists(lastScores);

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> this.finish());
    }

    private HashMap<String, Integer> getScores() {
        SharedPreferences sharedPref = getSharedPreferences(MainActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        HashMap<String, Integer> scores = (HashMap<String, java.lang.Integer>) sharedPref.getAll();
        scores.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));

        HashMap<String, Integer> sortedMap = new HashMap<>();

        int counter = 0;

        for (Map.Entry<String, Integer> entry: scores.entrySet()) {
            if (counter == MAX_VALUES) {
                break;
            } else {
                counter++;
            }
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    private void putValuesOnLists(HashMap<String, Integer> lastScores) {
        List<String> names = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: lastScores.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
            names.add(entry.getKey());
            values.add(Integer.toString(entry.getValue()));
        }

        // TODO - No muestra valores. :(
        scoresListNames.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names));
        scoresListValues.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values));
    }
}