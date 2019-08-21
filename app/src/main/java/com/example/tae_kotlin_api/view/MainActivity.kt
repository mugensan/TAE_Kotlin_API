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
import com.example.tae_kotlin_api.network.MovieRequest
import com.example.tae_kotlin_api.network.RetrofitInstances
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.diplay_movie_info_page.*
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
        call.enqueue(object : Callback<MoviePopular> {
            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {

            }

            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                val res = response.body()

                //!!. == none null()
                Log.d("MAINACTIVITY", res!!.results[0].title)
//                Log.d("MAINACTIVITY", res.results[10].overview)

                //this is how we set the recyclerView
                rv_data_display.layoutManager = LinearLayoutManager(this@MainActivity)

                //connecting the rv_ with the adapter
                //this is one way
                val adapter: MovieAdapter = MovieAdapter(res, object : OnMovieClickListener {


                    override fun onMovieClickListener(result: Results) {
                        Log.d("CLICKED_ITEM", result.title)
                        var intent = Intent(this@MainActivity, DisplayMovieInfo::class.java)
                        intent.putExtra(Constants.ID, result.id)
                        startActivity(intent)



                        sendToNextActivity()
                        //if you have different fun
                        //do your intentSatart()
                        //~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        //TODO(START DETAIL ACTIVITY, PARSE THE ID, NEW RETROFIT CALL --> THEN DISPLAY DATA)

                    }


                })
                //connecting the rv_ with the adapter
                rv_data_display.adapter = adapter


            }
        })
    }

    fun sendToNextActivity() {


    }
}


//    }
//    //so if you had dif fun
//    fun startFuntion(){
//        Intent intent = Intent
//        intentPutExtra()
//        startActivity()
////    }


//}
