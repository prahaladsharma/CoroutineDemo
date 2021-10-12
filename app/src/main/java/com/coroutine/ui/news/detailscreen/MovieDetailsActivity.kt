package com.coroutine.ui.news.detailscreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.coroutine.R
import com.coroutine.databinding.ActivityMovieDetailsBinding
import com.coroutine.pojo.Result

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding

    private var movie: Result?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MovieDetailsActivity, R.layout.activity_movie_details)

        movie = intent.getSerializableExtra("data") as Result?
        binding.itemDetails = movie
        binding.root

        binding.ivBack.setOnClickListener(View.OnClickListener { finish() })

    }
}