package com.lumi.moviecata.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.R
import com.lumi.moviecata.data.MovieEntity
import com.lumi.moviecata.databinding.MovieItemsBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private var listMovies = ArrayList<MovieEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieItemsBinding = MovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieItemsBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: MovieItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {

                tvItemTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }
}