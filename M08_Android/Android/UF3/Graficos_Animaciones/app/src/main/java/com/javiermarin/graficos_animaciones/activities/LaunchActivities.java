package com.javiermarin.graficos_animaciones.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.javiermarin.graficos_animaciones.views.Circle;

public class LaunchActivities extends AppCompatActivity {
    Activities ac;
    Circle circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ac = (Activities) getIntent().getExtras().get("activity");

        switch (ac) {
            case CIRCLES:
                circle = new Circle(this);
                setContentView(circle);
                break;
            case TWEEN:
                setContentView(new Circle(this));
                break;
            case DISPLACEMENTS:
                setContentView(new Circle(this));
                break;
            case ROTATIONS:
                setContentView(new Circle(this));
                break;
            case FADES:
                setContentView(new Circle(this));
                break;
            case CHANGE_COLOR:
                setContentView(new Circle(this));
                break;
            case PROPERTY:
                setContentView(new Circle(this));
                break;

            default:
                System.err.println("Algo ha salido mal");
                return;
        }
    }
}