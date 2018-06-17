package com.example.guoba.learnmaori;

/**
 * Created by Kevin Liu  on 18/11/2017.
 */

public class Color extends MaoriItem {
    private String color;
    public Color(String eng, String maoriTranslation, String audio, String color) {
        super(null, eng, maoriTranslation, audio);
        this.setColor(color);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
