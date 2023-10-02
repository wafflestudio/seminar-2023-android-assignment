package com.example.assignment2

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.drawToBitmap
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeAViewBinding
import com.example.assignment2.databinding.TypeBViewBinding
import com.example.assignment2.databinding.TypeCViewBinding
sealed class History(val viewType : ViewType ){
    data class TypeA(val num: Int) : History(ViewType.A)
    data class TypeB(val bitmap: Bitmap): History(ViewType.B)
    data class TypeC(val turn: Int): History(ViewType.C)
    enum class ViewType {A, B, C}
}
class HistoryAdapter(
    private val mainViewModel: MainViewModel,
    private val context : Context,
    private val list: List<History>,
    ):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var textViewList: MutableList<TextView> = mutableListOf()
    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            is History.TypeA -> History.ViewType.A.ordinal
            is History.TypeB -> History.ViewType.B.ordinal
            is History.TypeC -> History.ViewType.C.ordinal
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            History.ViewType.A.ordinal -> TypeAViewHolder(
                TypeAViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            History.ViewType.B.ordinal -> TypeBViewHolder(
                TypeBViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            History.ViewType.C.ordinal -> TypeCViewHolder(
                TypeCViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            else -> throw IllegalAccessError("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        when(holder){
            is TypeAViewHolder -> {
                holder.bind(data as History.TypeA)
            }
            is TypeBViewHolder -> {
                holder.bind(data as History.TypeB)
            }
            is TypeCViewHolder -> {
                holder.bind(data as History.TypeC)
            }
        }
    }

    inner class TypeAViewHolder(private val binding: TypeAViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: History.TypeA){
            binding.turn.text = "${data.num}턴"
        }
    }
    inner class TypeBViewHolder(private val binding: TypeBViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: History.TypeB) {
            binding.image.setImageBitmap(data.bitmap)
        }
    }
    inner class TypeCViewHolder(private val binding: TypeCViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: History.TypeC){
            binding.textview.text = "되돌아가기\n"
            binding.textview.id = data.turn
            textViewList.add(binding.textview)
            for(index in 0..< textViewList.size){
                if(binding.textview == textViewList[index]){
                    binding.textview.setOnClickListener{
                        textViewList = textViewList.subList(0, index + 1)
                        mainViewModel.rewindData(index + 1)
                    }
                }
            }
        }
    }
}