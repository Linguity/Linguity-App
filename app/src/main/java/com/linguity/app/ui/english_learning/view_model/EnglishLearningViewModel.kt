package com.linguity.app.ui.english_learning.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.responses.Article
import com.linguity.app.api.responses.ResponseEnglishLearning
import com.linguity.app.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnglishLearningViewModel(private val repository: Repository) : ViewModel() {
    private val _articleList = MutableLiveData<List<Article>>()
    private val _toastText = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()

    val articleList: LiveData<List<Article>> = _articleList
    val toastText: LiveData<String> = _toastText
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getArticleList()
    }

    private fun getArticleList() {
        _isLoading.value = true
        repository.getArticleList()
            .enqueue(object : Callback<ResponseEnglishLearning> {
                override fun onResponse(
                    call: Call<ResponseEnglishLearning>,
                    response: Response<ResponseEnglishLearning>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        responseBody?.row?.let {
                            _articleList.value = it
                        }
                        _isLoading.value = false
                    } else {
                        _toastText.value = "Failed: ${response.message()}"
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ResponseEnglishLearning>, t: Throwable) {
                    _toastText.value = "Failed: ${t.message}"
                    _isLoading.value = false
                }
            })
    }
}