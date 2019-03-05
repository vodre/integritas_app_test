package com.vodrex.mdb.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vodrex.mdb.R
import com.vodrex.mdb.ui.fragments.MoviesFragment
import com.vodrex.mdb.utils.MovieListType
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Gravity
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private var fragment : MoviesFragment = MoviesFragment()
    private var listType : MutableLiveData<MovieListType> = MutableLiveData()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_popular -> {
                listType.postValue(MovieListType.POPULAR)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_top_rated -> {
                listType.postValue(MovieListType.TOP_RATED)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_megaphono -> {
                listType.postValue(MovieListType.UPCOMING)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.app_name)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()

        listType.observe(this, Observer { type ->
            toolbar.title = "MDB - ${type.name}"
            getMovieList(type)
        })

        addFiltersButton(toolbar)
    }

    private fun addFiltersButton(toolbar: androidx.appcompat.widget.Toolbar?) {
        val lp = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        lp.gravity = Gravity.END
    }



    private fun getMovieList(type: MovieListType = listType.value?: MovieListType.POPULAR) {
        fragment.getMovieList(type)
    }
}
