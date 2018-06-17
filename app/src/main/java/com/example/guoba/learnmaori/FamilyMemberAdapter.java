package com.example.guoba.learnmaori;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin Liu  on 18/11/2017.
 */

public class FamilyMemberAdapter extends ArrayAdapter {
    int id;
    Context context;
    ArrayList<FamilyMember> familyMembers;

    MediaPlayer player;

    public FamilyMemberAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FamilyMember> objects) {
        super(context, resource, objects);
        this.id = resource;
        this.context = context;
        this.familyMembers = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListViewItem = convertView;

        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(id, parent, false);
        }
        FamilyMember currentItem = familyMembers.get(position);

        ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.image_view_icon);
        int img = context.getResources().getIdentifier(
                currentItem.getIcon(), "drawable",
                context.getPackageName());

        iconImageView.setImageResource(img);

        TextView engTextView = (TextView) currentListViewItem.findViewById(R.id.id_eng_word);
        engTextView.setText(currentItem.getEnglishName());

        TextView maoriTextView = (TextView) currentListViewItem.findViewById(R.id.id_maori_word);
        maoriTextView.setText(currentItem.getMaoriTranslation());

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

                Animation ani = new RotateAnimation(-45, 45, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                ani.setFillAfter(false);
                ani.setRepeatCount(3);
                ani.setRepeatMode(Animation.REVERSE);
                ani.setDuration(260);
                play.startAnimation(ani);

            }
        });

        return currentListViewItem;
    }
}
