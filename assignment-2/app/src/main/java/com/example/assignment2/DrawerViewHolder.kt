package com.example.assignment2

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.DrawerBinding

class DrawerViewHolder(private val binding: DrawerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data : MainActivity.MyData){
        binding.turnNumber.text = data.num.toString() + "턴"

        val gridLayout = binding.miniTictacto
        for(i in 1..data.O_lst.size){
            val child = gridLayout.getChildAt(data.O_lst[i-1]) as TextView
            child.text = "O"
        }
        for(i in 1..data.X_lst.size){
            val child = gridLayout.getChildAt(data.X_lst[i-1]) as TextView
            child.text = "X"
        }
    }
}