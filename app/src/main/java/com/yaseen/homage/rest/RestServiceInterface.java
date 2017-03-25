package com.yaseen.homage.rest;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by macbookair on 3/25/17.
 */

public interface RestServiceInterface {
    @GET("r2rzz/")
    Call<String> getVisits();
}
