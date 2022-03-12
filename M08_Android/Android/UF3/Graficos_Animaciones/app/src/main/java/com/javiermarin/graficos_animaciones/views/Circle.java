package com.javiermarin.graficos_animaciones.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Circle extends View {
    private ShapeDrawable circulo;
    private Random random;

    private boolean print;
    private int positionX;
    private int positionY;
    private int currentRed;
    private int currentGreen;
    private int currentBlue;
    private int currentSize;
    private boolean incrementa;

    public Circle(Context contexto) {
        super(contexto);
        setFocusable(true);
        this.circulo = new ShapeDrawable(new OvalShape());
        this.currentSize = 50;
        this.incrementa = true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (print) {
            @SuppressLint("DrawAllocation") Paint paint = new Paint();
            int radio = 30 + currentSize;
            paint.setARGB(255, currentRed, currentGreen, currentBlue);
            canvas.drawCircle(positionX, positionY, radio, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();

        switch (eventAction) {
            case MotionEvent.ACTION_MOVE:
                if (random == null) {
                    random = new Random();
                }

                if (incrementa && currentSize < 250) {
                    currentSize += 5;
                } else if (currentSize > 50) {
                    currentSize -= 5;
                    incrementa = false;
                } else {
                    incrementa = true;
                }

                currentRed = random.nextInt(255);
                currentGreen = random.nextInt(255);
                currentBlue = random.nextInt(255);
            case MotionEvent.ACTION_DOWN:
                positionX = (int) event.getX();
                positionY = (int) event.getY();
                print = true;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                print = false;
                break;
        }

        this.invalidate();
        return true;
    }


}
