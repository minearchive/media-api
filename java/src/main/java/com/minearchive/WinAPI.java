package com.minearchive;

import com.minearchive.struct.PlaybackInfo;
import com.minearchive.struct.PlaybackState;

public class WinAPI {
    static {
        System.load("X:\\build\\winAPI\\rust\\target\\debug\\winAPI.dll");
    }

    //play back
    private static native void tryStart();
    private static native void tryStop();
    private static native void trySkipNext();
    private static native void trySkipPrevious();

    //status
    private static native String tryGetCurrentPlaying();
    private static native String tryGetState();
    private static native byte[] tryGetCover();

    public static void mediaStart() { tryStart(); }
    public static void mediaStop() { tryStop(); }
    public static void mediaSkipNext() { trySkipNext(); }
    public static void mediaSkipPrevious() { trySkipPrevious(); }

    public static PlaybackInfo mediaCurrentPlaying() {
        return new PlaybackInfo(tryGetCurrentPlaying());
    }

    public static PlaybackState mediaCurrentState() {
        return new PlaybackState(tryGetState());
    }

    public static byte[] mediaGetCurrentCover() {
        return tryGetCover();
    }

}