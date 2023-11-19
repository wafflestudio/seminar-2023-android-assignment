package com.example.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlideAdapter(fragment: Fragment,private val movieList:List<MyDataTypes.MovieInfo>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = ObjectFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position)
        }
        return fragment
    }
}
private const val ARG_OBJECT = "object"