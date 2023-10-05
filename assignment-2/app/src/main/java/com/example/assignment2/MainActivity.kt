package com.example.assignment2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: BoardAdapter

    private fun handleTurn(position: TextView, index: Int) {
        if (viewModel.array1[index] || viewModel.array2[index]) {
            return
        }

        if (viewModel.turn.value?.rem(2) == 1) {
            position.text = "O"
            viewModel.array1[index] = true
        } else {
            position.text = "X"
            viewModel.array2[index] = true
        }

        viewModel.data.add(MyMultiData.TypeB(viewModel.array1, viewModel.array2))
        viewModel.turn.value?.let { adapter.notifyItemChanged(it) }
        if (viewModel.checkCondition(viewModel.array1) || viewModel.checkCondition(viewModel.array2)){
            viewModel.endGame()
        }
        viewModel.nextTurn()
        if (viewModel.turn.value == 10) {
            viewModel.drawGame()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // val 설정
        val menu = binding.menu
        val commentary = binding.commentary
        val setting = binding.setting
        val board = binding.board

        // adapter
        adapter = BoardAdapter(viewModel.data)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 상단 바
        //// 좌상단
        menu.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }

        //// 우상단
        setting.setOnClickListener {
            viewModel.initialization()
            viewModel.turn.value?.let { adapter.notifyItemChanged(it) }
            for (i in 0 until board.childCount) {
                val position = board.getChildAt(i) as TextView
                position.text = ""
            }
        }

        //// 중앙
        viewModel.gameStatus.observe(this) {
            commentary.text = when (it) {
                GameStatus.O_TURN -> "O의 차례입니다."
                GameStatus.X_TURN -> "X의 차례입니다."
                GameStatus.END -> "게임 오버"
                GameStatus.DRAW -> "무승부"
            }
            if (it == GameStatus.O_TURN || it == GameStatus.X_TURN) {
                setting.text = "초기화"
                setting.setBackgroundColor(Color.parseColor("#b3aca8"))

            } else {
                setting.text = "한 판 더"
                setting.setBackgroundColor(Color.parseColor("#0070ff"))
            }
        }

        // 보드
        for (i in 0 until board.childCount) {
            val position = board.getChildAt(i) as TextView
            position.setOnClickListener {
                handleTurn(position, i)
            }
        }
    }
}

