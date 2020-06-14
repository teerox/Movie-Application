package com.example.movieapp.screens.movieScreen


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MovieApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieFragmentBinding
import com.example.movieapp.model.Result
import com.example.movieapp.screens.viewpage.FragmentMainDirections
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var binding: MovieFragmentBinding

    var arrayMovies = arrayListOf<Result>()
    private val movieViewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory((requireContext().applicationContext as MovieApplication).movieRepoInterface)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false)


        recyclerView = binding.recyclerId

        try {
            setHasOptionsMenu(true)

            adapter = MovieAdapter(this.context!!, movieViewModel, arrayMovies) { }
            movieViewModel.items.observeForever {
                val movies = it
                runBlocking {
                    adapter.movieArray = movies as ArrayList<Result>
                    adapter.movieArray = movieViewModel.mapFavourite(movies) as ArrayList<Result>

                    val list = adapter.movieArray
                    adapter = MovieAdapter(requireContext(), movieViewModel, list) { Result ->
                        val action =
                           FragmentMainDirections.actionMovieFragmentToSingleScreenActivity(Result)
                          // FragmentMainDirections.actionMovieFragmentToSingleMovieFragment(Result)
                        findNavController().navigate(action)
                    }
                    recyclerView.adapter = adapter
                    binding.editsearchText.doOnTextChanged { _: CharSequence?, _: Int, _: Int, _: Int ->
                        val search = movieViewModel.search(list, binding.editsearchText.text.toString())
                        adapter =
                            MovieAdapter(requireContext(), movieViewModel, search as ArrayList<Result>) {}
                        recyclerView.adapter = adapter
                        return@doOnTextChanged
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }

            binding.searchButton.setOnClickListener {
                if(binding.editsearchText.visibility == View.VISIBLE){
                    binding.editsearchText.visibility = View.GONE
                }else{
                    binding.editsearchText.visibility = View.VISIBLE
                }
            }
            recyclerView.adapter = adapter
        } catch (e: Exception) {
            Log.e("error", e.message!!)
            Toast.makeText(requireContext(),"No Internet Connection",Toast.LENGTH_LONG).show()
        }


        return binding.root
    }
}
