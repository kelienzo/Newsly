package com.kelly.newsly.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NewsApiProvider {
    private val retrofit = Retrofit.Builder().baseUrl("https://api.currentsapi.services/v1/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val newsApiRequest = retrofit.create(NewsApiRequest::class.java)
}