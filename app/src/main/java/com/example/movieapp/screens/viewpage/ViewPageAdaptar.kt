package com.example.movieapp.screens.viewpage


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.screens.favouriteScreen.FavouriteScreenFragment
import com.example.movieapp.screens.movieScreen.MovieFragment

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


