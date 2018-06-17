package com.example.guoba.learnmaori;

/**
 * Created by Kevin Liu  on 18/11/2017.
 */

public class MaoriItem {
    //Attributes
    protected String icon;
    protected String englishName;
    protected String maoriTranslation;
    protected String audio;

    public MaoriItem(String icon, String eng, String maoriTranslation, String audio) {
        this.setIcon(icon);
        this.setEnglishName(eng);
        this.setMaoriTranslation(maoriTranslation);
        this.setAudio(audio);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getMaoriTranslation() {
        return maoriTranslation;
    }

    public void setMaoriTranslation(String maoriTranslation) {
        this.maoriTranslation = maoriTranslation;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
