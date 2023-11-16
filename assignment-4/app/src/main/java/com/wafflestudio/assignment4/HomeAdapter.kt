package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto

class HomeAdapter(activity: MovieDetailFragment, data:List<MovieDetailDto>) : FragmentStateAdapter(activity) {

    var dataSet = data

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = MovieDetailFragment()
        val movie = dataSet[position]

        val bundle = Bundle()
        fragment.arguments = bundle

        return MovieDetailFragment.newInstance(movie)
    }


}