package com.wafflestudio.assignment4

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewAdapter(fragment: Fragment, private val movies: List<MovieData>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = movies.size
    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }

    fun createFragmentForPosition(position: Int): Fragment {
        return if (movies.isNotEmpty()) {
            createMovieFragment(movies[position])
        } else {
            PageFragment()
        }
    }

    private fun createMovieFragment(movie: MovieData): Fragment {
        val movieFragment = PageFragment()
        movieFragment.arguments = Bundle().apply {
            putParcelable("data_movie", movie)
        }
        return movieFragment
    }
}
