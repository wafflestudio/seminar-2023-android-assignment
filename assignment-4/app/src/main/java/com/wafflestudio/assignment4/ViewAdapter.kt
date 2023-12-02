package com.wafflestudio.assignment4

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding

class ViewAdapter(
    private val movieInfoList: List<MovieInfo>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return movieInfoList.size.also { size ->
            Log.d("ViewAdapter", "Item count: $size")
        }
    }

    override fun createFragment(position: Int): Fragment {
        val movieFragment = MovieFragment(movieInfoList[position])
        Log.d("ViewAdapter", "Creating fragment for movie: ${movieInfoList[position].title}")
        return movieFragment
    }
}
