package com.javiermarin.graficos_animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.javiermarin.graficos_animaciones.activities.Activities;
import com.javiermarin.graficos_animaciones.activities.LaunchActivities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        Button circlesBtn = findViewById(R.id.circlesBtn);
        circlesBtn.setOnClickListener(v -> loadActivity(Activities.getActivity(R.id.circlesBtn)));

        Button tweenBtn = findViewById(R.id.tweenBtn);
        tweenBtn.setOnClickListener(v -> loadActivity(Activities.TWEEN));

        Button displacementsBtn = findViewById(R.id.displacementsBtn);
        displacementsBtn.setOnClickListener(v -> loadActivity(Activities.DISPLACEMENTS));

        Button rotationsBtn = findViewById(R.id.rotationsBtn);
        rotationsBtn.setOnClickListener(v -> loadActivity(Activities.ROTATIONS));

        Button fadesBtn = findViewById(R.id.fadesBtn);
        fadesBtn.setOnClickListener(v -> loadActivity(Activities.FADES));

        Button colorBtn = findViewById(R.id.colorBtn);
        colorBtn.setOnClickListener(v -> loadActivity(Activities.CHANGE_COLOR));

        Button propertyBtn = findViewById(R.id.propertyBtn);
        propertyBtn.setOnClickListener(v -> loadActivity(Activities.PROPERTY));
    }

    private void loadActivity(Activities activity) {
        Intent intent = new Intent(this, LaunchActivities.class);

        intent.putExtra("activity", activity);

        startActivity(intent);
    }
}