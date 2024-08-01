package com.minearchive;

import com.minearchive.struct.PlaybackInfo;
import com.minearchive.struct.PlaybackState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PlaybackInfo info = WinAPI.mediaCurrentPlaying();
        PlaybackState state = WinAPI.mediaCurrentState();

        System.out.println("Song   : " + info.getName());
        System.out.println("Artist : " + info.getArtist());
        System.out.println();

        System.out.println(Arrays.toString(WinAPI.mediaGetCurrentCover()));
        byteArrayToPNG(WinAPI.mediaGetCurrentCover(), "X:\\build\\winAPI\\text.png");
    }

    public static void byteArrayToPNG(byte[] byteArray, String outputPath) {
        try {
            // Convert byte array to BufferedImage
            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            BufferedImage bufferedImage = ImageIO.read(bis);

            // Write BufferedImage to PNG file
            File outputFile = new File(outputPath);
            ImageIO.write(bufferedImage, "png", outputFile);

            System.out.println("PNG image created successfully at: " + outputPath);
        } catch (IOException e) {
            System.err.println("Error converting byte array to PNG: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
