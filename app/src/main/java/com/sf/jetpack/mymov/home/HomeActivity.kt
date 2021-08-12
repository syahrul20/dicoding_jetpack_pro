package com.sf.jetpack.mymov.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.databinding.ActivityMainBinding
import com.sf.jetpack.mymov.fragment.bookmark.BookmarkFragment
import com.sf.jetpack.mymov.fragment.movie.MoviesFragment
import com.sf.jetpack.mymov.fragment.tvshow.TvShowsFragment

/*
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val manager = supportFragmentManager

    companion object {
        const val TAG_MOVIES = "tag_movies"
        const val TAG_TV_SHOW = "tag_tv_show"
        const val TAG_BOOKMARK = "tag_bookmark"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pushFragment(TAG_MOVIES, MoviesFragment())
        binding.toolbar.title = getString(R.string.title_movie)

        setUpBottomNav()
    }

    override fun onBackPressed() {
        if (binding.navView.selectedItemId != R.id.navigation_movies) {
            binding.navView.selectedItemId = R.id.navigation_movies
        } else {
            super.onBackPressed()
        }
    }

    private fun setUpBottomNav() = with(binding) {
        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_movies -> {
                    pushFragment(TAG_MOVIES, MoviesFragment())
                    binding.toolbar.title = getString(R.string.title_movie)
                }
                R.id.navigation_tv_shows -> {
                    pushFragment(TAG_TV_SHOW, TvShowsFragment())
                    binding.toolbar.title = getString(R.string.title_tv_show)
                }
                R.id.navigation_bookmark -> {
                    pushFragment(TAG_BOOKMARK, BookmarkFragment())
                    binding.toolbar.title = getString(R.string.title_bookmark)
                }
            }
            true
        }
    }

    private fun pushFragment(tag: String, fragment: Fragment) {
        val ft = manager.beginTransaction()
        if (manager.findFragmentByTag(tag) == null) {
            ft.add(R.id.fragment_container, fragment, tag)
        }
        val movies = manager.findFragmentByTag(TAG_MOVIES)
        val tvShow = manager.findFragmentByTag(TAG_TV_SHOW)
        val bookmark = manager.findFragmentByTag(TAG_BOOKMARK)

        if (movies != null) {
            ft.hide(movies)
        }
        if (tvShow != null) {
            ft.hide(tvShow)
        }
        if (bookmark != null) {
            ft.hide(bookmark)
        }

        if (tag == TAG_MOVIES) {
            if (movies != null) {
                ft.show(movies)
            }
        }

        if (tag == TAG_TV_SHOW) {
            if (tvShow != null) {
                ft.show(tvShow)
            }
        }

        if (tag == TAG_BOOKMARK) {
            if (bookmark != null) {
                ft.show(bookmark)
                binding.toolbar.title = getString(R.string.title_bookmark)
            }
        }
        ft.commitAllowingStateLoss()
    }
}