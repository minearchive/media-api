package com.minearchive;

import com.minearchive.struct.PlaybackInfo;
import com.minearchive.struct.PlaybackState;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class WinAPI {
    static {
        try {
            InputStream stream = WinAPI.class.getResourceAsStream("/windows-media-api.dll");

            String tempDir = System.getProperty("java.io.tmpdir");
            Path targetPath = Paths.get(tempDir, "windows-media-api.dll");
            Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.load(targetPath.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
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