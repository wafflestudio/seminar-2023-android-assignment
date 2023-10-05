package com.example.assignment2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.assignment2.databinding.GridItemBinding

class BoardCellAdapter<T>(private val context: Context, private val list: List<T>): BaseAdapter() {

    override fun getCount(): Int = list.size
    override fun getItemId(position: Int): Long = 0
    override fun getItem(position: Int): T = list[position]
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
        val itemData = getItem(position)
        binding.textViewGridItem.text = itemData.toString()

        return binding.root
    }
}