package com.example.movieapp.utils

class Utils {

    fun rating(num:Double):Float{
        var rate = num/2
        rate = String.format("%.1f", rate).toDouble()
        return rate.toFloat()
    }
}