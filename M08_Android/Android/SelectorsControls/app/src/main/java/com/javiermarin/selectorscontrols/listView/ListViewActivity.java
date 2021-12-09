package com.javiermarin.selectorscontrols.listView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.selectorscontrols.R;
import com.javiermarin.selectorscontrols.data.AmericanCountries;

public class ListViewActivity extends AppCompatActivity {

    private TextView listViewResult;
    private ListView listView;
    private Button listViewCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listViewResult = findViewById(R.id.listViewResult);

        createItemList();

        listViewCancelButton = findViewById(R.id.listViewCancelButton);
        listViewCancelButton.setOnClickListener(view -> returnMenu());
    }

    private void createItemList() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, AmericanCountries.COUNTRIES);

        listView =
                findViewById(R.id.listView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> listViewClick(i));
    }

    private void listViewClick(int position) {
        int area = AmericanCountries.AREAS.get(position);
        listViewResult.setText("La extensión de " + listView.getItemAtPosition(position).toString() + " es de: " + AmericanCountries.convertDistance(area) + " km2");
    }

    private void returnMenu() {
        finish();
    }
}