package com.coroutine.ui.news.movielist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coroutine.databinding.AdapterMovieBinding
import com.coroutine.pojo.Result
import com.coroutine.ui.news.detailscreen.MovieDetailsActivity

class MoviesAdapter() : RecyclerView.Adapter<MainViewHolder>() {

    private var context: Context?= null
    private var movieList = mutableListOf<Result>()

    fun addData(list: List<Result>) {
        this.movieList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        context = parent.context
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.name.text = movie.display_title
        if(movie.multimedia != null){
            Glide.with(holder.itemView.context).load(movie.multimedia.src).into(holder.binding.imageview)
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("data",  movie)
            intent.putExtras(bundle)
            context?.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {}