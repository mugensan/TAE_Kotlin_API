package com.example.tae_kotlin_api.network

import com.example.tae_kotlin_api.model.MoviePopular
import com.example.tae_kotlin_api.model.Results2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRequest {

    @GET("movie/popular")
    fun getMoviesPopular(@Query("api_key") apiKey:String):Call<MoviePopular>

    //get the title
//    @GET("movie/{id}")
//    fun GetMovieDetails(@Path("id") id:String):Call<Results2>

    @GET("movie/{movie_id}")
    fun GetMovieDetails(@Path("movie_id") movie_id: Int,
                       @Query("api_key") apiKey: String): Call<Results2>


//    //get overview
//    @GET ("movie/popular")
//    fun getMovieOverview(@Path("movieOverview") movieOverview:String):Call <MoviePopular>

}