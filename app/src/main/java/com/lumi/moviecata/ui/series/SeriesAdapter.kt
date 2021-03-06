package com.lumi.moviecata.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.R
import com.lumi.moviecata.data.SeriesEntity
import com.lumi.moviecata.databinding.MovieItemsBinding
import java.util.ArrayList

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private var listSeries = ArrayList<SeriesEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setSeries(series: List<SeriesEntity>?) {
        if (series == null) return
        this.listSeries.clear()
        this.listSeries.addAll(series)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val movieItemsBinding = MovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(movieItemsBinding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(listSeries[position])
    }

    override fun getItemCount(): Int = listSeries.size

    inner class SeriesViewHolder(private val binding: MovieItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesEntity) {
            with(binding) {

                tvItemTitle.text = series.title
                Glide.with(itemView.context)
                        .load(series.imagePath)
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