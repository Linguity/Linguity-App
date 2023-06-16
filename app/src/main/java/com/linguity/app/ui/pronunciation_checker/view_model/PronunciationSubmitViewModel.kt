package com.linguity.app.ui.pronunciation_checker.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.responses.ResponsePronunciationPredict
import com.linguity.app.data.Repository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PronunciationSubmitViewModel(private val repository: Repository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isSuccess = MutableLiveData<Boolean>()
    private val _status = MutableLiveData<String>()

    val isLoading: LiveData<Boolean> = _isLoading
    val isSuccess: LiveData<Boolean> = _isSuccess
    val status: LiveData<String> = _status

    fun postAnswer(id: Int, fileAudio: File) {
        _isLoading.value = true
        val requestAudioFile = fileAudio.asRequestBody("audio/wav".toMediaType())
        val audioMultipart = MultipartBody.Part.createFormData(
            "audio",
            fileAudio.name,
            requestAudioFile
        )
        repository.checkPronunciation(id, audioMultipart)
            .enqueue(object : Callback<ResponsePronunciationPredict> {
                override fun onResponse(
                    call: Call<ResponsePronunciationPredict>,
                    response: Response<ResponsePronunciationPredict>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        _isSuccess.value = response.isSuccessful
                        responseBody?.status.let {
                            _status.value = it
                        }
                        _isLoading.value = false
                    } else {
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ResponsePronunciationPredict>, t: Throwable) {
                    _isLoading.value = false
                }
            })
    }
}