package com.linguity.app.ui.spelling_quiz.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.responses.Quiz
import com.linguity.app.api.responses.ResponseQuizList
import com.linguity.app.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpellingListViewModel(private val repository: Repository) : ViewModel() {
    private val _spellingList = MutableLiveData<List<Quiz>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _toastText = MutableLiveData<String>()

    val spellingList: LiveData<List<Quiz>> = _spellingList
    val isLoading: LiveData<Boolean> = _isLoading
    val toastText: LiveData<String> = _toastText

    fun getSpellingListByLevel(level: String) {
        _isLoading.value = true
        repository.getSpellingListByLevel(level)
            .enqueue(object : Callback<ResponseQuizList> {
                override fun onResponse(
                    call: Call<ResponseQuizList>,
                    response: Response<ResponseQuizList>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        responseBody?.row.let {
                            _spellingList.value = it
                            Log.d("PronunciationViewModel", it.toString())
                        }
                        _isLoading.value = false
                    } else {
                        _toastText.value = "Failed: Server error"
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ResponseQuizList>, t: Throwable) {
                    _toastText.value = "Failed: ${t.message}"
                    _isLoading.value = false
                }
            })
    }
}