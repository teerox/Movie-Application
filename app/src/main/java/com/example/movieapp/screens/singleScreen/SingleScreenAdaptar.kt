package com.example.movieapp.screens.singleScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ListLayoutImageBinding
import com.example.movieapp.model.database.Result

class SingleScreenAdaptar (var context: Context, private var movieArray:ArrayList<Result>):
    RecyclerView.Adapter<SingleScreenAdaptar.ViewHolder>() {

    class ViewHolder(private var binding: ListLayoutImageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Result){
            binding.buttomrecyclerViewmovie = item
            Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_launcher_background).load(item.posterPath).into(binding.imageSingle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{

        val view  = LayoutInflater.from(parent.context)
        val binding = ListLayoutImageBinding.inflate(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return movieArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movieArray[position])
}