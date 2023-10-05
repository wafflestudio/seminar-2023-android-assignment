package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeAItemViewBinding
import com.example.assignment2.databinding.TypeBItemViewBinding
import com.example.assignment2.databinding.TypeCItemViewBinding

sealed class MyMultiData(val viewType: ViewType) {
    data class TypeA(val texts: List<String>) : MyMultiData(ViewType.A)
    data class TypeB(@DrawableRes val imageRes: Int) : MyMultiData(ViewType.B)
    data class TypeC(val num: Int) : MyMultiData(ViewType.C)
    enum class ViewType { A, B, C }
}

class MyMultiAdapter(
    private val list:List<MyMultiData>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = list.size;

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType.ordinal
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MyMultiData.ViewType.A.ordinal -> {
                TypeAViewHolder(TypeAItemViewBinding.inflate(LayoutInflater.from(parent.context)))
            }
            MyMultiData.ViewType.B.ordinal -> {
                TypeBViewHolder(TypeBItemViewBinding.inflate(LayoutInflater.from(parent.context)))
            }
            MyMultiData.ViewType.C.ordinal -> {
                TypeCViewHolder(TypeCItemViewBinding.inflate(LayoutInflater.from(parent.context)))
            }
            else -> throw IllegalStateException("Invalid ViewType")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        when(holder){
            is TypeAViewHolder -> {
                holder.bind(data as MyMultiData.TypeA)
            }
            is TypeBViewHolder -> {
                holder.bind(data as MyMultiData.TypeB)
            }
            is TypeCViewHolder -> {
                holder.bind(data as MyMultiData.TypeC)
            }
        }
    }


    inner class TypeAViewHolder(private val binding: TypeAItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data:MyMultiData.TypeA){
                data.texts.forEach(){

                }
            }
    }
    inner class TypeBViewHolder(private val binding: TypeBItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data:MyMultiData.TypeB){

            }
    }
    inner class TypeCViewHolder(private val binding: TypeCItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data:MyMultiData.TypeC){

        }
    }
}