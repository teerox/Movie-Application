package com.example.movieapp.screens.main


import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.screens.favourite.favouritescreen.FavouriteScreenFragment
import com.example.movieapp.screens.movie.moviescreen.MovieFragment

class ViewPageAdaptar(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return MovieFragment()
            1 -> return FavouriteScreenFragment()
        }
        return MovieFragment()
    }
}


