package com.lumi.moviecata.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.databinding.FragmentMovieBinding
import com.lumi.moviecata.ui.detail.moviedetail.DetailMovieActivity
import com.lumi.moviecata.viewmodel.ViewModelFactory
import com.lumi.moviecata.vo.Status

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMovieBinding.rvMovie.setHasFixedSize(true)
        adapter = MovieAdapter()
        fragmentMovieBinding.rvMovie.layoutManager = LinearLayoutManager(context)
        fragmentMovieBinding.rvMovie.adapter = adapter

        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieEntity) {
                val moveIntent = Intent(requireActivity(), DetailMovieActivity::class.java)
                moveIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.movieId)
                startActivity(moveIntent)
            }
        })

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            viewModel.getMovie().observe(this, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            adapter.submitList(movies.data)
                            adapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

        }
    }
}