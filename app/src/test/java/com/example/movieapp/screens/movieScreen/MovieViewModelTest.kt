package com.example.movieapp.screens.movieScreen

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieapp.model.database.Result
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest= Config.NONE)
class MovieViewModelTest{

    @Mock
    lateinit var viewModel: MovieViewModel
    lateinit var application: Application

    @Before
    fun setUp(){

        viewModel = MovieViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun search_should_return_a_List(){
        val array = listOf(Result("",1,"","Tony",3.5,"","",true))
       val result =  viewModel.search(array,"o")
        assertThat(result, Matchers.equalTo(array))
    }
}