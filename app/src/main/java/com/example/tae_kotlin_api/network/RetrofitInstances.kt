package com.example.tae_kotlin_api.network

import com.example.tae_kotlin_api.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstances {

    //step 2

        //Setting the getter here
        val retrofitInstances:Retrofit
            get(){
        //setting interceptors
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY




        //step 1
        //creating the builder, this the retrofit call

        //because we are only returning the builder we can use this method
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()





    }
}