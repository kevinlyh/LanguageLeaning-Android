package com.example.guoba.learnmaori;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View.OnClickListener CardNumbersHandler = new View.OnClickListener(){
        public void onClick(View view){
            Intent numbersActivity = new Intent(getBaseContext(),NumbersActivity.class);
            numbersActivity.putExtra(Intent.EXTRA_TEXT,"Test for message transfer.");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    new Pair<View,String>(findViewById(R.id.card_numbers_text),"numbers_text"),
                    new Pair<View,String>(findViewById(R.id.card_numbers_logo),"numbers_logo"));
            startActivity(numbersActivity, options.toBundle());
        }
    };
    View.OnClickListener CardFamilyMembersHandler = new View.OnClickListener(){
        public void onClick(View view){
            Intent familyMembersActivity = new Intent(getBaseContext(),FamilyMembersActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    new Pair<View,String>(findViewById(R.id.card_family_memebers_logo),"family_members_logo"),
                    new Pair<View,String>(findViewById(R.id.card_family_members_text),"family_members_text"));
            startActivity(familyMembersActivity, options.toBundle());
        }
    };
    View.OnClickListener CardColorsHandler = new View.OnClickListener(){
        public void onClick(View view){
            Intent colorsActivity = new Intent(getBaseContext(),ColorsActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    new Pair<View,String>(findViewById(R.id.card_colors_logo),"colors_logo"),
                    new Pair<View,String>(findViewById(R.id.card_colors_text),"colors_text"));
            startActivity(colorsActivity, options.toBundle());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView cardNumbers = (CardView) findViewById(R.id.card_numbers);
        cardNumbers.setOnClickListener(CardNumbersHandler);
        CardView cardFamilyMembers = (CardView) findViewById(R.id.card_family_memebers);
        cardFamilyMembers.setOnClickListener(CardFamilyMembersHandler);
        CardView cardColors = (CardView) findViewById(R.id.card_colors);
        cardColors.setOnClickListener(CardColorsHandler);
    }
}
