package com.lumi.moviecata.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.databinding.MovieItemsBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    var listMovies = ArrayList<MovieItem>()
        set(movie) {
            this.listMovies.clear()
            this.listMovies.addAll(movie)
            notifyDataSetChanged()
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
        fun bind(movie: MovieItem) {
            with(binding) {

                tvItemTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: MovieItem)
    }
}