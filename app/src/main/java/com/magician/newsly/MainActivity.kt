package com.magician.newsly

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = NewsAdapter(this@MainActivity, this)
        news_recycler_list.adapter = mAdapter
        //news_recycler_list.layoutManager = LinearLayoutManager(this@MainActivity)
        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
            }

        })
        news_recycler_list.layoutManager = layoutManager
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("NewsSuccess", news.toString())

                    mAdapter.updateNews(news.articles)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("NewsFailure", "Error in fetching News", t)
            }
        })
    }

    override fun onItemClicked(article: Article) {
        Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()
    }
}