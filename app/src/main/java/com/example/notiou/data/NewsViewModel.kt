package com.example.notiou.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notiou.model.News
import com.example.notiou.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    val myResponse: MutableLiveData<List<News>> = MutableLiveData()
    val repository: NewsRepository = NewsRepository()

    fun getNews() {
        viewModelScope.launch {
            val response = repository.getNews()
            myResponse.value = response.data
        }
    }
}