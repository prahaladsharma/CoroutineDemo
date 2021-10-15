package com.coroutine.ui.news.movielist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.coroutine.R
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class MovieActivityTest(java: Class<MovieActivity>) {

    @Rule
    @JvmField
    public var activityRule = MovieActivityTest(MovieActivity::class.java)
    public var activity = MovieActivity()

    @Before
    fun setUp() {
    }

    @Test
    fun recyclerViewItemClick() {
       Espresso.onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }

    @Test
    fun scrollDownTotalItem(){
        var recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.recyclerView)
        var itemCount = recyclerView.adapter?.itemCount
        if(itemCount != null){
            Espresso.onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount.minus(1)))
        }
    }
}