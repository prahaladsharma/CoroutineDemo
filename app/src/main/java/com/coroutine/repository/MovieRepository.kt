package com.coroutine.repository

import com.coroutine.network.ApiInterface

class MovieRepository(private val apiInterface: ApiInterface): DefaultMovieRepository {

    override suspend fun getAllMovies(offset: Int, order: String) = apiInterface.getCurrency(offset,order)

    //suspend fun getAllMovies(offset: Int, order: String) = apiInterface.getCurrency(offset,order)
}
