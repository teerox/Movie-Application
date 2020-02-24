package com.example.movieapp.screens.movieScreen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.Result
import com.example.movieapp.utils.Utils
import com.google.android.material.snackbar.Snackbar


class MovieAdapter(
    val context: Context,
    var viewModel: MovieViewModel, var movieArray: ArrayList<Result>,
    private val clickListerner: (result: Result) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(view)

        return ViewHolder(binding, viewModel)
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
                Movie(item.title, item.voteAverage.toString(), item.releaseDate, item.posterPath,item.overview,item.backdropPath,item.isFavourite)
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

            binding.myMovie = item
            val img = MyRetrofitBuilder.IMAGE_BASE_URL + "original" + item.posterPath
            Glide.with(binding.root.context).asBitmap().error(R.drawable.banner33_2x).load(img)
                .into(binding.imageView)
        }
    }





//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val searchString: String = constraint.toString()
//
//                if (searchString.isEmpty()) {
//                 filteredUserList = movieArray
//
//                } else {
//                    val tempFilteredList= ArrayList<Result>()
//                    for (user in movieArray) { // search for user title
//                        if (user.title.toLowerCase().contains(searchString.toLowerCase())) {
//                            tempFilteredList.add(user)
//                        }
//                        movieArray = tempFilteredList
//                    }
//
//                    filteredUserList = tempFilteredList
//                    //movieArray.clear()
//                    movieArray.addAll(movieArray)
//                   //
//                   // movieArray.addAll(filteredUserList!!)
////                    filteredUserList = movieArray
//                    Log.e("adaaptter222", filteredUserList.toString())
//                }
//                val filterResults = FilterResults()
//                filterResults.values = filteredUserList
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                if (results != null) {
//                    filteredUserList = results.values as ArrayList<Result>?
//                    // movieArray.clear()
//                    // movieArray.addAll(movieArray2)
//                    notifyDataSetChanged()
//                    // Log.e("adaaptter",filteredUserList .toString())
//                }
//
//            }
//
//        }
//
//    }

}