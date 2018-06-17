package com.example.guoba.learnmaori;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_colors, null);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
        setContentView(R.layout.activity_colors);

        ArrayList<Color> colors = getColors();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ColorRecycleViewAdapter adapter = new ColorRecycleViewAdapter(this, R.layout.colors_list_view_item, colors);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Color> getColors() {
        ArrayList<Color> colors = new ArrayList<Color>();

        colors.add(new Color("Yellow","Kowhai","audio_yellow", "#FFEB3B"));
        colors.add(new Color("Orange","Karaka","audio_orange", "#FF9800"));
        colors.add(new Color("Red","Whero","audio_red", "#F44336"));
        colors.add(new Color("Pink","Mawhero","audio_pink", "#E91E63"));
        colors.add(new Color("Purple","Tawa","audio_purple", "#9C27B0"));
        colors.add(new Color("Blue","Kikorangi","audio_blue", "#2196F3"));
        colors.add(new Color("Green","Kakariki","audio_green", "#4CAF50"));
        colors.add(new Color("Brown","Parauri","audio_brown", "#795548"));
        colors.add(new Color("Grey","Kiwikiwi","audio_grey", "#9E9E9E"));
        colors.add(new Color("White","Ma","audio_white", "#FFFFFF"));
        colors.add(new Color("Black","Mangu","audio_black", "#000000"));

        return colors;
    }

}
