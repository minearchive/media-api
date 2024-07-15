package com.minearchive.struct;

public class PlaybackState {

    private String raw;
    private Type type;
    private boolean[] enabledStates = new boolean[15];
    private long start, end, position, minSeek, maxSeek;

    enum Type { CHANGING, PLAYING, PAUSED, STOPPED }

    public PlaybackState(String raw) {
        this.raw = raw;
        String[] data = raw.split(",");
        switch (data[0]) {
            case "changing":
                type = Type.CHANGING;
                break;
            case "playing":
                type = Type.PLAYING;
                break;
            case "paused":
                type = Type.PAUSED;
                break;
            case "stopped":
                type = Type.STOPPED;
                break;
        }

        for (int i = 1; i <= 15; i++) {
            enabledStates[i - 1] = data[i].equals("y");
        }

        start = Long.parseLong(data[16]);
        end = Long.parseLong(data[17]);
        position = Long.parseLong(data[18]);
        minSeek = Long.parseLong(data[19]);
        maxSeek = Long.parseLong(data[20]);
    }

    public String getRaw() { return raw; }
    public boolean isPlayEnabled() { return enabledStates[0]; }
    public boolean isPauseEnabled() { return enabledStates[1]; }
    public boolean isStopEnabled() { return enabledStates[2]; }
    public boolean isRecordEnabled() { return enabledStates[3]; }
    public boolean isFastForwardEnabled() { return enabledStates[4]; }
    public boolean isRewindEnabled() { return enabledStates[5]; }
    public boolean isNextEnabled() { return enabledStates[6]; }
    public boolean isPreviousEnabled() { return enabledStates[7]; }
    public boolean isChannelUpEnabled() { return enabledStates[8]; }
    public boolean isChannelDownEnabled() { return enabledStates[9]; }
    public boolean isPlayPauseToggleEnabled() { return enabledStates[10]; }
    public boolean isShuffleEnabled() { return enabledStates[11]; }
    public boolean isRepeatEnabled() { return enabledStates[12]; }
    public boolean isPlaybackRateEnabled() { return enabledStates[13]; }
    public boolean isPlaybackPositionEnabled() { return enabledStates[14]; }
    public long getStart() { return start; }
    public long getEnd() { return end; }
    public long getPosition() { return position; }
    public long getMinSeek() { return minSeek; }
    public long getMaxSeek() { return maxSeek; }

    @Override
    public String toString() {
        return "PlaybackState{" +
                "raw='" + raw + '\'' +
                ", type=" + type +
                ", playEnabled=" + enabledStates[0] +
                ", pauseEnabled=" + enabledStates[1] +
                ", stopEnabled=" + enabledStates[2] +
                ", recordEnabled=" + enabledStates[3] +
                ", fastForwardEnabled=" + enabledStates[4] +
                ", rewindEnabled=" + enabledStates[5] +
                ", nextEnabled=" + enabledStates[6] +
                ", previousEnabled=" + enabledStates[7] +
                ", channelUpEnabled=" + enabledStates[8] +
                ", channelDownEnabled=" + enabledStates[9] +
                ", playPauseToggleEnabled=" + enabledStates[10] +
                ", shuffleEnabled=" + enabledStates[11] +
                ", repeatEnabled=" + enabledStates[12] +
                ", playbackRateEnabled=" + enabledStates[13] +
                ", playbackPositionEnabled=" + enabledStates[14] +
                ", start=" + start +
                ", end=" + end +
                ", position=" + position +
                ", minSeek=" + minSeek +
                ", maxSeek=" + maxSeek +
                '}';
    }
}
