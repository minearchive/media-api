package com.minearchive;

import com.minearchive.struct.PlaybackInfo;
import com.minearchive.struct.PlaybackState;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WinAPI {

    private static PlaybackInfo info = new PlaybackInfo("None,None,None,None,-1,-1");
    private static PlaybackState state = new PlaybackState("stopped,n,y,y,n,n,n,y,y,n,n,y,n,n,n,y,0,-1,-1,0,-1,");
    private static byte[] cover = new byte[] {};

    static {
        try {
            InputStream stream = WinAPI.class.getResourceAsStream("/com/minearchive/windows-media-api.dll");

            String tempDir = System.getProperty("java.io.tmpdir");
            Path targetPath = Paths.get(tempDir, "windows-media-api.dll");
            Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.load(targetPath.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            info = new PlaybackInfo(tryGetCurrentPlaying());
            state = new PlaybackState(tryGetState());
            cover = tryGetCover();
        }, 0, 100, TimeUnit.MILLISECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdown));
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
        return info;
    }

    public static PlaybackState mediaCurrentState() {
        return state;
    }

    public static byte[] mediaGetCurrentCover() {
        return cover;
    }

}