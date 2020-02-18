package com.example.movieapp.screens.viewpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain:Fragment(){
    private lateinit var viewPageAdaptar: ViewPageAdaptar
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPageAdaptar = ViewPageAdaptar(activity!!)
        viewPager22.adapter = viewPageAdaptar
        //tabs.setupWithViewPager(viewPager22)
        TabLayoutMediator(tabs,viewPager22,TabLayoutMediator.TabConfigurationStrategy {tab, position ->
            run {
                when(position){
                    0 -> tab.text = "Movies"
                    1 -> tab.text = "Favourites"
                }
            }
        }).attach()


    }
}