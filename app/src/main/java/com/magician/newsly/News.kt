package com.magician.newsly


import com.google.gson.annotations.SerializedName

data class News(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)