package com.wafflestudio.assignment4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding

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