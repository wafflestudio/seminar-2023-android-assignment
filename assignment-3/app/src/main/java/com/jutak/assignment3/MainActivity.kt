package com.jutak.assignment3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.CustomTitleLayoutBinding
import com.jutak.assignment3.databinding.PostDialogLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemClickListener<Int> {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private var wordListInfos = emptyList<MyData.WordListInfo>()

    override fun onItemClick(itemId: Int) {
        // 클릭한 아이템의 ID를 사용하여 필요한 동작 수행
        // 예: 상세 화면으로 전환하면서 ID를 전달
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("itemId", itemId)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            // 비동기 작업을 수행할 코루틴 블록
            val result = viewModel.fetchWordListInfo()
            withContext(Dispatchers.Main) {
                // 메인 스레드에서 수행할 작업
                if (result != "SUCCESS") {
                    Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
                }
            }
            wordListInfos = viewModel.getWordListInfo()
            withContext(Dispatchers.Main) {
                // 메인 스레드에서 수행할 작업
                if (result == "SUCCESS") {
                    val adapter = WordListAdapter(this@MainActivity, wordListInfos.toMutableList(), this@MainActivity)
                    binding.wordList.adapter= adapter
                    binding.wordList.layoutManager = LinearLayoutManager(this@MainActivity)

                    viewModel.wordListInfoData.observe(this@MainActivity) { wordListInfo ->
                        // LiveData를 관찰하여 데이터를 얻고, adapter를 업데이트
                        Log.d("DetailActivity", "Word list size: ${wordListInfo.size}") // 디버그 로그 추가
                        adapter.setItems(wordListInfo)
                    }
                }
            }

        }

        binding.postBtn.setOnClickListener() {
            showInputDialog()
        }




    }

    private fun showInputDialog() {
        val dialogBinding = PostDialogLayoutBinding.inflate(layoutInflater)
        val customTitleLayoutBinding = CustomTitleLayoutBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCustomTitle(customTitleLayoutBinding.root)
            .setPositiveButton("Submit") { dialog, _ ->
                val owner = dialogBinding.ownerEditText.text.toString()
                val name = dialogBinding.nameEditText.text.toString()
                val password = dialogBinding.passwordEditText.text.toString()

                // 데이터 처리 (예: 로그로 출력)
                val message = MyData.WordListPostInfo(name, owner, password)

                lifecycleScope.launch(Dispatchers.IO) {
                    // 비동기 작업을 수행할 코루틴 블록
                    val result = viewModel.pushWordList(message)
                    withContext(Dispatchers.Main) {
                        // 메인 스레드에서 수행할 작업
                        if (result != "SUCCESS") {
                            Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }


}