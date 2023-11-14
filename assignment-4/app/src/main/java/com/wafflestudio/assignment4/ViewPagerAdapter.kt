package com.wafflestudio.assignment4

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.FragmentPageBinding

class ViewPagerAdapter(fragment: Fragment, private val movieList: List<DataMovie>) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        if(movieList.size>1) {
            val fragment = PageFragment()
            fragment.arguments = Bundle().apply {
                putParcelable("data_movie", movieList[position])
            }
            //Log.d("making fragment", "called")
            return fragment
        }
        //Log.d("making fragment", "empty")
        return PageFragment()
    }
}