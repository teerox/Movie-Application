package com.example.movieapp.di.modules

import android.content.Context
import com.example.movieapp.MyApplication
import com.example.movieapp.api.ApiService
import com.example.movieapp.utils.Utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val application: MyApplication) {

    @Provides
    fun context(): Context {
        return application
    }


    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    @Singleton
    @Provides
    internal fun provideBalanceService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}