package com.example.tae_kotlin_api.network

import com.example.tae_kotlin_api.model.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRequest {

    @GET("movie/popular")
    fun getMoviesPopular(@Query("api_key") apiKey:String):Call<MoviePopular>

}