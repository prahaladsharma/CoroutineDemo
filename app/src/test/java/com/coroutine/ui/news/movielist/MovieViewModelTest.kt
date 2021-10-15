package com.coroutine.ui.news.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coroutine.MainCoroutineRule
import com.coroutine.repository.DemoMovieRepositoryTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MovieViewModel
    private lateinit var demoMovieRepositoryTest: DemoMovieRepositoryTest

    @Before
    fun setUp(){
        viewModel = MovieViewModel(DemoMovieRepositoryTest())
        demoMovieRepositoryTest = DemoMovieRepositoryTest()
    }

    @Test
    fun `insert empty order field return error`() {
        viewModel.getAllMovies(1, "")
        viewModel.movieList.observeForever {
            assert(it.has_more).equals(false)
        }
    }

    @Test
    fun `insert zero offset field return error`() {
        viewModel.getAllMovies(0, "by-opening-date")
        viewModel.movieList.observeForever {
            assert(it.has_more).equals(false)
        }
    }
}