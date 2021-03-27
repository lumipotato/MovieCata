package com.lumi.moviecata.ui.series

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.databinding.FragmentSeriesBinding
import com.lumi.moviecata.ui.detail.DetailSeriesActivity
import com.lumi.moviecata.viewmodel.ViewModelFactory
import com.lumi.moviecata.vo.Status

class SeriesFragment : Fragment() {
    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding
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
            override fun onItemClicked(data: SeriesEntity) {
                val moveIntent = Intent(requireActivity(), DetailSeriesActivity::class.java)
                moveIntent.putExtra(DetailSeriesActivity.EXTRA_SERIES, data.tvId)
                startActivity(moveIntent)
            }
        })

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]

            viewModel.getSeries().observe(this, { series ->
                if (series != null) {
                    when (series.status) {
                        Status.LOADING -> fragmentSeriesBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentSeriesBinding.progressBar.visibility = View.GONE
                            adapter.setSeries(series.data)
                            adapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentSeriesBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

        }
    }
}