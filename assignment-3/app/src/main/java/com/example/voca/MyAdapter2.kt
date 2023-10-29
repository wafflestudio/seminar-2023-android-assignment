package com.example.voca

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.persistableBundleOf
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.voca.databinding.VocaBinding
import com.example.voca.databinding.VocasBinding

class MyAdapter2(private val data: LiveData<out List<MyDataTypes>>,private val specific:MyDataTypes.VocaListSpecificInfo):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            MyDataTypes.ViewType.A.ordinal->{
                VocaViewHolder(VocaBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            }
            MyDataTypes.ViewType.B.ordinal->{
                VocaViewHolder(VocaBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            }
            else -> throw IllegalStateException("Invalid ViewType") // 발생할 일은 없다.
        }


    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is VocaListViewHolder->{
                holder.set(position)
            }
            is VocaViewHolder->{
                holder.set(position)
            }
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick2(it, position)
        }
    }
    inner class VocaListViewHolder(private val binding:VocasBinding): RecyclerView.ViewHolder(binding.root){

        /*
        val count=binding.historyTitle
        val boards=binding.historyBoard
        val restartButton=binding.historyGo
        */

        fun set(position: Int){
            Log.d("aaaa",data.value.toString())
            binding.ownerName.text=specific.owner
            //Log.d("aaaa",binding.ownerName.text.toString())
            binding.vocaListName.text=specific.name
            binding.vocaListButton.setOnClickListener {
                //  viewModel.openVocaList(position)
            }
            /*
            binding.historyTitle.text="${data[position].count}턴"
            val currentBoard=Array(9) { 0 }

            val ii=0
            binding.historyBoard.forEach {
                val textView=it as TextView
                val buttonNum=viewModel.getButtonNum2(textView,binding)

                textView.text=when (data[position].board[buttonNum]){
                    1->"O"
                    2->"X"
                    else->""
                }
                /*
                if(buttonNum==data[position].count-1){
                    textView.text=when (data[position].board[buttonNum]){
                        1->"O"
                        2->"X"
                        else->""
                    }
                }*/
            }*/
        }
    }

    inner class VocaViewHolder(private val binding: VocaBinding): RecyclerView.ViewHolder(binding.root){


        fun set(position: Int){

            binding.vocaName.text=specific.word_list[position].spell
            binding.vocaMean.text=specific.word_list[position].meaning
            binding.vocaButton.setOnClickListener {
                //openVocaList(position)
            }
            /*
            binding.historyTitle.text="${data[position].count}턴"
            val currentBoard=Array(9) { 0 }

            val ii=0
            binding.historyBoard.forEach {
                val textView=it as TextView
                val buttonNum=viewModel.getButtonNum2(textView,binding)

                textView.text=when (data[position].board[buttonNum]){
                    1->"O"
                    2->"X"
                    else->""
                }
                /*
                if(buttonNum==data[position].count-1){
                    textView.text=when (data[position].board[buttonNum]){
                        1->"O"
                        2->"X"
                        else->""
                    }
                }*/
            }*/
        }
    }
    interface OnItemClickListener {
        fun onClick2(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener


}