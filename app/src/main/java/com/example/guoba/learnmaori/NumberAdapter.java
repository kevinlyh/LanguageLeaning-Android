package com.example.guoba.learnmaori;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin Liu  on 09/11/2017.
 */

public class NumberAdapter extends ArrayAdapter {
    int id;
    Context context;
    ArrayList<Number> numbers;

    MediaPlayer player;

    public NumberAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Number> objects) {
        super(context, resource, objects);
        this.id = resource;
        this.context = context;
        this.numbers = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListViewItem = convertView;

        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(id, parent, false);
        }
        Number currentNumber = numbers.get(position);

        ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.image_view_icon);
        int img = context.getResources().getIdentifier(
                currentNumber.getIcon(), "drawable",
                context.getPackageName());

        iconImageView.setImageResource(img);

        TextView maoriTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_maori_word);
        maoriTextView.setText(currentNumber.getEnglishName() + " : " + currentNumber.getMaoriTranslation());

        final String audio = currentNumber.getAudio();
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
                Animation ani = new AlphaAnimation(0.3f, 1.0f);
                ani.setDuration(800);
                play.startAnimation(ani);
            }
        });

        return currentListViewItem;
    }
}
