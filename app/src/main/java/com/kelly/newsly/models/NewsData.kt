package com.kelly.newsly.models

data class NewsData(
    val news: List<New>,
    val page: Int,
    val status: String
)