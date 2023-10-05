package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeAItemViewBinding
import com.example.assignment2.databinding.TypeBItemViewBinding


sealed class MyMultiData(val viewType: ViewType){
    data class TypeA(val num: Int): MyMultiData(ViewType.A)
    data class TypeB(val O: BooleanArray, val X: BooleanArray): MyMultiData(ViewType.B)
    enum class ViewType{A, B}
}

class BoardAdapter(private val data: List<MyMultiData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is MyMultiData.TypeA -> MyMultiData.ViewType.A.ordinal
            is MyMultiData.TypeB -> MyMultiData.ViewType.B.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MyMultiData.ViewType.A.ordinal -> {
                TypeAViewHolder(TypeAItemViewBinding.inflate(LayoutInflater.from(parent.context)))
            }
            MyMultiData.ViewType.B.ordinal -> {
                TypeBViewHolder(TypeBItemViewBinding.inflate(LayoutInflater.from(parent.context)))
            }
            else -> throw IllegalStateException("Invalid ViewType")
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val board = data[position]
        when (holder){
            is TypeAViewHolder -> {
                holder.bind()
            }
            is TypeBViewHolder ->{
                holder.bind(board as MyMultiData.TypeB)
            }
        }
    }


    inner class TypeAViewHolder(private val binding: TypeAItemViewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }
    inner class TypeBViewHolder(private val binding: TypeBItemViewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: MyMultiData.TypeB) {
            val O_Indices = board.O.withIndex()
                .filter { it.value }
                .map { it.index }
            val X_Indices = board.X.withIndex()
                .filter { it.value }
                .map { it.index }
            for (index in O_Indices){
                when (index){
                    0 -> binding.b1.text = "O"
                    1 -> binding.b2.text = "O"
                    2 -> binding.b3.text = "O"
                    3 -> binding.b4.text = "O"
                    4 -> binding.b5.text = "O"
                    5 -> binding.b6.text = "O"
                    6 -> binding.b7.text = "O"
                    7 -> binding.b8.text = "O"
                    8 -> binding.b9.text = "O"
                }
            }
            for (index in X_Indices){
                when (index){
                    0 -> binding.b1.text = "X"
                    1 -> binding.b2.text = "X"
                    2 -> binding.b3.text = "X"
                    3 -> binding.b4.text = "X"
                    4 -> binding.b5.text = "X"
                    5 -> binding.b6.text = "X"
                    6 -> binding.b7.text = "X"
                    7 -> binding.b8.text = "X"
                    8 -> binding.b9.text = "X"
                }
            }
        }
    }
}

