package com.vodrex.mdb.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vodrex.mdb.db.entities.Movie
import com.vodrex.mdb.R
import com.vodrex.mdb.ui.MoviesAdapter
import com.vodrex.mdb.ui.viewmodel.MoviesViewModel
import com.vodrex.mdb.utils.MovieListType
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), MoviesAdapter.OnClickListener {

    private val viewModel by viewModel<MoviesViewModel>()
    private val adapter = MoviesAdapter(this)

    private val nRows = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.moviesLiveData.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            movies_swipe_refresh_layout.isRefreshing = false
            showAnim(it.isEmpty())
        })

        viewModel.genresLiveData.observe(this, Observer {
            adapter.genres = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun showAnim(show: Boolean) {
        movies_recycler_view.visibility = if (show) View.GONE else View.VISIBLE
        animation_view.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun getMovieList(type: MovieListType){
        viewModel.getMovieList(type)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movies_recycler_view.adapter = adapter
        movies_recycler_view.layoutManager = GridLayoutManager(context, nRows) as RecyclerView.LayoutManager?

        movies_swipe_refresh_layout.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    override fun onItemClicked(view: View, item: Movie) {
    }
}
