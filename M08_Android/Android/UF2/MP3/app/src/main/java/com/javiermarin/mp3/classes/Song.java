package com.javiermarin.mp3.classes;

import android.net.Uri;

public class Song {
    private Uri uri;
    private String name;
    private int duration;

    public Song(Uri uri, String name, int duration) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
