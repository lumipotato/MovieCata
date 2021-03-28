package com.lumi.moviecata.ui.favorite.moviefavorite

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
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.databinding.FragmentMovieBinding
import com.lumi.moviecata.ui.detail.moviedetail.DetailMovieActivity
import com.lumi.moviecata.ui.movie.MovieAdapter
import com.lumi.moviecata.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: FavoriteMovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentMovieBinding.rvMovie)

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
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            showLoading(true)
            viewModel.getFavMovie().observe(this, { movies ->
                if (movies != null) {
                    adapter.submitList(movies)
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

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = adapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setBookmark(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    movieEntity?.let { viewModel.setBookmark(it) }
                }
                snackbar.show()
            }
        }
    })
}