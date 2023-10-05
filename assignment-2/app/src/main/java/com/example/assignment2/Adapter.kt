package com.example.assignment2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeAItemViewBinding
import com.example.assignment2.databinding.TypeBItemViewBinding
import com.example.assignment2.databinding.TypeCItemViewBinding

sealed class MyMultiData(val viewType: ViewType) {
    data class TypeA(val texts: List<Int>) : MyMultiData(viewType = ViewType.A)
    data class TypeB(@DrawableRes val imageRes: Int) : MyMultiData(viewType = ViewType.B)
    data class TypeC(val num: Int) : MyMultiData(viewType = ViewType.C)
    enum class ViewType { A, B, C } // viewType은 잠시 무시하자
}
class Adapter(
    private val list:List<MyMultiData>,
    private val context: Context
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            MyMultiData.ViewType.A.ordinal -> TypeAViewHolder(
                TypeAItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            MyMultiData.ViewType.B.ordinal -> TypeBViewHolder(
                TypeBItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            MyMultiData.ViewType.C.ordinal -> TypeCViewHolder(
                TypeCItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            else -> throw IllegalAccessError("")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            is MyMultiData.TypeA -> MyMultiData.ViewType.A.ordinal
            is MyMultiData.TypeB -> MyMultiData.ViewType.B.ordinal
            is MyMultiData.TypeC -> MyMultiData.ViewType.C.ordinal
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data=list[position]
        when(holder){
            is TypeAViewHolder -> holder.bind(data as MyMultiData.TypeA)
            is TypeBViewHolder -> holder.bind(data as MyMultiData.TypeB)
            is TypeCViewHolder -> holder.bind(data as MyMultiData.TypeC)
        }
    }

    inner class TypeAViewHolder(private val binding: TypeAItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data:MyMultiData.TypeA){
                var i:Int=1
                data.texts.forEach{
                    val textView=TextView(context).apply{
                        text="($i)번째 턴 : ($it)"
                    }
                    i+=1
                    binding.root.addView(textView)
                }
            }
    }
    inner class TypeBViewHolder(private val binding: TypeBItemViewBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: MyMultiData.TypeB){
            binding.image.setImageResource(data.imageRes)
        }
    }
    inner class TypeCViewHolder(private val binding: TypeCItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data:MyMultiData.TypeC){
            binding.num.text=data.num.toString()
        }
    }
}