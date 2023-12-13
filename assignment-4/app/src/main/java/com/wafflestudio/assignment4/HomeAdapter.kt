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

class HomeAdapter(activity: Fragment, data:List<MovieDetailDto>) : FragmentStateAdapter(activity) {

    var dataSet = data

    fun setItems(newItems: List<MovieDetailDto>) {
        dataSet = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  minOf(dataSet.size, 5)
    }

    override fun createFragment(position: Int): Fragment {
        val movie = dataSet[position]

        return MovieDetailFragment.newInstance(movie)
    }


}