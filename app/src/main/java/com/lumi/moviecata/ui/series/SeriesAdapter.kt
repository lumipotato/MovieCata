package com.lumi.moviecata.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.databinding.MovieItemsBinding
import java.util.*

class SeriesAdapter : PagedListAdapter<SeriesEntity, SeriesAdapter.SeriesViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SeriesEntity>() {
            override fun areItemsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val movieItemsBinding = MovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(movieItemsBinding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = getItem(position)
        if (series != null) {
            holder.bind(series)
        }
    }

    fun getSwipedData(swipedPosition: Int): SeriesEntity? = getItem(swipedPosition)

    inner class SeriesViewHolder(private val binding: MovieItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesEntity) {
            with(binding) {

                tvItemTitle.text = series.title
                Glide.with(itemView.context)
                        .load("${BuildConfig.IMG_URL}${series.imagePath}")
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(imgPoster)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(series) }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: SeriesEntity)
    }
}