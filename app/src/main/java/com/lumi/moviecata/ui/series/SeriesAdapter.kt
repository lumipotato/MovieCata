package com.lumi.moviecata.ui.series

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.R
import com.lumi.moviecata.data.SeriesEntity
import com.lumi.moviecata.databinding.MovieItemsBinding
import com.lumi.moviecata.ui.detail.DetailSeriesActivity
import java.util.ArrayList

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {
    private var listSeries = ArrayList<SeriesEntity>()

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
        val series = listSeries[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = listSeries.size


    class SeriesViewHolder(private val binding: MovieItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesEntity) {
            with(binding) {
                tvItemTitle.text = series.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailSeriesActivity::class.java)
                    intent.putExtra(DetailSeriesActivity.EXTRA_SERIES, series.seriesId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(series.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}