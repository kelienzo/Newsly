package com.kelly.newsly.api

import com.kelly.newsly.models.NewsData
import retrofit2.http.GET

interface NewsApiRequest {

    @GET("latest-news?language=en&apiKey=SDEQChvBXzVjVO0KoNblyRiVQLhjJN00AWqn9uEGwgWgSXLI")
    suspend fun getAllNews(): NewsData
}