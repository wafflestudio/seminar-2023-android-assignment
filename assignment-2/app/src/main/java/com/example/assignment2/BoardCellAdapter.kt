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

    private val list: List<String> = viewModel.getDataList()
    override fun getCount(): Int = list.size
    override fun getItemId(position: Int): Long = 0
    override fun getItem(position: Int): String = list[position]
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
        binding.textViewGridItem.text = viewModel.getData(position).toString()
        binding.textViewGridItem.setOnClickListener() {
            if (binding.textViewGridItem.text == " ") {
                viewModel.handleClickEvent(position)
                binding.textViewGridItem.text = viewModel.getData(position).toString()
                Log.d("tvgi", "dataList updated: $list")
            }

        }



        return binding.root
    }
}