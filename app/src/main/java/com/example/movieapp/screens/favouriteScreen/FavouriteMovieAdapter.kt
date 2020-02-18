package com.example.movieapp.screens.favouriteScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ListLayoutFavouriteBinding
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.model.database.Movie

class FavouriteMovieAdapter(val context: Context):


    RecyclerView.Adapter<FavouriteMovieAdapter.ViewHolder>() {
    var movieArray = arrayListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
        val binding = ListLayoutFavouriteBinding.inflate(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movieArray[position])

    class ViewHolder(var binding: ListLayoutFavouriteBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie){

            binding.ImageView2.setImageResource(R.drawable.ic_favorite_black_24dp)
            binding.myfavMovie = item
            val img =MyRetrofitBuilder.IMAGE_BASE_URL + "original" + item.movieImage
            Glide.with(binding.root.context).asBitmap().error(R.drawable.banner33_2x).load(img).into(binding.imageView)
        }
    }
}