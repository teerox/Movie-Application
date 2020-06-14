package com.example.movieapp.screens.favourite.favouritescreen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ListLayoutFavouriteBinding
import com.example.movieapp.api.MyRetrofitBuilder
import com.example.movieapp.model.Result
import com.example.movieapp.utils.Utils

class FavouriteMovieAdapter(val context: Context, private val clickListerner: (result: Result) -> Unit): RecyclerView.Adapter<FavouriteMovieAdapter.ViewHolder>()
{
    var movieArray = arrayListOf<Result>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
        val binding = ListLayoutFavouriteBinding.inflate(view)
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return movieArray.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movieArray[position], clickListerner)



    class ViewHolder(var binding: ListLayoutFavouriteBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Result, clickListerner: (result: Result) -> Unit) {
            val rating = Utils().rating(item.voteAverage)
            binding.favouriteItemRating.rating = rating
            binding.textView3.text = "Rating: $rating/5"
            binding.root.setOnClickListener {
                clickListerner(item)

            }

            binding.ImageView2.setImageResource(R.drawable.ic_favorite_black_24dp)
            binding.myfavMovie = item
            val img =MyRetrofitBuilder.IMAGE_BASE_URL + "original" + item.posterPath
            Glide.with(binding.root.context).asBitmap().error(R.drawable.banner33_2x).load(img).into(binding.imageView)
        }
    }
}