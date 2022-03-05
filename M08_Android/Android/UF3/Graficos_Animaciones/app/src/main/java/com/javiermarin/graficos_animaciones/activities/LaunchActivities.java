package com.javiermarin.graficos_animaciones.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;

import com.javiermarin.graficos_animaciones.MainActivity;

public class LaunchActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (getIntent().getExtras().get("activity")) {
            case MainActivity.Actividades.CIRCLES:
                setContentView(new Circle(this));
                break;
            case MainActivity.Actividades.TWEEN:
                setContentView(new Circle(this));
                break;
            case MainActivity.Actividades.DISPLACEMENTS:
                setContentView(new Circle(this));
                break;
            case MainActivity.Actividades.ROTATIONS:
                setContentView(new Circle(this));
                break;
            case MainActivity.Actividades.FADES:
                setContentView(new Circle(this));
                break;
            case MainActivity.Actividades.CHANGE_COLOR:
                setContentView(new Circle(this));
                break;
            case MainActivity.Actividades.PROPERTY:
                setContentView(new Circle(this));
                break;

            default:
                System.err.println("Algo ha salido mal");
                return;
        }
    }

    private static class Circle extends View {
        private ShapeDrawable circulo;

        public Circle(Context contexto) {
            super(contexto);
            setFocusable(true);
            this.circulo = new ShapeDrawable(new OvalShape());
            this.circulo.getPaint().setColor(0xFF00FF00);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            int posicionX = 10;
            int posicionY = 10;
            int ancho = 300;
            int alto = 300;
            this.circulo.setBounds(posicionX, posicionY, posicionX
                    +
                    ancho, posicionY + alto);
            this.circulo.draw(canvas);
        }
    }
}