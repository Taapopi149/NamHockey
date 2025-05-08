package com.example.namhockey

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsViewModel: ViewModel() {

private val _articles = MutableLiveData<List<Article>>()
val articles: LiveData<List<Article>> = _articles

init {
   fetchNewEverything()
}


    fun fetchNewEverything() {

        val newsApiClient = NewsApiClient(Constant.apiKey)

        val request = EverythingRequest.Builder().language("en").q("Hockey").build()

        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed ", throwable.localizedMessage)
                }
            }

        })
    }

}