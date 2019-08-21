package com.example.tae_kotlin_api.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tae_kotlin_api.Constants
import com.example.tae_kotlin_api.R
import com.example.tae_kotlin_api.model.MoviePopular
import com.example.tae_kotlin_api.model.Results
import com.example.tae_kotlin_api.model.Results2
import com.example.tae_kotlin_api.network.MovieRequest
import com.example.tae_kotlin_api.network.RetrofitInstances
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.diplay_movie_info_page.*
import kotlinx.android.synthetic.main.row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayMovieInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diplay_movie_info_page)

        val intent = intent
        val id = intent.getIntExtra(Constants.ID,0)


//        val id = intent.

        val movieRequest = RetrofitInstances().retrofitInstances.create(MovieRequest::class.java)
        val call = movieRequest.GetMovieDetails(id,Constants.API_KEY)



        call.enqueue(object : Callback<Results2> {
            override fun onFailure(call: Call<Results2>, t: Throwable) {
            }



            override fun onResponse(call: Call<Results2>, response: Response<Results2>) {
                val res = response.body()
                Log.d("SECONDACTIVITY",res!!.title )
                tv_title2.text = res.title
                tv_overview.text = res.overview
                rt_bar.rating = res.vote_average.toFloat()
                Picasso.get().load("https://image.tmdb.org/t/p/w185" + res.poster_path).into(img_view)
                tv_vote_avg!!.text = res.vote_average.toString() + "/10"

                }




        })

    }
}


