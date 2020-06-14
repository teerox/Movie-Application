package com.example.movieapp.screens.movie.moviescreen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.api.MyRetrofitBuilder
import com.example.movieapp.model.Result
import com.example.movieapp.screens.movie.movieviewmodel.MovieViewModel
import com.example.movieapp.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MovieAdapter(
    val context: Context,
    var viewModel: MovieViewModel, var movieArray: ArrayList<Result>,
    private val clickListerner: (result: Result) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(view)

        return ViewHolder(
            binding,
            viewModel
        )
    }

    override fun getItemCount(): Int {
        return movieArray.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movieArray[position], clickListerner)

    class ViewHolder(var binding: MovieItemBinding, var viewModel: MovieViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Result, clickListerner: (result: Result) -> Unit) {
            val rating =Utils().rating(item.voteAverage)
            binding.moveItemRating.rating = rating
            binding.textView3.text = "Rating: $rating/5"
            binding.root.setOnClickListener {
                clickListerner(item)
            }
            val movie =
                Result(
                    item.posterPath,
                    item.backdropPath,
                    item.title,
                    item.voteAverage,
                    item.overview,
                    item.releaseDate,
                    item.isFavourite,
                    item.uid
                )
            if (!item.isFavourite) {
                binding.ImageView2.visibility = View.VISIBLE
                binding.ImageView3.visibility = View.GONE
            } else {
                binding.ImageView2.visibility = View.GONE
                binding.ImageView3.visibility = View.VISIBLE
            }
            movie.uid = item.uid

            binding.ImageView2.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main){
                    viewModel.like(item,it as ImageView)
                }
            }

            binding.ImageView3.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    viewModel.unLike(item,it as ImageView)
                }
            }


            binding.myMovie = item
            val img = MyRetrofitBuilder.IMAGE_BASE_URL + "original" + item.posterPath
            Glide.with(binding.root.context).asBitmap().error(R.drawable.banner33_2x).load(img)
                .into(binding.imageView)
        }
    }


}