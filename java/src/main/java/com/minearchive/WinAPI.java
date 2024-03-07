package com.minearchive;

import java.nio.file.Paths;

public class WinAPI {
    static {
        System.load("winAPI.dll");
    }

    //play back
    private static native void tryStart();
    private static native void tryStop();
    private static native void trySkipNext();
    private static native void trySkipPrevious();

    //status
//    private static native void tryGetPlayingTitle();

    public static void mediaStart() {
        tryStart();
    }

    public static void mediaStop() {
        tryStop();
    }

    public static void mediaSkipNext() {
        trySkipNext();
    }

    public static void mediaSkipPrevious() {
        trySkipPrevious();
    }

}