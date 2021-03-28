package com.lumi.moviecata.ui.favorite.moviefavorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.databinding.FragmentMovieBinding
import com.lumi.moviecata.ui.detail.moviedetail.DetailMovieActivity
import com.lumi.moviecata.ui.movie.MovieAdapter
import com.lumi.moviecata.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
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
        adapter.notifyDataSetChanged()
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
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            showLoading(true)
            viewModel.getFavMovie().observe(this, { movies ->
                if (movies != null) {
                    adapter.setMovies(movies)
                    showLoading(false)
                    adapter.notifyDataSetChanged()
                }
                if (movies.isNotEmpty()){
                    fragmentMovieBinding.textNotFound.visibility = View.GONE
                }
                else{
                    fragmentMovieBinding.textNotFound.visibility = View.VISIBLE
                }
            })

        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}