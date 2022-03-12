package com.javiermarin.graficos_animaciones;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.javiermarin.graficos_animaciones.activities.Activities;
import com.javiermarin.graficos_animaciones.activities.LaunchActivitiesManager;

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

        Button textSizeBtn = findViewById(R.id.textSizeBtn);
        textSizeBtn.setOnClickListener(v -> loadActivity(Activities.TEXT_SIZE));
    }

    private void loadActivity(Activities activity) {
        Intent intent = new Intent(this, LaunchActivitiesManager.class);

        intent.putExtra("activity", activity);

        startActivity(intent);
    }
}