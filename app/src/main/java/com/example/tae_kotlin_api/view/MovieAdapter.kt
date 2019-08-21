package com.example.tae_kotlin_api.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tae_kotlin_api.Constants
import com.example.tae_kotlin_api.R
import com.example.tae_kotlin_api.model.MoviePopular
import com.example.tae_kotlin_api.model.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*

//in the adapter you have a constructor
class MovieAdapter(private val moviePopular: MoviePopular, private val listener: OnMovieClickListener)
    : RecyclerView.Adapter<MovieViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //not return right away
//        val view = MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.row,parent,false))
        // returning right away 2nd way to do
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false))

    }

    override fun getItemCount(): Int {
        return moviePopular.results.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        //creating the binder, after the [position]. <this is where you get the option for ya list requests
        holder.tv_title.text = moviePopular.results[position].title
        //for the image, declare the image in the viewHolder
//        Picasso.get().load("https://image.tmdb.org/t/p/w185" + moviePopular.results[position].poster_path).into(holder.image)
        Picasso.get().load(Constants.IMG + moviePopular.results[position].poster_path).into(holder.image);
        //holder for the onClickListener
        holder.bind(moviePopular.results[position],listener)
    }


}
//we are extending the viewHolder, it can be outside or inside the class
//outside
class MovieViewHolder (view: View): RecyclerView.ViewHolder(view),View.OnClickListener{




    override fun onClick(p0: View?) {



    }

    fun bind(results: Results, listener: OnMovieClickListener) {
        itemView.setOnClickListener{
            listener.onMovieClickListener(results)
        }

    }

    //this is where you load your data to the TextView in XML
    val tv_title =view.tv_title
    val tv_title2 = view.tv_title
    val image: ImageView = view.iv_imageView
    val cardV = view.cv_displayCard
    val id = view.id



}

//either create your on clickListenen interface here or create a new interface in view model
interface OnMovieClickListener{
    fun onMovieClickListener(result: Results)
    //next step go to the top in the main class and add private val listener: onMovieClickListener to the parameters
    //then got to on bind
    //then alt + enter to create member
    //go to fun bind (itemView)
    //then go to main where your (res) is and pass the object (res, object...)

}
