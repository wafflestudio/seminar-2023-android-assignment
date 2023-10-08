package com.example.assignment2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.example.assignment2.databinding.GridItemBinding

class BoardCellAdapter (private val context: Context, private val viewModel: MainViewModel): BaseAdapter() {

    private var list: List<Char> = emptyList() // LiveData의 변경을 반영하기 위한 리스트

    init {
        // LiveData를 관찰하고 변경될 때마다 list를 업데이트
        viewModel.data.observeForever { updatedData ->
            // LiveData가 변경될 때마다 list를 업데이트
            list = updatedData[viewModel.getTurn()][1].map { cell ->
                when (cell) {
                    0 -> ' '
                    1 -> 'O'
                    2 -> 'X'
                    else -> ' ' // 예외 처리 - 유효하지 않은 경우 공백 문자
                }
            }
            notifyDataSetChanged() // 어댑터에게 데이터 변경을 알림
        }
    }
    override fun getCount(): Int = list.size
    override fun getItemId(position: Int): Long = 0
    override fun getItem(position: Int): Char = list[position]
    fun getItem(position: Int, list: List<Char>): Char = list[position]

    fun setItems(newList: List<Char>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val binding: GridItemBinding
        // 1. 재활용 가능하도록 convertView를 이용해 효율적 메모리 관리

        if (convertView == null) {
            binding = GridItemBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        }
        else {
            binding = convertView.tag as GridItemBinding
        }

        // 2. 해당 셀의 데이터를 설정함
        binding.textViewGridItem.text = list.get(position).toString()
        binding.textViewGridItem.setOnClickListener() {
            if (!viewModel.getGameStat().isGameFinished()) {
                Log.d("BCA", "clicked well")
                viewModel.handleClickEvent(position)
                val newList = viewModel.getBoard().toList()
                binding.textViewGridItem.text = getItem(position, newList).toString()
                Log.d("BCA", "${binding.textViewGridItem.text}")
            }

        }



        return binding.root
    }
}