package com.lumi.moviecata.ui.favorite.seriesfavorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.databinding.FragmentSeriesBinding
import com.lumi.moviecata.ui.detail.seriesdetail.DetailSeriesActivity
import com.lumi.moviecata.ui.series.SeriesAdapter
import com.lumi.moviecata.viewmodel.ViewModelFactory

class FavoriteSeriesFragment : Fragment() {
    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding
    private lateinit var adapter: SeriesAdapter
    private lateinit var viewModel: FavoriteSeriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentSeriesBinding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentSeriesBinding.rvSeries)

        fragmentSeriesBinding.rvSeries.setHasFixedSize(true)
        adapter = SeriesAdapter()
        adapter.notifyDataSetChanged()
        fragmentSeriesBinding.rvSeries.layoutManager = LinearLayoutManager(context)
        fragmentSeriesBinding.rvSeries.adapter = adapter

        adapter.setOnItemClickCallback(object : SeriesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SeriesEntity) {
                val moveIntent = Intent(requireActivity(), DetailSeriesActivity::class.java)
                moveIntent.putExtra(DetailSeriesActivity.EXTRA_SERIES, data.tvId)
                startActivity(moveIntent)
            }
        })

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteSeriesViewModel::class.java]

            showLoading(true)
            viewModel.getFavSeries().observe(this, { series ->
                if (series != null) {
                    adapter.submitList(series)
                    showLoading(false)
                    adapter.notifyDataSetChanged()
                }
                if (series.isNotEmpty()){
                    fragmentSeriesBinding.textNotFound.visibility = View.GONE
                }
                else{
                    fragmentSeriesBinding.textNotFound.visibility = View.VISIBLE
                }
            })

        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentSeriesBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentSeriesBinding.progressBar.visibility = View.INVISIBLE
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val seriesEntity = adapter.getSwipedData(swipedPosition)
                seriesEntity?.let { viewModel.setBookmark(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    seriesEntity?.let { viewModel.setBookmark(it) }
                }
                snackbar.show()
            }
        }
    })
}