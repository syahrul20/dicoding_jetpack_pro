package com.sf.jetpack.mymov.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sf.jetpack.mymov.fragment.movie.MovieBookmarkFragment
import com.sf.jetpack.mymov.fragment.tvshow.TvShowBookmarkFragment

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */


class BookmarkPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieBookmarkFragment()
            else -> TvShowBookmarkFragment()
        }
    }
}