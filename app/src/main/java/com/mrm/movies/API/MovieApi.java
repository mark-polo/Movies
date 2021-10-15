package com.mrm.movies.API;

import com.mrm.movies.Model.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("/")
    Call<Model> getMovies(@Query("t") String name);
}