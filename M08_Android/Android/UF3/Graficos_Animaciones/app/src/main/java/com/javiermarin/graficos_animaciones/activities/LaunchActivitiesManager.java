package com.javiermarin.graficos_animaciones.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.javiermarin.graficos_animaciones.R;
import com.javiermarin.graficos_animaciones.views.Circle;

import java.lang.reflect.Array;

public class LaunchActivitiesManager extends AppCompatActivity {
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
                setContentView(R.layout.tween_animation);

                TextView tv1 = findViewById(R.id.tv1);
                TextView tv2 = findViewById(R.id.tv2);
                TextView tv3 = findViewById(R.id.tv3);

                setTweenAnimation(tv1, R.anim.tween_animation1);
                setTweenAnimation(tv2, R.anim.tween_animation2);
                setTweenAnimation(tv3, R.anim.tween_animation3);
                break;
            case DISPLACEMENTS:
                setContentView(R.layout.displacement_animation);
                // reconocemos el image view
                ImageView sun = findViewById(R.id.sun);

                // inflamos el XML de la animación
                Animator sunSet = AnimatorInflater.loadAnimator(this, R.animator.sun_moving);

                // seleccionamos el objetivo de la animación inflada
                sunSet.setTarget(sun);

                // ejecutamos la animación con el objetivo ya establecido
                sunSet.start();

                break;
            case ROTATIONS:
                setContentView(R.layout.rotation_animation);
                TextView tv = this.findViewById(R.id.rotations_tv1);
                AnimatorSet tvSet = new AnimatorSet();

                TimeInterpolator inter = new LinearInterpolator();

                ValueAnimator rotateTv = ObjectAnimator.ofFloat(tv, "rotation", 0, 360);
                rotateTv.setDuration(3000)
                        .setRepeatCount(ValueAnimator.INFINITE);
                rotateTv.setInterpolator(inter);

                tvSet.play(rotateTv);
                tvSet.start();

                ValueAnimator moveTv = ObjectAnimator.ofFloat(tv, "x", 300);
                moveTv.setDuration(3000)
                        .setRepeatMode(ValueAnimator.REVERSE);
                moveTv.setRepeatCount(ValueAnimator.INFINITE);
                moveTv.setInterpolator(inter);

                tvSet.playTogether(rotateTv, moveTv);

                tvSet.play(moveTv).before(rotateTv);
                break;
            case FADES:
                setContentView(R.layout.fade_animation);
                TextView fade_tv1 = findViewById(R.id.fade_tv1);

                ValueAnimator fadeAnim = ObjectAnimator.ofFloat(fade_tv1, "alpha", 1f, 0f);
                fadeAnim.setDuration(3000);
                fadeAnim.setRepeatCount(ValueAnimator.INFINITE);
                fadeAnim.setRepeatMode(ValueAnimator.REVERSE);

                fadeAnim.start();
                break;
            case TEXT_SIZE:
                String texto = "La República Galáctica está sumida en el caos. Los impuestos de las rutas comerciales a los sistemas estelares exteriores están en disputa. Esperando resolver el con un bloqueo de poderosas naves de guerra, la codiciosa Federación de Comercio ha detenido todos los envíos al pequeño planeta de Naboo. Mientras el Congreso de la República debate interminablemente esta alarmante cadena de acontecimientos, el Canciller Supremo ha enviado en secreto a dos Caballeros Jedi, guardianes de la paz y la justicia en la galaxia, para resolver el conflicto....";

                setContentView(R.layout.text_size_animation);

                ConstraintLayout cl = findViewById(R.id.text_layout);

                TextView tv_size = findViewById(R.id.color_tv1);

                tv_size.setText(texto);

                ValueAnimator animator = ValueAnimator.ofFloat(1000, -2000f);
                animator.setDuration(15000);

                animator.addUpdateListener(valueAnimator -> {
                    float animatedValue = (float) valueAnimator.getAnimatedValue();
                    tv_size.setTranslationY(animatedValue);
                });

                animator.start();

                break;

            default:
                System.err.println("Algo ha salido mal");
                return;
        }


    }

    private void setTweenAnimation(TextView tv, int tween_animation) {
        Animation animacion
                = AnimationUtils.loadAnimation(this, tween_animation);
        animacion.setRepeatCount(Animation.INFINITE);
        animacion.setRepeatMode(Animation.REVERSE);
        tv.startAnimation(animacion);
    }
}