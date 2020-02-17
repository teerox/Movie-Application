package com.example.movieapp.screens.movieScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieItemBinding

import com.example.movieapp.model.api.ApiService
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.model.database.Result

class MovieAdapter( val context: Context,private val clickListerner:(Result) -> Unit):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
     var movieArray = arrayListOf<Result>()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return movieArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieArray[position]

        holder.bind(movieArray[position],clickListerner)

        Glide.with(context).load(MyRetrofitBuilder.IMAGE_BASE_URL + "original" + movie.backdropPath).into(holder.binding.imageView)

    }

    class ViewHolder(var binding:MovieItemBinding ):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result, clickListerner: (Result) -> Unit){
            binding.root.setOnClickListener {
                clickListerner(item)
            }
            binding.myMovie = item
            val img =MyRetrofitBuilder.IMAGE_BASE_URL + "original" + item.posterPath
            Glide.with(binding.root.context).asBitmap().error(R.drawable.banner33_2x).load(img).into(binding.imageView)

        }
    }
}