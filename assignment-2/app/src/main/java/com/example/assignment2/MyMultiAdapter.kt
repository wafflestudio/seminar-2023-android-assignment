package com.example.assignment2

import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeAItemViewBinding
import com.example.assignment2.databinding.TypeBItemViewBinding
import com.example.assignment2.databinding.TypeCItemViewBinding

sealed class MyMultiData(val viewType: ViewType) {
    data class TypeA(val board : Array<Array<Boolean?>>, val count:Int) : MyMultiData(ViewType.A)
    data class TypeB(val gameStart: String) : MyMultiData(ViewType.B)
    data class TypeC(val board : Array<Array<Boolean?>>, val team:Boolean, val isEnd:Boolean) : MyMultiData(ViewType.C)
    enum class ViewType { A, B, C }
}

class MyMultiAdapter(
    private var list:List<MyMultiData>
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

    fun setData(newList: List<MyMultiData>) {
        list = newList;
    }

    private fun fillBoard(column:Int, row:Int, block:TextView, board:Array<Array<Boolean?>>){
        if(board[column][row] == true) block.text = "X"
        else if (board[column][row] == false) block.text = "O"
        else block.text = "    "
    }
    inner class TypeAViewHolder(private val binding: TypeAItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data:MyMultiData.TypeA){
            fillBoard(0,0,binding.block00,data.board)
            fillBoard(0,1,binding.block01,data.board)
            fillBoard(0,2,binding.block02,data.board)
            fillBoard(1,0,binding.block10,data.board)
            fillBoard(1,1,binding.block11,data.board)
            fillBoard(1,2,binding.block12,data.board)
            fillBoard(2,0,binding.block20,data.board)
            fillBoard(2,1,binding.block21,data.board)
            fillBoard(2,2,binding.block22,data.board)

            binding.whatNumber.text = data.count.toString()
        }

    }
    inner class TypeBViewHolder(private val binding: TypeBItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data:MyMultiData.TypeB){
            binding.text.text = data.gameStart
        }
    }
    inner class TypeCViewHolder(private val binding: TypeCItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data:MyMultiData.TypeC){
            fillBoard(0,0,binding.block00,data.board)
            fillBoard(0,1,binding.block01,data.board)
            fillBoard(0,2,binding.block02,data.board)
            fillBoard(1,0,binding.block10,data.board)
            fillBoard(1,1,binding.block11,data.board)
            fillBoard(1,2,binding.block12,data.board)
            fillBoard(2,0,binding.block20,data.board)
            fillBoard(2,1,binding.block21,data.board)
            fillBoard(2,2,binding.block22,data.board)

            if (!data.isEnd) binding.status.text = "무승부!"
            else if(data.team) binding.status.text = "O의 승리!"
            else binding.status.text = "X의 승리"
        }
    }
}