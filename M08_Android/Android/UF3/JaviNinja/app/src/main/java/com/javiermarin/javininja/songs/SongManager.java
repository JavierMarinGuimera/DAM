package com.javiermarin.javininja.songs;

import android.content.Context;
import android.media.MediaPlayer;

import com.javiermarin.javininja.R;

public class SongManager extends Thread {

    private int song;
    private Context context;

    public SongManager(int song, Context context) {
        this.song = song;
        this.context = context;
    }

    @Override
    public void run() {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.lanzamiento);
        mp.start();

        while (mp.isPlaying()) {}

        mp.release();
    }
}
