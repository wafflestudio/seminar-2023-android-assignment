package com.wafflestudio.assignment4

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.assignment4.databinding.FragmentScreenSlidePageBinding

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var movies  : List<MovieData>? = emptyList()

    lateinit var firstFragment : ScreenSlidePageFragment
    fun submitList(newData : List<MovieData>){
        movies = newData
    }
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        Log.d("viewpager", "createFragment$position")
        if(movies?.isNotEmpty() == true) {
        val movie = movies?.get(position)
        return ScreenSlidePageFragment(movie)
        }
        else if(position==0){
            firstFragment = ScreenSlidePageFragment(null)
            return firstFragment
        }
        else return ScreenSlidePageFragment(null)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return itemId<5
    }
}