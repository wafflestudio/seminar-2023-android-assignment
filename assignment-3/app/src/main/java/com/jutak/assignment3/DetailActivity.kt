package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.jutak.assignment3.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), OnItemClickListener<Int> {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onItemClick(itemId: Int) {
        // 클릭한 아이템의 ID를 사용하여 필요한 동작 수행
        // 예: 상세 화면으로 전환하면서 ID를 전달
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent로 전달된 데이터 가져오기
        val itemId = intent.getIntExtra("itemId", 1)
        val recAdapter = WordListAdapter(this, viewModel.getWordInfos(itemId), this)

        val backButton = binding.backButton
        backButton.setOnClickListener() {
            onBackPressed()
        }

        binding.wordInfo.adapter = recAdapter
        binding.wordInfo.layoutManager = LinearLayoutManager(this)

        viewModel.wordListData.observe(this) { wordList ->
            // LiveData를 관찰하여 데이터를 얻고, adapter를 업데이트
            Log.d("DetailActivity", "Word list size: ${wordList.size}") // 디버그 로그 추가
            recAdapter.setItems(wordList)
            recAdapter.notifyDataSetChanged()
        }

//        lifecycleScope.launch {
//            try {
//                val wordList = viewModel.getWordInfos(itemId) // 데이터 로딩 (비동기 작업)
//                recAdapter.setItems(wordList)
//            } catch (e: Exception) {
//                // 오류 처리
//                Log.e("DetailActivity", "Error loading data: ${e.message}")
//            }
//        }

        // 가져온 데이터를 상세 화면에 표시
//        binding.detailTextView.text = "Item ID: $itemId" // 상세 정보에 따라 데이터 표시 방식을 수정

    }
}