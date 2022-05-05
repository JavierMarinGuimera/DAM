package com.javiermarin.javininja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TwoLineListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {
    private static final int MAX_VALUES = 5;

    private ListView scoresListNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        addFunctionality();
    }

    private void addFunctionality() {
        scoresListNames = findViewById(R.id.scoresListNames);

        HashSet<String> lastScores = getScores();

        putValuesOnLists(lastScores);

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> this.finish());
    }

    private HashSet<String> getScores() {
        return (HashSet<String>) MainActivity.sp.getStringSet("scores", new HashSet<>());
    }
    private void putValuesOnLists(HashSet<String> lastScores) {
        List<String> list = new ArrayList<>();
        lastScores.forEach(s -> {
            list.add(s);
        });

        // TODO - No muestra valores. :(
        scoresListNames.setAdapter(new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, list));
    }
}