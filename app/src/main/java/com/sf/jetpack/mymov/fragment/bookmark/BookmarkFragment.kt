package com.sf.jetpack.mymov.fragment.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.adapter.BookmarkPagerAdapter
import com.sf.jetpack.mymov.databinding.FragmentBookmarkBinding

/*
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null

    private val binding get() = _binding!!

    private val listTabsTitle = arrayOf(
        R.string.app_tab_movies,
        R.string.app_tab_tv_show
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookmarkPagerAdapter = BookmarkPagerAdapter(requireActivity())
        binding.viewpager.apply {
            adapter = bookmarkPagerAdapter
            TabLayoutMediator(binding.tabs, binding.viewpager) { tabs, position ->
                tabs.text = getString(listTabsTitle[position])
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}