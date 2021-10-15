package com.coroutine.ui.news.movielist

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coroutine.MainCoroutineRule
import com.coroutine.pojo.Link
import com.coroutine.pojo.Multimedia
import com.coroutine.pojo.Result
import com.coroutine.ui.news.detailscreen.MovieDetailsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import java.util.*


class MoviesAdapterTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var mainViewHolder:MoviesAdapter.MainViewHolder
    //private lateinit var mockView: MockView
    //private lateinit var context: Context
    //private lateinit var view:View
    private lateinit var activity: MovieActivity

    @Before
    fun setUp(){
        moviesAdapter = MoviesAdapter()
        //context = Context()
        //mockView = MockView(context)
        //view = View(context)
        activity = MovieActivity()
    }

    @Test
    fun itemCount(){
        val link = Link("article", "https://www.nytimes.com/2021/10/07/movies/jacinta-review.html", "Read the New York Times Review of Jacinta")
        val multiMedia = Multimedia(120, "https://static01.nyt.com/images/2021/10/08/arts/jacinta1/jacinta1-mediumThreeByTwo440.jpg", "mediumThreeByTwo210",140)
        val candy = Result("Beatrice Loayza", 1, "", "", "",
        link, "", multiMedia, "2021-10-08", "2021-10-07", "This haunting documentary")

        moviesAdapter.addData(Arrays.asList(candy))
        //assert(moviesAdapter.itemCount).isEqualTo(3);
    }

    @Test
    fun testOnClickButtonClicks() {
        //mainViewHolder.itemView.setOnClickListener(activity)
        verify(activity).startActivity(Intent(activity, MovieDetailsActivity::class.java))
    }

}