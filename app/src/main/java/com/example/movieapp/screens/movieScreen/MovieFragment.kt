package com.example.movieapp.screens.movieScreen


import android.annotation.SuppressLint
import android.app.Service
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieFragmentBinding
import com.example.movieapp.model.database.Result
import com.example.movieapp.screens.viewpage.FragmentMainDirections
import com.example.movieapp.screens.viewpage.MainActivity
import kotlinx.android.synthetic.main.movie_fragment.*
import kotlinx.coroutines.runBlocking


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var binding: MovieFragmentBinding
    lateinit var adapter: MovieAdapter
    lateinit var viewModel: MovieViewModel

    var arrayMovies = arrayListOf<Result>()

    internal var textlength = 0
    lateinit var movieNamesArrayList: ArrayList<Result>
    lateinit var array_sort: ArrayList<Result>


    @SuppressLint("DefaultLocale")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        recyclerView = binding.recyclerId
        setHasOptionsMenu(true)


        val movies = viewModel.allMovies
        adapter = MovieAdapter(this.context!!, viewModel,arrayMovies) { }


        movies.observeForever {
            runBlocking {
                adapter.movieArray = it as ArrayList<Result>
                adapter.movieArray = viewModel.mapFavourite(it) as ArrayList<Result>
                val list =  adapter.movieArray
                adapter = MovieAdapter(context!!,viewModel, list) {
                        Result ->
                    val action = FragmentMainDirections.actionMovieFragmentToSingleMovieFragment(Result)
                    findNavController().navigate(action)
                }

                recyclerView.adapter = adapter

                binding.editsearchText.doOnTextChanged { _: CharSequence?, _: Int, _: Int, _: Int ->
                    val search = viewModel.search(list,binding.editsearchText.text.toString())
                      adapter = MovieAdapter(context!!,viewModel, search as ArrayList<Result>) {}
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
        return binding.root
    }



}


