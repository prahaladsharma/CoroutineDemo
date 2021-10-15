package com.coroutine.repository

import com.coroutine.pojo.MovieReviews
import retrofit2.Response

interface DefaultMovieRepository {
    suspend fun getAllMovies(offset: Int, order: String): Response<MovieReviews>
}