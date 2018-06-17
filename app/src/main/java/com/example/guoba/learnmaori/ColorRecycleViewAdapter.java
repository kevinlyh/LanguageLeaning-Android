package com.example.guoba.learnmaori;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin Liu  on 22/11/2017.
 */

public class ColorRecycleViewAdapter extends RecyclerView.Adapter<ColorRecycleViewAdapter.ColorViewHolder> {
    int id;
    Context context;
    ArrayList<Color> colors;

    MediaPlayer player;


    public ColorRecycleViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Color> objects) {
        this.id = resource;
        this.context = context;
        this.colors = objects;
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        ColorViewHolder colorHolder = (ColorViewHolder) holder;

        View currentListViewItem = colorHolder.itemView;

        Color currentItem = colors.get(position);

        ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.image_view_icon);
        Drawable fg = iconImageView.getDrawable();
        fg.setTint(android.graphics.Color.parseColor(currentItem.getColor()));

        Drawable bg = iconImageView.getBackground();
        if (currentItem.getColor().equals("#FFFFFF")) {
            bg.setTint(android.graphics.Color.GRAY);
        } else {
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

                if (player != null) {
                    player.release();
                }
                player = MediaPlayer.create(context, i);
                player.start();

                play.setAlpha(0.1f);
                play.animate().alpha(1.0f).setDuration(800).setListener(null);

            }
        });
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ColorViewHolder(LayoutInflater.from(context).inflate(id, parent, false));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    class ColorViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        public ColorViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
