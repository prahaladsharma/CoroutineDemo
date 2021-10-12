package com.coroutine.repository

import com.coroutine.network.ApiInterface

class MovieRepository(private val apiInterface: ApiInterface) {
    suspend fun getAllMovies(offset: Int, order: String) = apiInterface.getCurrency(offset,order)
}
