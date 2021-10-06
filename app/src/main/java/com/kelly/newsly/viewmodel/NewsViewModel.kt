package com.kelly.newsly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelly.newsly.api.NewsApiProvider
import com.kelly.newsly.models.New
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel() : ViewModel() {
    val newsLiveData = MutableLiveData<List<New>>()

    fun getAllNews() {
        try {
            viewModelScope.launch {
                newsLiveData.postValue(NewsApiProvider.newsApiRequest.getAllNews().news)
            }
        } catch (e: Exception) {

        }
    }
}