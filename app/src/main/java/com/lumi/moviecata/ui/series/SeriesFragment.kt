package com.lumi.moviecata.ui.series

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumi.moviecata.data.SeriesEntity
import com.lumi.moviecata.databinding.FragmentSeriesBinding
import com.lumi.moviecata.ui.detail.DetailSeriesActivity

class SeriesFragment : Fragment() {
    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentSeriesBinding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SeriesViewModel::class.java]
            val series = viewModel.getSeries()

            val seriesAdapter = SeriesAdapter()
            seriesAdapter.setSeries(series)

            with(fragmentSeriesBinding.rvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
            seriesAdapter.setOnItemClickCallback(object : SeriesAdapter.OnItemClickCallback {
                override fun onItemClicked(data: SeriesEntity) {
                    val moveIntent = Intent(requireActivity(), DetailSeriesActivity::class.java)
                    moveIntent.putExtra(DetailSeriesActivity.EXTRA_SERIES, data.seriesId)
                    startActivity(moveIntent)
                }
            })
        }
    }
}