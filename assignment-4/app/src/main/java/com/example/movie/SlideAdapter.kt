package com.example.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlideAdapter(fragment: Fragment,private val movieList:List<MyDataTypes.MovieInfo>,private val token:String) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("see in",movieList.toString())
        // Return a NEW fragment instance in createFragment(int)
        val fragment = ObjectFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putParcelable("object",movieList[position])
            putString("token",token)
        }
        return fragment
    }
}
private const val ARG_OBJECT = "object"