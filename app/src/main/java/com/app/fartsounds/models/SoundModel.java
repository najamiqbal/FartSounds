package com.app.fartsounds.models;

public class SoundModel {

    String name;
    int nameId;
    String fileName;
    String nameFrench;
    String duration;
    boolean isPlaying;

    public SoundModel(int nameId, String n, String fn, String nFr, String d) {
        this.nameId = nameId;
        this.name = n;
        this.fileName = fn;
        this.nameFrench = nFr;
        this.duration = d;
        this.isPlaying = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNameFrench() {
        return nameFrench;
    }

    public void setNameFrench(String nameFrench) {
        this.nameFrench = nameFrench;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }
}