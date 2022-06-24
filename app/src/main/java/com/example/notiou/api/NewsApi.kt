package com.example.notiou.api

import com.example.notiou.model.ReqResData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET("news?access_key=eaece24fd9202966037590387e006425&languages=en")
    suspend fun getNews(): ReqResData
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Long): ReqResData
}