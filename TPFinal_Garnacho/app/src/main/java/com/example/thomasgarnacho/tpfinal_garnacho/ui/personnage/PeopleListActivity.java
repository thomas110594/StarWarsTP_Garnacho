package com.example.thomasgarnacho.tpfinal_garnacho.ui.personnage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomasgarnacho.tpfinal_garnacho.R;
import com.example.thomasgarnacho.tpfinal_garnacho.data.models.SWModelList;
import com.example.thomasgarnacho.tpfinal_garnacho.data.models.SWPeople;
import com.example.thomasgarnacho.tpfinal_garnacho.data.models.HttpError;
import com.example.thomasgarnacho.tpfinal_garnacho.data.remote.StarWarsApiService;
import com.example.thomasgarnacho.tpfinal_garnacho.data.remote.StarWarsInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thomasgarnacho on 10/01/2018.
 */

public class PeopleListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CHARACTER = 1234;
    public static String PEOPLE_ADDRESS_KEY = "PEOPLE_ADDRESS_KEY";
    private TextView noCharactersFound;
    private final List<SWPeople> listOfCharacters = new ArrayList<>();
    private PeopleAdapter peopleAdapter;
    private int ALL_PEOPLE_PAGES = 9;
    private final StarWarsInterface starWarsInterface= StarWarsApiService.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.activity_characters);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setContentView(R.layout.activity_listcharacters);

        noCharactersFound = findViewById(R.id.emptyCharacters);
        final ListView characters = findViewById(R.id.charactersListView);

        listOfCharacters.clear();
        peopleAdapter = new PeopleAdapter(this, listOfCharacters, characterSelectedListener);
        characters.setAdapter(peopleAdapter);

       for (int i = 0; i<ALL_PEOPLE_PAGES; i++){
            starWarsInterface.getAllPeople(i).enqueue(new Callback<SWModelList>() {

                @Override
                public void onResponse(final Call<SWModelList> call, final Response<SWModelList> response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            handleResponse(response);
                        }
                    });
                }

                @Override
                public void onFailure(final Call<SWModelList> call, final Throwable t) {
                    t.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(PeopleListActivity.this, R.string.name_error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }

    }

    public static Intent getStartIntent(final Context context){
        return new Intent (context, PeopleListActivity.class);
    }

    /**
     * Triggered when a device is selected
     */
    private final PeopleAdapter.OnCharacterSelectedListener characterSelectedListener = new PeopleAdapter.OnCharacterSelectedListener(){
        @Override
        public void handle(final SWPeople character) {
            final Intent data = PeopleActivity.getStartIntent(PeopleListActivity.this);
            data.putExtra(PEOPLE_ADDRESS_KEY, (Parcelable) character);
            startActivity(data);
        }
    };

    /**
     * Handle default response for both GET/POST methods
     */
    private void handleResponse(final Response<SWModelList> response) {
        if (response.isSuccessful()) {
            SWModelList swModelList = (SWModelList) response.body();
            updatePeople(swModelList.results);
        } else { // error HTTP
            try {
                final HttpError error = new Gson().fromJson(response.errorBody().string(), HttpError.class);
                Toast.makeText(PeopleListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (final IOException e) {
                e.printStackTrace();
                Toast.makeText(PeopleListActivity.this, R.string.unknown_error, Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Refresh the list of devices with new values.
     * If the new list is empty, show a message to inform the user.
     */
    private void updatePeople(final List<SWPeople> characters) {
        if (characters != null && characters.size() > 0) {
            noCharactersFound.setVisibility(View.GONE);
            listOfCharacters.addAll(characters);
            peopleAdapter.notifyDataSetChanged();
        } else {
            noCharactersFound.setVisibility(View.VISIBLE); // display the noPeople TextView
        }
    }

}
