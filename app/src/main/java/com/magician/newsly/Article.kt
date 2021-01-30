package com.magician.newsly


import com.google.gson.annotations.SerializedName

data class Article(
    val author: Any,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)