package com.example.guoba.learnmaori;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_family_members, null);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
        setContentView(R.layout.activity_family_members);

        ArrayList<FamilyMember> familyMembers = getFamilyMembers();
        FamilyMemberAdapter itemsAdpter = new FamilyMemberAdapter(this, R.layout.family_members_list_view_item, familyMembers);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(itemsAdpter);
    }

    private Map<String,String> getMaoriFamilyMembers(){
        Map<String,String> words = new LinkedHashMap<String,String>();
        words.put("Father","Pāpā");
        words.put("Mother","Whaea");
        words.put("Wife","Wahine");
        words.put("Husband","Tāne");
        words.put("Child","Tamaiti");
        words.put("Children","Tamariki");
        words.put("Daughter","Tamāhine");
        words.put("Son","Tama");
        words.put("Brother","Tungāne");
        words.put("Sister","Tuahine");
//        words.put("Grandmother","Tipuna wahine");
//        words.put("Grandfather","Tupuna tāne");
//        words.put("Girl","Kotiro");
//        words.put("Baby","Pepi");
        return words;
    }

    private ArrayList<FamilyMember> getFamilyMembers() {
        ArrayList<FamilyMember> familyMembers = new ArrayList<FamilyMember>();
        Map<String, String> words = getMaoriFamilyMembers();

        for (String key : words.keySet()) {
            String eng = key;
            String maoriTranslation = words.get(key);
            String icon = "icon_" + eng.toLowerCase();
            String audio = "audio_" + eng.toLowerCase();
            FamilyMember n = new FamilyMember(icon, eng, maoriTranslation, audio);
            familyMembers.add(n);
        }
        return familyMembers;
    }

}
