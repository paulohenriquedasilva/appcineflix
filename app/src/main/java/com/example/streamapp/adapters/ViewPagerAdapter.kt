package com.example.streamapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.streamapp.fragments.FilmesFragment
import com.example.streamapp.fragments.HomeFragment
import com.example.streamapp.fragments.SeriesFragment

class ViewPagerAdapter(
    private val abas: List<String>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return abas.size
    }

    override fun createFragment(position: Int): Fragment {
        when( position ){
            1 -> return FilmesFragment()
            2 -> return SeriesFragment()
        }
        return HomeFragment()
    }


}