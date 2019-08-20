package com.example.tae_kotlin_api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tae_kotlin_api.Constants
import com.example.tae_kotlin_api.R
import com.example.tae_kotlin_api.model.MoviePopular
import com.example.tae_kotlin_api.network.MovieRequest
import com.example.tae_kotlin_api.network.RetrofitInstances
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //calling the retrofitInstance (interface), to be used like an Intent of some sort
        val MovieRequest = RetrofitInstances().retrofitInstances.create(MovieRequest::class.java)

        //need to create the call
        val call = MovieRequest.getMoviesPopular(Constants.API_KEY)

        //creating the enqueue
        call.enqueue(object : Callback<MoviePopular>{
            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {

            }

            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                val res = response.body()

                //!!. == none null()
                Log.d("MAINACTIVITY",res!!.results[0].title)
//                Log.d("MAINACTIVITY", res.results[10].overview)

                //this is how we set the recyclerView
                rv_data_display.layoutManager = LinearLayoutManager(this@MainActivity)

                //connecting the rv_ with the adapter
                //this is one way
                val adapter: MovieAdapter = MovieAdapter(res)
                //connecting the rv_ with the adapter
                rv_data_display.adapter = adapter


            }
        })
    }
}
