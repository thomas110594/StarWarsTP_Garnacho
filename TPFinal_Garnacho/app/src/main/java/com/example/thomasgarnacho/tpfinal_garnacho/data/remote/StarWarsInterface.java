package com.example.thomasgarnacho.tpfinal_garnacho.data.remote;


import com.example.thomasgarnacho.tpfinal_garnacho.data.models.SWModelList;
import com.example.thomasgarnacho.tpfinal_garnacho.data.models.SWPeople;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

import retrofit2.http.Query;



/**
 * StarWarsInterface
 */
public interface StarWarsInterface {

    String BASE_URL = "https://swapi.co/api/";
    String PAGE_1 = BASE_URL+"people/?page=1";
    String ALL_PAGE = BASE_URL+"people/";

    @GET(PAGE_1)
    Call<SWModelList> readName();

    @GET(ALL_PAGE)
    Call<SWModelList> getAllPeople(@Query("page") int page);





}
