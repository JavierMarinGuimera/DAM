package com.javiermarin.javininja.songs;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.Toast;

import com.javiermarin.javininja.MainActivity;
import com.javiermarin.javininja.R;
import com.javiermarin.javininja.game.GameView;

public class SongManager extends Thread {

    private final Context context;
    private final int song;
    private boolean loop = false;

    private MediaPlayer mp;
    private boolean playing = false;

    public SongManager(Context context, int song) {
        this.context = context;
        this.song = song;
    }

    public SongManager(Context context, int song, boolean loop) {
        this(context, song);
        this.loop = loop;
    }

    public void startSong() {
        mp.start();
    }

    public void restartSong() {
        mp.seekTo(0);
        mp.start();
    }

    public void pauseSong() {
        mp.pause();
    }

    public void stopPlaying() {
        mp.stop();
        mp.release();
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setVolume(boolean isMuted) {
        if (isMuted) {
            mp.setVolume(0, 0);
        } else {
            mp.setVolume(100, 100);
        }
    }

    /**
     * Thread:
     */
    @Override
    public void run() {
        mp = MediaPlayer.create(this.context, this.song);
        mp.start();

        setVolume(MainActivity.sp.getBoolean("musicMuted", false));

        this.playing = true;

        if (loop) {
            mp.setLooping(loop);
        } else {
            mp.setOnCompletionListener(mediaPlayer -> {
                mediaPlayer.reset();
                mediaPlayer.release();
            });
        }
    }
}
