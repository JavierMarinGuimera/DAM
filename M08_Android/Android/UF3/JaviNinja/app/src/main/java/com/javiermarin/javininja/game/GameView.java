package com.javiermarin.javininja.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.javininja.MainActivity;
import com.javiermarin.javininja.R;
import com.javiermarin.javininja.dialog.DialogManager;
import com.javiermarin.javininja.songs.SongManager;

import java.util.Vector;

public class GameView extends View {
    /**
     * Variables:
     */
    public Vector<GameGraphics> objetivos;
    public int numObjetivos = 2;

    /**
     * Ninja:
     */
    private final GameGraphics ninja; // Gràfic del ninja
    private int giroNinja; // Increment de direcció
    private float aceleracionNinja; // augment de velocitat
    // Increment estàndard de gir i acceleració

    /**
     * Variables staticas movimiento ninja:
     */
    private static final int INC_GIRO = 5;
    private static final float INC_ACELERACION = 0.5f;


    /**
     * Thread y tiempos
     */
    private final ThreadJoc thread = new ThreadJoc();
    // Cada cuanto tiempo queremos procesar cambios (ms)
    private final static int PERIODO_PROCESO = 50;
    // Cuándo se realizó el último proceso
    private long ultimoProceso = 0;

    /**
     * Posicionamiento:
     */
    private float mX = 0, mY = 0;
    private boolean lanzamiento = false;

    /**
     * Lanzamiento:
     */
    private final GameGraphics cuchillo;
    private final static int INC_VELOCITAT_GANIVET = 24;
    private boolean cuchilloActivo = false;
    private int tiempoCuchillo;

    /**
     * Partes objetivo aniquilado:
     */
    private final Drawable[] partesObjetivo = new Drawable[7];

    /**
     * Game Over
     */
    private Activity gameActivity;
    private int score = 0;
    private TextView scorePoints;
    public static boolean confirmed = false;
    private Drawable drawableNinja, drawableCuchillo, drawableEnemigo;

    /**
     * Definiciones de la clase:
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Obtenemos referéncia al recurso ninja_enemic guardado en carpeta Res
        drawableEnemigo = context.getResources().
                getDrawable(R.drawable.ninja_enemigo, null);
        // Creamos los objetivos o blancos e inicializamos su velocidad, ángulo y rotación. La posición inicial no la podemos obtener hasta conocer el ancho y altto de la pantalla.
        objetivos = new Vector<GameGraphics>();

        for (int i = 0; i < numObjetivos; i++) {
            GameGraphics objetivo = new GameGraphics
                    (this, drawableEnemigo);
            objetivo.setIncY(Math.random() * 4 - 2);
            objetivo.setIncX(Math.random() * 4 - 2);
            objetivo.setAngle((int) (Math.random() * 360));
            objetivo.setRotacio((int) (Math.random() * 8 - 4));
            objetivos.add(objetivo);
        }

        // Inicializamos el ninja:
        drawableNinja = context.getResources().
                getDrawable(R.drawable.ninja01, null);
        ninja = new GameGraphics(this, drawableNinja);

        // Inicializamos el cuchillo:
        drawableCuchillo = context.getResources().
                getDrawable(R.drawable.cuchillo, null);
        cuchillo = new GameGraphics(this, drawableCuchillo);

        getObjectiveParts();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void getObjectiveParts() {
        partesObjetivo[0] = this.getResources().
                getDrawable(R.drawable.cabeza_ninja, null); //cap
        partesObjetivo[1] = this.getResources().
                getDrawable(R.drawable.cuerpo_ninja, null); //cos
        partesObjetivo[2] = this.getResources().
                getDrawable(R.drawable.cola_ninja, null);
        partesObjetivo[3] = this.getResources().
                getDrawable(R.drawable.brazo_derecho, null);
        partesObjetivo[4] = this.getResources().
                getDrawable(R.drawable.brazo_izquierdo, null);
        partesObjetivo[5] = this.getResources().
                getDrawable(R.drawable.pierna_derecha, null);
        partesObjetivo[6] = this.getResources().
                getDrawable(R.drawable.pierna_izquierda, null);
    }

    // Métode que ens dóna ample i alt pantalla
    @Override
    protected void onSizeChanged(int ancho, int alto,
                                 int ancho_anter, int alto_anter) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);

        // Disposición del ninja en medio de la pantalla
        ninja.setPosX((ancho - ninja.getAmplada()) / 2);
        ninja.setPosY((alto - ninja.getAltura()) / 2);

        // Una vez que conocemos nuestro ancho y alto, situamos los objetivos de forma aleatoria
        for (GameGraphics objetivo : objetivos) {
            do {
                objetivo.setPosX(Math.random() * (ancho - objetivo.getAmplada()));
                objetivo.setPosY(Math.random() * (alto - objetivo.getAltura()));
            } while (objetivo.distancia(ninja) < (ancho + alto) / 5);
        }

        ultimoProceso = System.currentTimeMillis();
        thread.start();
    }

    /**
     * Método que dibuja la vista
     *
     * @param canvas
     */
    @Override
    synchronized protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (GameGraphics objetivo : objetivos) {
            objetivo.dibujaGrafico(canvas);
        }

