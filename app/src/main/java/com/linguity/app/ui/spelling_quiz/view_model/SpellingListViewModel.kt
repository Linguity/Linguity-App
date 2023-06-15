package com.linguity.app.ui.spelling_quiz.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.responses.Quiz
import com.linguity.app.api.responses.ResponseSpelling
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
        repository.getSpellingListByLevel(level)
            .enqueue(object : Callback<ResponseSpelling> {
                override fun onResponse(
                    call: Call<ResponseSpelling>,
                    response: Response<ResponseSpelling>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        responseBody?.row.let {
                            _spellingList.value = it
                        }
                        _isLoading.value = false
                    } else {
                        _toastText.value = "Failed: Server error"
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ResponseSpelling>, t: Throwable) {
                    _toastText.value = "Failed: ${t.message}"
                    _isLoading.value = false
                }
            })
    }
}