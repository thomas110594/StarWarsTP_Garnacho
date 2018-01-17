package com.example.thomasgarnacho.tpfinal_garnacho.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thomasgarnacho.tpfinal_garnacho.R;
import com.example.thomasgarnacho.tpfinal_garnacho.ui.personnage.PeopleListActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        initUI();
    }


    private void initUI(){
        final Button showListCharacters = findViewById(R.id.showList);
        showListCharacters.setOnClickListener(onShowListCharactersButtonClicked);

    }


    public final View.OnClickListener onShowListCharactersButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Intent intent = PeopleListActivity.getStartIntent(MainActivity.this);
            startActivityForResult(intent, PeopleListActivity.REQUEST_CODE_CHARACTER);
        }
    };



}
