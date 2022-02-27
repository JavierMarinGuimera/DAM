package com.javiermarin.graficosyanimaciones.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CircleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Circle(this));
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
        }

        private void dkjf() {
            this.circulo.draw(canvas);
        }
    }
}