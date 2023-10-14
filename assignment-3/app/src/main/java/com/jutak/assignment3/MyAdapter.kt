package com.jutak.assignment3;

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.VocabularyListBinding


class MyAdapter(private val data: LiveData<out List<out MyMultiData>>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var binding: VocabularyListBinding? = null

    override fun getItemViewType(position: Int): Int {
        return data.value!![position].viewType.ordinal
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = VocabularyListBinding.inflate(LayoutInflater.from(parent.context))
        setMatchParentToRecyclerView()
        return when(viewType){
            MyMultiData.ViewType.A.ordinal -> VocaViewHolder(binding!!)
            MyMultiData.ViewType.B.ordinal -> WordViewHolder(binding!!)
            else -> throw IllegalStateException("Invalid ViewType")
        }
    }

    private fun setMatchParentToRecyclerView(){
        val layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding!!.root.layoutParams = layoutParams
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = data.value!![position]
        when(info){
            is MyMultiData.Voca -> (holder as VocaViewHolder).bind(info)
            is MyMultiData.Word -> (holder as WordViewHolder).bind(info)
        }


        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    inner class VocaViewHolder(private val binding: VocabularyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.Voca) {
            binding.first.text = data.owner
            binding.second.text = data.name
        }
    }

    inner class WordViewHolder(private val binding: VocabularyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.Word) {
            binding.first.text = data.spell
            binding.second.text = data.meaning
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener
}

