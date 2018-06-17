package com.example.guoba.learnmaori;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin Liu  on 18/11/2017.
 */

public class ColorAdapter extends ArrayAdapter {
    int id;
    Context context;
    ArrayList<Color> colors;

    MediaPlayer player;

    public ColorAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Color> objects) {
        super(context, resource, objects);
        this.id = resource;
        this.context = context;
        this.colors = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListViewItem = convertView;

        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(id, parent, false);
        }
        Color currentItem = colors.get(position);

        ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.image_view_icon);
        Drawable fg = iconImageView.getDrawable();
        fg.setTint(android.graphics.Color.parseColor(currentItem.getColor()));

        Drawable bg = iconImageView.getBackground();
        if(currentItem.getColor().equals("#FFFFFF")) {
            bg.setTint(android.graphics.Color.GRAY);
        }
        else
        {
            bg.setTint(android.graphics.Color.WHITE);
        }

        TextView engTextView = (TextView) currentListViewItem.findViewById(R.id.id_eng_word);
        engTextView.setText(currentItem.getEnglishName());

        TextView maoriTextView = (TextView) currentListViewItem.findViewById(R.id.id_maori_word);
        maoriTextView.setText(currentItem.getMaoriTranslation());

        TextView midTextView = (TextView) currentListViewItem.findViewById(R.id.id_mid_symbol);
        midTextView.setText("->");

        final String audio = currentItem.getAudio();
        final ImageView play = (ImageView) currentListViewItem.findViewById(R.id.image_view_play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = context.getResources().getIdentifier(
                        audio, "raw",
                        context.getPackageName());

                if (player != null){
                    player.release();
                }
                player = MediaPlayer.create(context, i);
                player.start();

                play.setAlpha(0.1f);
                play.animate().alpha(1.0f).setDuration(800).setListener(null);

            }
        });

        return currentListViewItem;
    }
}
