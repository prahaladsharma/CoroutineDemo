package com.coroutine.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coroutine.repository.MovieRepository
import com.coroutine.ui.news.movielist.MovieViewModel

class MyViewModelFactory constructor(private val repository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            MovieViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}