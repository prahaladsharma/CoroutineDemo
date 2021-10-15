package com.coroutine.ui.news.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.coroutine.R
import com.coroutine.databinding.ActivityMoviesBinding
import com.coroutine.network.ApiInterface
import com.coroutine.network.MyViewModelFactory
import com.coroutine.pojo.Result
import com.coroutine.repository.MovieRepository
import com.coroutine.util.SystemUtils

class MovieActivity : AppCompatActivity() {

    lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MoviesAdapter
    lateinit var binding: ActivityMoviesBinding
    var offset: Int = 1
    var order: String = "by-opening-date"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MovieActivity, R.layout.activity_movies)
        binding.activity = this

        setUpView()
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        if (SystemUtils.isNetworkConnected(this)) {
            binding.progressBar.visibility = View.VISIBLE
            if(offset != null && (order.length>0 && order.isNotEmpty())) {
                viewModel.getAllMovies(offset, order)
            }
        } else{
            Toast.makeText(this, "Please check internet connection!!!!", Toast.LENGTH_LONG).show()
        }

        viewModel.movieList.observe(this, {
            binding.progressBar.visibility = View.GONE
            it.results?.let { users -> renderList(users) }
            binding.recyclerView.visibility = View.VISIBLE
        })
    }

    private fun setupViewModel() {
        val apiInterface = ApiInterface.getInstance()
        val mainRepository = MovieRepository(apiInterface)
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MovieViewModel::class.java)
    }

    private fun setUpView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MoviesAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun renderList(users: List<Result>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}