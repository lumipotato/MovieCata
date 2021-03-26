package com.lumi.moviecata.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumi.moviecata.data.source.remote.response.SeriesItem
import com.lumi.moviecata.databinding.FragmentSeriesBinding
import com.lumi.moviecata.ui.detail.DetailSeriesActivity
import com.lumi.moviecata.ui.series.SeriesAdapter
import com.lumi.moviecata.ui.series.SeriesViewModel
import com.lumi.moviecata.viewmodel.ViewModelFactory

class FavoriteSeriesFragment : Fragment() {
    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding
    private lateinit var seriesViewModel: SeriesViewModel
    private lateinit var adapter: SeriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentSeriesBinding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSeriesBinding.rvSeries.setHasFixedSize(true)
        adapter = SeriesAdapter()
        adapter.notifyDataSetChanged()
        fragmentSeriesBinding.rvSeries.layoutManager = LinearLayoutManager(context)
        fragmentSeriesBinding.rvSeries.adapter = adapter

        adapter.setOnItemClickCallback(object : SeriesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SeriesItem) {
                val moveIntent = Intent(requireActivity(), DetailSeriesActivity::class.java)
                moveIntent.putExtra(DetailSeriesActivity.EXTRA_SERIES, data.id)
                startActivity(moveIntent)
            }
        })

        showLoading(true)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance()

            seriesViewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]
            activity?.let {
                seriesViewModel.getSeries().observe(it, { movie ->
                    if (movie != null) {
                        adapter.listSeries = movie as ArrayList<SeriesItem>
                        showLoading(false)
                    }
                })
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentSeriesBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentSeriesBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}