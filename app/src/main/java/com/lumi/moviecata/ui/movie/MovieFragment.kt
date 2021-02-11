package com.lumi.moviecata.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.databinding.FragmentMovieBinding
import com.lumi.moviecata.ui.detail.DetailMovieActivity
import com.lumi.moviecata.viewmodel.MovieViewModel
import com.lumi.moviecata.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMovieBinding.rvMovie.setHasFixedSize(true)
        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()
        fragmentMovieBinding.rvMovie.layoutManager = LinearLayoutManager(context)
        fragmentMovieBinding.rvMovie.adapter = adapter

        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieItem) {
                val moveIntent = Intent(requireActivity(), DetailMovieActivity::class.java)
                moveIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.id.toString())
                startActivity(moveIntent)
            }
        })

        if (activity != null) {
            val factory = ViewModelFactory.getInstance()

            movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            activity?.let {
                movieViewModel.getMovie().observe(it, { movie ->
                    if (movie != null) {
                        adapter.listMovies = movie as ArrayList<MovieItem>
                    }
                })
            }

        }
    }
}