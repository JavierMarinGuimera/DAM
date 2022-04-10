package com.javiermarin.javininja.songs;

import java.util.List;
import java.util.TreeMap;

public class SongsManager {

    public static TreeMap<String, Song> songs;

    static {
        songs = new TreeMap<>();
    }

    public static void addSong(String type, Song song) {
        songs.put(type, song);
    }
}
