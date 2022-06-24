package com.example.notiou.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.mediastack.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}