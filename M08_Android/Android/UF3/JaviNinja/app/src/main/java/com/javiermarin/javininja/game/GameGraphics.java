package com.javiermarin.javininja.game;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class GameGraphics {
    private Drawable drawable; //Imatge que dibuixarem
    private double posX, posY; //Posicio
    private double incX, incY; //Velocitat desplacament
    private int angle, rotacio; //Angle i velocitat rotacio
    private int amplada, altura; //Dimensions de la imatge
    private int radiColisio; //Per determinar col.lisio

    //On dibuixem el grafic (utilitzat en view.invalidate)
    private View view;

    // Per a determinar l'espai a esborrar (view.invalidate)
    public static final int MAX_VELOCITAT = 20;

    public GameGraphics(View view, Drawable drawable) {
        this.view = view;
        this.drawable = drawable;
        amplada = drawable.getIntrinsicWidth();
        altura = drawable.getIntrinsicHeight();
        radiColisio = (altura + amplada) / 4;
    }

    public void dibuixaGrafic(Canvas canvas) {
        canvas.save();
        int x = (int) (posX + amplada / 2);
        int y = (int) (posY + altura / 2);
        canvas.rotate((float) angle, (float) x, (float) y);
        drawable.setBounds((int) posX, (int) posY,
                (int) posX + amplada, (int) posY + altura);
        drawable.draw(canvas);
        canvas.restore();
        int rInval = (int) Math.hypot(amplada, altura) / 2 + MAX_VELOCITAT;
        view.invalidate(x - rInval, y - rInval, x + rInval, y + rInval);
    }

    public void incrementaPos(double factor) {
        posX += incX * factor;
        // Si sortim de la pantalla, corregim posició
        if (posX < -amplada / 2) {
            posX = view.getWidth() - amplada / 2;
        }
        if (posX > view.getWidth() - amplada / 2) {
            posX = -amplada / 2;
        }
        posY += incY * factor;
        if (posY < -altura / 2) {
            posY = view.getHeight() - altura / 2;
        }
        if (posY > view.getHeight() - altura / 2) {
            posY = -altura / 2;
        }
        angle += rotacio * factor; //Actualitzem angle
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public double distancia(GameGraphics g) {
        return Math.hypot(posX - g.posX, posY - g.posY);
    }

    public boolean verificaColision(GameGraphics g) {
        return (distancia(g) < (radiColisio + g.radiColisio));
    }

    /**
     * Getters & setters:
     */
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getRotacio() {
        return rotacio;
    }

    public void setRotacio(int rotacio) {
        this.rotacio = rotacio;
    }

    public int getAmplada() {
        return amplada;
    }

    public void setAmplada(int amplada) {
        this.amplada = amplada;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getRadiColisio() {
        return radiColisio;
    }

    public void setRadiColisio(int radiColisio) {
        this.radiColisio = radiColisio;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getMaxVelocitat() {
        return MAX_VELOCITAT;
    }
}
