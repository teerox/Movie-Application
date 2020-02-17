package com.example.movieapp.screens.favouriteScreen

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ListLayoutFavouriteBinding
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.Result
import com.example.movieapp.screens.movieScreen.MovieAdapter

class FavouriteMovieAdapter(val context: Context, private val clickListerner:(Movie) -> Unit):
    RecyclerView.Adapter<FavouriteMovieAdapter.ViewHolder>() {

    class ViewHolder(var binding: ListLayoutFavouriteBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result, clickListerner: (Result) -> Unit){
            binding.root.setOnClickListener {
                clickListerner(item)
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}