package com.example.guoba.learnmaori;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class NumbersActivity extends AppCompatActivity {
    final static String TAG = "NumbersActivity";
    View.OnClickListener playAllHandler = new View.OnClickListener(){
        private Boolean isPlaying = false;
        private int currentPos = 0;
        public void onClick(View view){
            if(isPlaying){
                isPlaying = false;
            }
            else{
                isPlaying = true;
                currentPos = 0;
                playAll();
            }
        }

        public void playAll(){
            if(isPlaying){
                ListView lv = (ListView)findViewById(R.id.list);
                if(currentPos >= lv.getCount()){
                    isPlaying = false;
                    return ;
                }
                int first = lv.getFirstVisiblePosition();
                int last = first + lv.getChildCount();
                int pos = currentPos++;
                lv.smoothScrollToPosition(pos);
                Log.d(TAG, "pos:"+pos+" first:"+first +" last:"+last);
                if(first > pos || pos >= last){ //waiting to scroll to position when the position is not in screen.
                    currentPos--;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            playAll();
                        }
                    },400);
                }
                else {
                    View v = lv.getChildAt(pos - first);
                    ImageView play = (ImageView) v.findViewById(R.id.image_view_play);
                    play.callOnClick();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            playAll();
                        }
                    }, 1000);
                }
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Log.d(TAG, "getDisplayOptions:" + actionBar.getDisplayOptions());
            View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_numbers, null);

            //set handler for play all.
            Button b = customView.findViewById(R.id.play_all);
            b.setOnClickListener(playAllHandler);
            TextView tv = (TextView) customView.findViewById(R.id.text_play_all);
            tv.setOnClickListener(playAllHandler);

            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);

       }
        setContentView(R.layout.activity_numbers);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(Intent.EXTRA_TEXT);
        Log.d(TAG, msg);

//        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        ArrayList<Number> numbers = getNumbers();
        NumberAdapter itemsAdpter = new NumberAdapter(this, R.layout.numbers_list_view_item, numbers);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(itemsAdpter);
    }

    private Map<Integer, String> getMaoriDigits() {
        Map<Integer, String> words = new LinkedHashMap<Integer, String>();
        words.put(1, "Tahi");
        words.put(2, "Rua");
        words.put(3, "Toru");
        words.put(4, "Wha");
        words.put(5, "Rima");
        words.put(6, "Ono");
        words.put(7, "Whitu");
        words.put(8, "Waru");
        words.put(9, "Iwa");
        words.put(10, "Tekau");
        return words;
    }

    private String getNumberEnglish(int num){
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            default:
                return "" + num;
        }

    }

    private ArrayList<Number> getNumbers() {
        ArrayList<Number> numbers = new ArrayList<Number>();
        Map<Integer, String> words = getMaoriDigits();
        for (Integer key : words.keySet()) {
            int id = key;
            String maoriTranslation = words.get(key);
            String icon = "icon" + id;
            String audio = "audio_" + id;
            String eng = getNumberEnglish(id);
            Number n = new Number(id, icon, eng, maoriTranslation, audio);
            numbers.add(n);
        }
        return numbers;
    }
}
