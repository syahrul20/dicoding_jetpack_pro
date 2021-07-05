package com.sf.jetpack.mymov.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.fragment.movie.MovieFavoriteFragment
import com.sf.jetpack.mymov.fragment.movie.MoviesFragment
import com.sf.jetpack.mymov.fragment.tvshow.TvShowFavoriteFragment
import com.sf.jetpack.mymov.fragment.tvshow.TvShowsFragment

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

private val TAB_TITLES = arrayOf(
    R.string.app_tab_movies,
    R.string.app_tab_tv_show
)

class FavoritePagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieFavoriteFragment()
            else -> TvShowFavoriteFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}