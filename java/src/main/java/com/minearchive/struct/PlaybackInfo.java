package com.minearchive.struct;

public class PlaybackInfo {

    private final String raw, name, artist, albumName, albumArtist, track, albumRange;

    public PlaybackInfo(String raw) {
        this.raw = raw;
        String[] data = raw.split(",");
        name = data[0];
        artist = data[1];
        albumName = data[2];
        albumArtist = data[3];
        track = data[4];
        albumRange = data[5];
    }

    public String getRaw() {
        return raw;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getTrack() {
        return track;
    }

    public String getAlbumRange() {
        return albumRange;
    }

    @Override
    public String toString() {
        return "PlaybackInfo{" +
                "raw='" + raw + '\'' +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", track='" + track + '\'' +
                ", albumRange='" + albumRange + '\'' +
                '}';
    }

}
