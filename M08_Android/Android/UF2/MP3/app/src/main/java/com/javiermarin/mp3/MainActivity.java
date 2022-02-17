package com.javiermarin.mp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.javiermarin.mp3.classes.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements Runnable {
    // CONSTANTS:
    private static final int CAN_WE_READ = 0;
    private static final int REWIND_SECS = 10000;

    // USEFUL APP VARIABLES:
    private int currentSongPosition;
    private MediaPlayer mediaPlayer;
    private ListView musicList;
    private List<Song> songs;

    // BUTTONS LAYOUT THINGS:
    private TextView songName;
    private SeekBar songSeekBar;
    private ImageButton playSongBtn;
    private static Thread currentThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
        askForPermissions();
    }

    private void addFunctionality() {
        Toolbar toolbarTop = findViewById(R.id.toolbarTop);
        toolbarTop.setTitle("Música");
        setSupportActionBar(toolbarTop);

        songs = new ArrayList<>();
        musicList = findViewById(R.id.musicList);
        getSongs();

        if (songs != null) {
            constructMusicList(musicList);
        } else {
            Toast.makeText(this, "You don't have songs on your device.", Toast.LENGTH_SHORT).show();
        }

        musicList.setOnItemClickListener((parent, view, position, id) -> {
            currentSongPosition = position;
            playSongFromList(musicList.getItemAtPosition(position).toString());
        });

        songName = findViewById(R.id.songName);
        songSeekBar = findViewById(R.id.songSeekBar);
        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                if (fromUser) {
                    moveSongToX(progress);
                }
            }
        });

        // MOVING BUTTONS:
        ImageButton rewindSongBtn = findViewById(R.id.rewindSongBtn);
        rewindSongBtn.setOnClickListener(v -> rewindAdvanceSong("rewind"));
        ImageButton advanceSongBtn = findViewById(R.id.advanceSongBtn);
        advanceSongBtn.setOnClickListener(v -> rewindAdvanceSong("advance"));

        // CHANGING SONG BUTTONS:
        ImageButton lastSongBtn = findViewById(R.id.lastSongBtn);
        lastSongBtn.setOnClickListener(v -> lastNextSong("last"));
        ImageButton nextSongBtn = findViewById(R.id.nextSongBtn);
        nextSongBtn.setOnClickListener(v -> lastNextSong("next"));

        // PLAY / PAUSE BUTTON:
        playSongBtn = findViewById(R.id.playSongBtn);
        playSongBtn.setOnClickListener(v -> playStopSong());
    }


    private void getSongs() {
        Uri collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
        };
        String selection = MediaStore.Audio.Media.DURATION + " >= ?";
        String[] selectionArgs = new String[]{
                String.valueOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS))};
        String sortOrder = MediaStore.Audio.Media.DISPLAY_NAME + " ASC";

        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            // Cache column indices.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
            int durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);

            while (cursor.moveToNext()) {
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);

                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

                songs.add(new Song(contentUri, name, duration));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void constructMusicList(ListView musicList) {
        List<String> previousSongList = new ArrayList<>();

        for (Song song : songs) {
            previousSongList.add(song.getName().substring(0, song.getName().length() - 4));
        }

        musicList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, previousSongList));
    }

    private void askForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    CAN_WE_READ);
        }
    }

    public void playSongFromList(String selectedSong) {
        Song songToPlay = null;

        playSongBtn.setImageResource(R.drawable.ic_pause_solid);
        songSeekBar.setProgress(0);

        for (Song song : songs) {
            if (song.getName().substring(0, song.getName().length() - 4).equals(selectedSong)) {
                songName.setText(song.getName().substring(0, song.getName().length() - 4));
                songToPlay = song;
            }
        }

        if (songToPlay != null) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.release();
                mediaPlayer = null;
            }

            mediaPlayer = MediaPlayer.create(this, songToPlay.getUri());
            mediaPlayer.start();

            if (currentThread != null && currentThread.isAlive()) currentThread.interrupt();

            currentThread = new Thread(this);
            currentThread.start();
        } else {
            Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Buttons methods:
     */

    public void rewindAdvanceSong(String action) {
        if (mediaPlayer != null) {
            if (action.equals("advance")) {
                int nextPosition = mediaPlayer.getCurrentPosition() + REWIND_SECS;
                mediaPlayer.seekTo(nextPosition);

                int singleDuration = mediaPlayer.getDuration() / 100;
                int newPosition = nextPosition / singleDuration;
                songSeekBar.setProgress(newPosition);
            } else {
                int nextPosition = mediaPlayer.getCurrentPosition() - REWIND_SECS;
                mediaPlayer.seekTo(nextPosition);

                int singleDuration = mediaPlayer.getDuration() / 100;
                int newPosition = nextPosition / singleDuration;
                songSeekBar.setProgress(newPosition);
            }
        }
    }

    public void moveSongToX(int progress) {
        if (mediaPlayer != null) {
            int singleDuration = mediaPlayer.getDuration() / 100;
            int newPosition = singleDuration * progress;
            mediaPlayer.seekTo(newPosition);
        }
    }

    public void playStopSong() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                playSongBtn.setImageResource(R.drawable.ic_play);
                mediaPlayer.pause();
            } else {
                playSongBtn.setImageResource(R.drawable.ic_pause_solid);
                if (currentThread != null) {
                    System.out.println("Hola a todos");
                    synchronized (currentThread) {
                        currentThread.notify();
                    }
                }
            }
        } else {
            Toast.makeText(this, "Playing random song", Toast.LENGTH_SHORT).show();
            playSongFromList(musicList.getItemAtPosition((int) (Math.random() * songs.size())).toString());
        }
    }

    public void lastNextSong(String action) {
        if (mediaPlayer != null) {
            switch (action) {
                case "next":
                    if (currentSongPosition == musicList.getAdapter().getCount() - 1) {
                        currentSongPosition = 0;
                    } else {
                        currentSongPosition++;
                    }
                    break;

                case "last":
                    if (currentSongPosition == 0) {
                        currentSongPosition = musicList.getAdapter().getCount() - 1;
                    } else {
                        currentSongPosition--;
                    }
                    break;

                case "random":
                    currentSongPosition = (int) (Math.random() * songs.size());
                    break;

                default:
                    Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                    break;
            }

            playSongFromList(musicList.getItemAtPosition(currentSongPosition).toString());
        }
    }

    @Override
    public void run() {
        int total = mediaPlayer.getDuration();
        int singleDuration = total / 100;

        while (!currentThread.isInterrupted() && mediaPlayer != null) {
            try {
                synchronized (currentThread) {
                    while (!mediaPlayer.isPlaying()) {
                        currentThread.wait();
                        mediaPlayer.start();
                    }
                }
                int newPosition = mediaPlayer.getCurrentPosition() / singleDuration;
                songSeekBar.setProgress(newPosition);
            } catch (Exception e) {
                return;
            }
        }

        System.out.println("SALIDA");

        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            lastNextSong("next");
        }
    }
}