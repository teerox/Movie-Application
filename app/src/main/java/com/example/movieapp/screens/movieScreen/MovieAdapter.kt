package com.example.movieapp.screens.movieScreen

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.Result
import com.google.android.material.snackbar.Snackbar

class MovieAdapter(
    val context: Context,
    var viewModel: MovieViewModel,
    private val clickListerner: (result: Result) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var movieArray = arrayListOf<Result>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(view)

        return ViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {

        return movieArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movieArray[position], clickListerner)

    class ViewHolder(var binding: MovieItemBinding, var viewModel: MovieViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result, clickListerner: (result: Result) -> Unit) {
            binding.root.setOnClickListener {
                clickListerner(item)

            }
            val movie =
                Movie(item.title, item.voteAverage.toString(), item.releaseDate, item.posterPath)
            if (!item.isFavourite) {
                binding.ImageView2.visibility = View.VISIBLE
                binding.ImageView3.visibility = View.GONE
            } else {
                binding.ImageView2.visibility = View.GONE
                binding.ImageView3.visibility = View.VISIBLE
            }
            movie.uid = item.id
            binding.ImageView2.setOnClickListener {
                if (!item.isFavourite) {
                    item.isFavourite = true
                    binding.ImageView2.setImageResource(R.drawable.ic_favorite_black_24dp)
                    viewModel.insert(movie)
                    Snackbar.make(it, "Movie Added to Favourites", Snackbar.LENGTH_LONG).show()
                } else {
                    item.isFavourite = false
                    binding.ImageView2.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    viewModel.delete(movie.uid)
                    Snackbar.make(it, "Movie Removed from Favourites", Snackbar.LENGTH_LONG).show()
                }
                Log.e("Moviess", movie.toString())
            }
            binding.ImageView3.setOnClickListener {
                if (item.isFavourite) {
                    item.isFavourite = false
                    viewModel.delete(movie.uid)
                    binding.ImageView3.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    Snackbar.make(it, "Movie Removed from Favourites", Snackbar.LENGTH_LONG).show()
                } else {
                    item.isFavourite = true
                    viewModel.insert(movie)
                    binding.ImageView2.setImageResource(R.drawable.ic_favorite_black_24dp)
                    Snackbar.make(it, "Movie Removed from Favourites", Snackbar.LENGTH_LONG).show()
                }

            }

//            val checked = binding.ImageView2.isChecked
//            if(checked){
//                val movie = Movie(item.title,item.voteAverage.toString(),item.releaseDate,item.posterPath)
//                movie.uid = item.id
//                //viewModel.insert(movie)
////                binding.ImageView2.isChecked = true
//                Log.e("Moviess",movie.toString())
//               // Toast.makeText(, "Movie Added to Favourites", Toast.LENGTH_LONG).show()
//            }
            //else{
            // viewModel.delete(movie.uid)
            // Log.e("Moviess222",movie.toString())
            // Snackbar.make(itemView, "Movie Removed Favourites", Snackbar.LENGTH_LONG).show()
            //}


            binding.myMovie = item
            val img = MyRetrofitBuilder.IMAGE_BASE_URL + "original" + item.posterPath
            Glide.with(binding.root.context).asBitmap().error(R.drawable.banner33_2x).load(img)
                .into(binding.imageView)
        }
    }

}