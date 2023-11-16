package com.wafflestudio.assignment4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding

class ViewPagerAdapter(
    private val list: List<MovieInfo>,
    fragment: Fragment
):FragmentStateAdapter(fragment){
    override fun getItemCount(): Int{
        Log.d("aaaa",list.size.toString())
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = MovieFragment(list[position])
        Log.d("aaaa","movie")
        return fragment
    }

}

/*
class ViewPagerAdapter(
    private val list: List<MovieInfo>,
    private val context: Context
):RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val viewholder=PagerViewHolder(FragmentMovieBinding.inflate(LayoutInflater.from(parent.context)))
        viewholder.setIsRecyclable(false)
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.PagerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =list.size

    override fun getItemViewType(position: Int): Int =position
    inner class PagerViewHolder(private val binding:FragmentMovieBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data:MovieInfo){
                    //val imageurl = "https://image.tmdb.org/t/p/original"+data.result[0].poster_path
                    this.binding.title.text=data.title
                }
            }
}

 */