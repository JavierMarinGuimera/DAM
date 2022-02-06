package com.javiermarin.mp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // CONSTANTS:
    private static final int CAN_WE_READ = 0;
    private static final String ROOT_PATH_SD = Environment.getExternalStorageDirectory().getPath() + "/";
    private static final int REWIND_SECS = 10;

    // USEFUL APP VARIABLES:
    private int currentSongPosition;
    private MediaPlayer mp;
    private ListView musicList;
    private ArrayList<HashMap<String, String>> songs;
    private Thread updateSeekBar;

    // BUTTONS LAYOUT THINGS:
    private TextView songName;
    private ImageButton rewindSongBtn;
    private SeekBar songSeekBar;
    private ImageButton advanceSongBtn;
    private ImageButton lastSongBtn;
    private ImageButton playSongBtn;
    private ImageButton nextSongBtn;

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

        musicList = findViewById(R.id.musicList);
        songs = getSongs(ROOT_PATH_SD);

        if (songs != null) {
            constructMusicList(musicList, songs);
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
        rewindSongBtn = findViewById(R.id.rewindSongBtn);
        rewindSongBtn.setOnClickListener(v -> rewindAdvanceSong("rewind"));
        advanceSongBtn = findViewById(R.id.advanceSongBtn);
        advanceSongBtn.setOnClickListener(v -> rewindAdvanceSong("advance"));

        // CHANGING SONG BUTTONS:
        lastSongBtn = findViewById(R.id.lastSongBtn);
        lastSongBtn.setOnClickListener(v -> lastNextSong("last"));
        nextSongBtn = findViewById(R.id.nextSongBtn);
        nextSongBtn.setOnClickListener(v -> lastNextSong("next"));

        // PLAY / PAUSE BUTTON:
        playSongBtn = findViewById(R.id.playSongBtn);
        playSongBtn.setOnClickListener(v -> playStopSong());
    }

    private ArrayList<HashMap<String, String>> getSongs(String rootPath) {
        ArrayList<HashMap<String, String>> fileList = new ArrayList<>();

        try {
            File rootFolder = new File(rootPath);

            //here you will get NPE if directory doesn't contains  any file,handle it like this.
            File[] files = rootFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (getSongs(file.getAbsolutePath()) != null) {
                            fileList.addAll(Objects.requireNonNull(getSongs(file.getAbsolutePath())));
                        } else {
                            break;
                        }
                    } else if (file.getName().endsWith(".mp3")) {
                        HashMap<String, String> song = new HashMap<>();
                        song.put("file_path", file.getAbsolutePath());
                        song.put("file_name", file.getName());
                        fileList.add(song);
                    }
                }
            } else {
                Log.println(Log.WARN, "Error", "No files");
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }

    private void constructMusicList(ListView musicList, ArrayList<HashMap<String, String>> songs) {
        List<String> previousSongList = new ArrayList<>();

        for (HashMap<String, String> directorySongs : songs) {
            for (Map.Entry<String, String> song : directorySongs.entrySet()) {
                if (!song.getValue().contains("/")) {
                    previousSongList.add(song.getValue());
                }
            }
        }


        musicList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, previousSongList));
    }

    private void askForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CAN_WE_READ);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CAN_WE_READ);
            }
        }
    }

    public void playSongFromList(String selectedSong) {
        Uri songToPlay = null;

        playSongBtn.setImageResource(R.drawable.ic_pause_solid);
        songSeekBar.setProgress(0);

        for (HashMap<String, String> directorySongs : songs) {
            for (Map.Entry<String, String> song : directorySongs.entrySet()) {
                if (song.getValue().endsWith(selectedSong) && song.getValue().contains("/")) {
                    songToPlay = Uri.parse(song.getValue());
                } else if (song.getValue().endsWith(selectedSong)) {
                    songName.setText(song.getValue().substring(0, song.getValue().length() - 4));
                }
            }
        }

        if (songToPlay != null) {
            if (mp != null && mp.isPlaying()) {
                mp.release();
            }

            mp = MediaPlayer.create(this, songToPlay);
            mp.start();

            updateSeekBar = new Thread() {
                @Override
                public void run() {
                    while (mp.isPlaying()) {
                        try {
                            int singleDuration = mp.getDuration() / 100;
                            int newPosition = mp.getCurrentPosition() / singleDuration;
                            songSeekBar.setProgress(newPosition);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            updateSeekBar.start();
        } else {
            Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Buttons methods
     */

    public void rewindAdvanceSong(String action) {
        if (mp != null) {
            if (action.equals("advance")) {
                int nextPosition = mp.getCurrentPosition() + 10000;
                mp.seekTo(nextPosition);

                int singleDuration = mp.getDuration() / 100;
                int newPosition = nextPosition / singleDuration;
                songSeekBar.setProgress(newPosition);
            } else {
                int nextPosition = mp.getCurrentPosition() - 10000;
                mp.seekTo(nextPosition);

                int singleDuration = mp.getDuration() / 100;
                int newPosition = nextPosition / singleDuration;
                songSeekBar.setProgress(newPosition);
            }
        }
    }

    public void moveSongToX(int progress) {
        if (mp != null && mp.isPlaying()) {
            int singleDuration = mp.getDuration() / 100;
            int newPosition = singleDuration * progress;
            mp.seekTo(newPosition);
        }
    }

    public void playStopSong() {
        if (mp != null) {
            if (mp.isPlaying()) {
                playSongBtn.setImageResource(R.drawable.ic_play);
                mp.pause();
            } else {
                playSongBtn.setImageResource(R.drawable.ic_pause_solid);
                mp.start();
            }
        } else {
            Toast.makeText(this, "Select a song first", Toast.LENGTH_SHORT).show();
        }
    }

    public void lastNextSong(String action) {
        if (mp != null) {
            if (action.equals("next")) {
                if (currentSongPosition == musicList.getAdapter().getCount() - 1) {
                    currentSongPosition = 0;
                } else {
                    currentSongPosition++;
                }
            } else {
                if (currentSongPosition == 0) {
                    currentSongPosition = musicList.getAdapter().getCount() - 1;
                } else {
                    currentSongPosition--;
                }
            }

            playSongFromList(musicList.getItemAtPosition(currentSongPosition).toString());
        }
    }
}