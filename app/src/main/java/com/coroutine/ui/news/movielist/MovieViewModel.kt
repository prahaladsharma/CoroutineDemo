package com.coroutine.ui.news.movielist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coroutine.pojo.MovieReviews
import com.coroutine.repository.DefaultMovieRepository
import com.coroutine.repository.MovieRepository
import kotlinx.coroutines.*


class MovieViewModel @ViewModelInject constructor(
    private val mainRepository: DefaultMovieRepository
    ) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<MovieReviews>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies(offset: Int, order: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllMovies(offset, order)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}