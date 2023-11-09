package com.wafflestudio.assignment4

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.assignment4.databinding.FragmentPageBinding

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    private lateinit var binding : FragmentPageBinding

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = PageFragment()
        return fragment
    }
}