package com.example.assignment2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeAItemViewBinding

class Adapter(
    private val list:List<List<Int>>,
    private val context: Context
):RecyclerView.Adapter<Adapter.TypeAViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAViewHolder {
        val viewHolder=TypeAViewHolder(TypeAItemViewBinding.inflate(LayoutInflater.from(parent.context)))
        viewHolder.setIsRecyclable(false)
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TypeAViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TypeAViewHolder(private val binding: TypeAItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data:List<Int>){
                var i:Int=1
                Log.d("aaaa",data.toString())
                data.forEach{
                    val textView=TextView(context).apply{
                        text="${i}번째 턴 : ${it}"
                    }
                    i+=1
                    binding.historyview.addView(textView)
                }
            }
    }
}