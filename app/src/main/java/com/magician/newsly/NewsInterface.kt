package com.magician.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//http://newsapi.org/v2/top-headlines?country=in&apiKey=97f1d659d2dc48909d143b3b9421353e
//http://newsapi.org/v2/everything?q=tesla&from=2020-12-30&sortBy=publishedAt&apiKey=97f1d659d2dc48909d143b3b9421353e

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "97f1d659d2dc48909d143b3b9421353e"

interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int
    ): Call<News>
    //http://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
}

object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}