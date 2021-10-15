package com.coroutine.repository

import com.coroutine.pojo.MovieReviews
import junit.framework.TestCase
import retrofit2.Response

class DemoMovieRepositoryTest : DefaultMovieRepository {
    override suspend fun getAllMovies(offset: Int, order: String): Response<MovieReviews> {
        TODO("Not yet implemented")
    }

}