        ninja.dibujaGrafico(canvas);
        cuchillo.dibujaGrafico(canvas);
    }


    /**
     * User interaction part:
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lanzamiento = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mX);
                float dy = Math.abs(y - mY);
                if (dy < 6 && dx > 6) {
                    giroNinja = Math.round((x - mX) / 2);
                    lanzamiento = false;
                } else if (dx < 6 && dy > 6) {
                    aceleracionNinja = Math.round((mY - y) / 25);
                    lanzamiento = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                giroNinja = 0;
                aceleracionNinja = 0;
                if (lanzamiento) {
                    disparaGanivet();
                }
                break;
        }
        mX = x;
        mY = y;
        return true;
    }

    @Override
    public boolean onKeyDown(int codiTecla, KeyEvent event) {
        super.onKeyDown(codiTecla, event);

        // Suposem que processarem la pulsació
        boolean procesada = true;
        switch (codiTecla) {
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionNinja = +INC_ACELERACION;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                aceleracionNinja = -INC_ACELERACION;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                giroNinja = -INC_GIRO;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroNinja = +INC_GIRO;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                disparaGanivet();
            default:
                // Si estem aquí, no hi ha pulsació que ens interessi
                procesada = false;
                break;
        }
        return procesada;
    }

    @Override
    public boolean onKeyUp(int codigoTecla, KeyEvent evento) {
        super.onKeyUp(codigoTecla, evento);

        // Suposem que processarem la pulsació
        boolean procesada = true;
        switch (codigoTecla) {
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionNinja = 0;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                aceleracionNinja = 0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroNinja = 0;
                break;
            default:
                // Si estem aquí, no hi ha pulsació que ens interessi
                procesada = false;
                break;
        }
        return procesada;
    }

    /**
     * Thread part:
     */
    class ThreadJoc extends Thread {
        @Override
        public void run() {
            while (true) {
                actualizaMovimiento();
            }
        }
    }

    synchronized protected void actualizaMovimiento() {
        long instant_actual = System.currentTimeMillis();
        // No facis res si el període de procés no s'ha complert.
        if (ultimoProceso + PERIODO_PROCESO > instant_actual) {
            return;
        }
        // Per una execució en temps real calculem retard
        double retardo = (instant_actual - ultimoProceso) / PERIODO_PROCESO;
        ultimoProceso = instant_actual; // Per a la propera vegada
        // Actualitzem velocitat i direcció del personatge Ninja a partir de
        // girNinja i acceleracioNinja (segons l'entrada del jugador)
        ninja.setAngle((int) (ninja.getAngle() + giroNinja * retardo));
        double nIncX = ninja.getIncX() + aceleracionNinja *
                Math.cos(Math.toRadians(ninja.getAngle())) * retardo;
        double nIncY = ninja.getIncY() + aceleracionNinja *
                Math.sin(Math.toRadians(ninja.getAngle())) * retardo;
        // Actualitzem si el módul de la velocitat no és més gran que el màxim
        if (Math.hypot(nIncX, nIncY) <= GameGraphics.getMaxVelocidad()) {
            ninja.setIncX(nIncX);
            ninja.setIncY(nIncY);
        }
        // Actualitzem posicions X i Y
        ninja.incrementaPos(retardo);
        for (GameGraphics objectiu : objetivos) {
            objectiu.incrementaPos(retardo);
        }

        // Actualitzem posició de ganivet
        if (cuchilloActivo) {
            cuchillo.incrementaPos(retardo);
            tiempoCuchillo -= retardo;
            if (tiempoCuchillo < 0) {
                cuchilloActivo = false;
            } else {
                for (int i = 0; i < objetivos.size(); i++)
                    if (cuchillo.verificaColision(objetivos.elementAt(i))) {
                        destruyeObjetivo(i);
                        break;
                    }
            }
        }
    }

    private void disparaGanivet() {
        cuchillo.setPosX(ninja.getPosX() + ninja.getAmplada() / 2 - cuchillo.getAmplada() / 2);
        cuchillo.setPosY(ninja.getPosY() + ninja.getAltura() / 2 - cuchillo.getAltura() / 2);
        cuchillo.setAngle(ninja.getAngle());
        cuchillo.setIncX(Math.cos(Math.toRadians(cuchillo.getAngle())) *
                INC_VELOCITAT_GANIVET);
        cuchillo.setIncY(Math.sin(Math.toRadians(cuchillo.getAngle())) *
                INC_VELOCITAT_GANIVET);
        tiempoCuchillo = (int) Math.min(this.getWidth() / Math.abs(cuchillo.getIncX()),
                this.getHeight() / Math.abs(cuchillo.getIncY())) - 2;
        cuchilloActivo = true;
        new SongManager(this.getContext(), R.raw.lanzamiento).start();
    }

    @SuppressLint("SetTextI18n")
    private void destruyeObjetivo(int i) {
        int numPartes = 3;

        if (objetivos.get(i).getDrawable() == drawableEnemigo) {
            for (int n = 0; n < numPartes; n++) {
                GameGraphics parteObjetivo = new GameGraphics(this, partesObjetivo[n]);
                parteObjetivo.setPosX(objetivos.get(i).getPosX());
                parteObjetivo.setPosY(objetivos.get(i).getPosY());
                parteObjetivo.setIncX(Math.random() * 7 - 3);
                parteObjetivo.setIncY(Math.random() * 7 - 3);
                parteObjetivo.setAngle((int) (Math.random() * 360));
                parteObjetivo.setRotacio((int) (Math.random() * 8 - 4));
                objetivos.add(parteObjetivo);
            }
        }
        objetivos.remove(i);
        cuchilloActivo = false;
        new SongManager(this.getContext(), R.raw.explosion).start();

        score += 5;

        if (scorePoints == null) {
            scorePoints = gameActivity.findViewById(R.id.scorePoints);
        } else {
            // TODO - No consigo recoger el textview para los puntos. Siempre es null. :(
//            scorePoints.setText(Integer.toString(score));
        }

        if (objetivos.size() == 0) {
            endGame();
        }
    }

    /**
     * Game over part:
     */

    public void setGameActivity(Activity activity) {
        this.gameActivity = activity;
    }

    private void endGame() {
        DialogManager.confirmDialog(this.getContext(), score, gameActivity);
        // TODO - Necesito que se haga finish SOLO cuando el usuario acepta el dialog
    }

    public void saveResults() {
        SharedPreferences sharedPref = this.getContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(MainActivity.userName, score);
        editor.apply();
    }
}