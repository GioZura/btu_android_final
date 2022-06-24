package com.example.notiou.repository

import com.example.notiou.api.RetrofitInstance
import com.example.notiou.model.News
import com.example.notiou.model.ReqResData

class NewsRepository {
    suspend fun getNews(): ReqResData {
        return RetrofitInstance.api.getNews()
    }
}