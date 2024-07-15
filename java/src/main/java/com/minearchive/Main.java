package com.minearchive;

import com.minearchive.struct.PlaybackInfo;
import com.minearchive.struct.PlaybackState;

public class Main {
    public static void main(String[] args) {
        PlaybackInfo info = WinAPI.mediaCurrentPlaying();
        PlaybackState state = WinAPI.mediaCurrentState();

        System.out.println("Song   : " + info.getName());
        System.out.println("Artist : " + info.getArtist());
        System.out.println();

    }
}
