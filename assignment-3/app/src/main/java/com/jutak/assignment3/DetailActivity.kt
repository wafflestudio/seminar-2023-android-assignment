package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.jutak.assignment3.databinding.ActivityDetailBinding
import com.jutak.assignment3.databinding.CustomTitleLayoutBinding
import com.jutak.assignment3.databinding.EditDialogLayoutBinding
import com.jutak.assignment3.databinding.PostDialogLayoutBinding
import com.jutak.assignment3.databinding.WordAddDialogLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), OnItemClickListener<Int> {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MainViewModel by viewModels()
    // valid 인증 받은 password 및 permission을 단어 삭제와 추가에도 계속 써야 하므로, DetailActivity 내 전역변수 선언
    private var password = ""
    private var permission = false
    override fun onItemClick(itemId: Int) {

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
            onBackPressed() // deprecated 되어서 바꿔야 함!
        }

        val editButton = binding.detailEditBtn
        editButton.setOnClickListener() {
            showVerifyDialog(itemId)
            editButton.visibility = View.GONE
            binding.detailRemoveBtn.visibility = View.VISIBLE
            binding.detailAddBtn.visibility = View.VISIBLE
        }

        val detailRemoveButton = binding.detailRemoveBtn
        detailRemoveButton.setOnClickListener() {
            val toastText = viewModel.deleteWordList(itemId, password)
            Toast.makeText(this@DetailActivity, toastText, Toast.LENGTH_SHORT).show()
            // 요청 성공 시 메인화면으로 <- 요청 성공/실패 체크
            onBackPressed()

        }
        val detailAddButton = binding.detailAddBtn
        detailAddButton.setOnClickListener() {
            // 다이얼로그 생성 후 해당 입력값들로 MyData.WordInfo 인스턴스 생성 및 viewModel.putWord()
            showWordAddDialog(itemId)
        }


        binding.wordInfo.adapter = recAdapter
        binding.wordInfo.layoutManager = LinearLayoutManager(this)

        viewModel.wordListData.observe(this) { wordList ->
            // LiveData를 관찰하여 데이터를 얻고, adapter를 업데이트
            Log.d("DetailActivity", "Word list size: ${wordList.size}") // 디버그 로그 추가
            recAdapter.setItems(wordList)
            recAdapter.notifyDataSetChanged()
        }

    }

    private fun showVerifyDialog(itemId: Int) {
        val dialogBinding = EditDialogLayoutBinding.inflate(layoutInflater)
        val customTitleLayoutBinding = CustomTitleLayoutBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCustomTitle(customTitleLayoutBinding.root)
            .setTitle("수정 권한 확인하기")
            .setPositiveButton("Submit") { dialog, _ ->
                password = dialogBinding.inputPassword.text.toString()

                // 비밀번호 검증 (뷰모델로 password 전송하고, 뷰모델은 모델로 password 전송.
                permission = viewModel.getPermission(itemId, password)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun showWordAddDialog(itemId: Int) {
        val dialogBinding = WordAddDialogLayoutBinding.inflate(layoutInflater)
        val customTitleLayoutBinding = CustomTitleLayoutBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCustomTitle(customTitleLayoutBinding.root)
            .setTitle("단어 추가하기")
            .setPositiveButton("Submit") { dialog, _ ->
                val spell = dialogBinding.dialogWordSpell.text.toString()
                val meaning = dialogBinding.dialogWordMeaning.text.toString()
                val synonym = dialogBinding.dialogWordSynonym.text?.toString()
                val antonym = dialogBinding.dialogWordAntonym.text?.toString()
                val sentence = dialogBinding.dialogWordSentence.text?.toString()
                viewModel.putWord(itemId, MyData.WordInfo(spell, meaning, synonym, antonym, sentence))
                // 비밀번호 검증 (뷰모델로 password 전송하고, 뷰모델은 모델로 password 전송.

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }


}