package com.jutak.assignment3;

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.VocabularyListBinding


class MyAdapter(private val data: ArrayList<MyMultiData.Voca>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var binding: VocabularyListBinding? = null;

    override fun getItemCount(): Int {
        Log.d("item count", data.size.toString())
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = VocabularyListBinding.inflate(LayoutInflater.from(parent.context))
        setMatchParentToRecyclerView()
        Log.d("view holder", viewType.toString())
        return MyViewHolder(binding!!)
    }

    private fun setMatchParentToRecyclerView(){
        val layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding!!.root.layoutParams = layoutParams
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = data[position]
        (holder as MyViewHolder).bind(info)
    }

    private inner class MyViewHolder(private val binding: VocabularyListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: MyMultiData.Voca){
            binding.owner.text = data.owner
            binding.name.text = data.name
            //Log.d("voca_list", data.owner)
        }
    }
}

