package com.sf.jetpack.mymov.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.fragment.movie.MoviesFragment
import com.sf.jetpack.mymov.fragment.tvshow.TvShowsFragment

private val TAB_TITLES = arrayOf(
        R.string.app_tab_movies,
        R.string.app_tab_tv_show
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment.newInstance()
            1 -> TvShowsFragment.newInstance()
            else -> MoviesFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}