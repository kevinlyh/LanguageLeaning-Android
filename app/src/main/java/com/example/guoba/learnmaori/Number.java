package com.example.guoba.learnmaori;

/**
 * Created by Kevin Liu  on 09/11/2017.
 */

public class Number extends MaoriItem {
    private int id;

    public Number(int id, String icon, String eng, String maoriTranslation, String audio) {
        super(icon, eng, maoriTranslation, audio);
        this.setId(id);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
