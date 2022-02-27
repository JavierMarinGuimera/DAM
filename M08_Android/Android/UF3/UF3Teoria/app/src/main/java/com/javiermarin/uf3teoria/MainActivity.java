package com.javiermarin.uf3teoria;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import
        android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle
                                 savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VistaSimple(this));
    }
    private static class VistaSimple extends View {
        private ShapeDrawable circulo = new ShapeDrawable();
        public VistaSimple(Context contexto) {
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