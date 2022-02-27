package com.javiermarin.graficosyanimaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.javiermarin.graficosyanimaciones.activities.CircleActivity;

public class MainActivity extends AppCompatActivity {
    public enum Actividades {
        CIRCLES,
        TWEEN
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        Button circlesBtn = findViewById(R.id.circlesBtn);
        circlesBtn.setOnClickListener(v -> loadActivity(Actividades.CIRCLES));

        Button tweenBtn = findViewById(R.id.tweenBtn);
        tweenBtn.setOnClickListener(v -> loadActivity(Actividades.TWEEN));
    }

    private void loadActivity(Actividades actividad) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (actividad) {
            case CIRCLES:
                intent = new Intent(this, CircleActivity.class);
                break;

            case TWEEN:
                intent = new Intent(this, CircleActivity.class);
                break;

            default:
                System.err.println("Algo ha salido mal");
        }

        startActivity(intent);
    }
}