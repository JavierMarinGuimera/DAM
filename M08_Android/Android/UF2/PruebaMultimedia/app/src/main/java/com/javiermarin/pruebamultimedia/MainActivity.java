package com.javiermarin.pruebamultimedia;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button botonReproducir;
    private TextView lbEstado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonReproducir = (Button) findViewById(R.id.botonReproducir);
        lbEstado = (TextView) findViewById(R.id.lbEstado);

        //También puedes asociar la propiedad Onclic del botón con el método onClick en vez de utilizar OnClickListener
        //botonReproducir.setOnClickListener(new //View.OnClickListener() {
        botonReproducir.setOnClickListener(clickListener -> {
            MediaPlayer mp =
                    MediaPlayer.create(MainActivity.this,
                            R.raw.letitbe);
            mp.start();
            lbEstado.setText("Reproduciendo...");
            mp.setOnCompletionListener(completionListener -> lbEstado.setText("Fin de la Reproducción"));
        });
    }
}