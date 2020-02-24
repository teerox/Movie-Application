package com.example.movieapp.utils

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class UtilsTest{
    @Mock
    lateinit var utils: Utils

    @Before
    fun setup(){
        utils = Utils()
    }

    @Test
    fun rating_should_return_a_float(){
        val num:Double = 8.0
        val res = utils.rating(num)
        assertEquals(4.0F, res)
    }
}