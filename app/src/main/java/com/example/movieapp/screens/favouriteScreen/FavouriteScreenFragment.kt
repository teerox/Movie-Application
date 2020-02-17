package com.example.movieapp.screens.favouriteScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.RecyclerFragmentBinding

class FavouriteScreenFragment :Fragment(){
    lateinit var binding: RecyclerFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favourite_fragment,container,false)
        return binding.root
    }
}