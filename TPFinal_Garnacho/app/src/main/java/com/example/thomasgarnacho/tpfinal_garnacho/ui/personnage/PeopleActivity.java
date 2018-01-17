package com.example.thomasgarnacho.tpfinal_garnacho.ui.personnage;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thomasgarnacho.tpfinal_garnacho.R;
import com.example.thomasgarnacho.tpfinal_garnacho.data.models.SWPeople;

import static com.example.thomasgarnacho.tpfinal_garnacho.ui.personnage.PeopleListActivity.PEOPLE_ADDRESS_KEY;

public class PeopleActivity extends AppCompatActivity {


    public static final int REQUEST_CODE_CHARACTER = 5678;

    private TextView name;
    private TextView mass;
    private TextView hair;
    private TextView eye;
    private TextView gender;
    private TextView height;
    private TextView skin;
    private TextView birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.activity_people);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setContentView(R.layout.activity_people);

        name = findViewById(R.id.name);
        mass = findViewById(R.id.mass);
        height = findViewById(R.id.height);
        eye = findViewById(R.id.eyeColor);
        skin = findViewById(R.id.skinColor);
        gender = findViewById(R.id.gender);
        birth = findViewById(R.id.birthYear);
        hair = findViewById(R.id.hairColor);



        SWPeople swPeople = getIntent().getParcelableExtra(PEOPLE_ADDRESS_KEY);


        name.setText(swPeople.name);
        mass.setText(swPeople.mass);
        height.setText(swPeople.height);
        eye.setText(swPeople.eyeColor);
        skin.setText(swPeople.skinColor);
        birth.setText(swPeople.birthYear);
        hair.setText(swPeople.hairColor);
        gender.setText(swPeople.gender);



    }

    public static Intent getStartIntent(final Context context){
        return new Intent (context, PeopleActivity.class);
    }
}
