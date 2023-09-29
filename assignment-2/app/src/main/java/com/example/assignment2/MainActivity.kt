package com.example.assignment2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: HistoryAdapter
    private val viewModel: MyViewModel by viewModels()

    private fun teamToString(team: Int): String {
        return when (team) {
            0 -> ""
            1 -> "O"
            2 -> "X"
            else -> "ERROR"
        }
    }
    enum class Status {
        X_WIN, O_WIN, DRAW, CONTINUE
    }

    private fun initGame() {

        viewModel.boardInit()

        // initialize drawer
        viewModel.boardData.clear()
        viewModel.boardData.add(MyMultiData.GameStart(0))
        adapter.notifyDataSetChanged()

        // initialize game status
        viewModel.team = 1
        viewModel.liveGameStatus.value = Status.CONTINUE
    }

    private fun boardMulti(idx1: Int, idx2: Int, idx3: Int): Int
    = viewModel.board[idx1] * viewModel.board[idx2] * viewModel.board[idx3]

    private fun clickedBoard(position: Int){

        if (viewModel.liveGameStatus.value != Status.CONTINUE) {
            return
        }

        if (viewModel.board[position] == 0) {

            viewModel.changeBoard(position)

            viewModel.boardData.add(MyMultiData.GameBoard(0))
            adapter.notifyItemChanged(viewModel.boardData.lastIndex)

            viewModel.team = if (viewModel.team == 1) 2 else 1
            
            // Check Game Status
            val resultList = listOf<Int>(
                boardMulti(1, 2, 3),
                boardMulti(4, 5, 6),
                boardMulti(7, 8, 9),
                boardMulti(1, 4, 7),
                boardMulti(2, 5, 8),
                boardMulti(3, 6, 9),
                boardMulti(1, 5, 9),
                boardMulti(3, 5, 7)
            )
            if (resultList.any { it == 1 }) {
                viewModel.liveGameStatus.value = Status.O_WIN
            }
            else if (resultList.any { it == 8 }) {
                viewModel.liveGameStatus.value = Status.X_WIN
            }
            else if (viewModel.board.any { it == 0 }) {
                viewModel.liveGameStatus.value = Status.CONTINUE
            }
            else {
                viewModel.liveGameStatus.value = Status.DRAW
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = HistoryAdapter(viewModel.boardData, this)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // board status changed -> change title and button
        viewModel.liveGameStatus.observe(this) {
            binding.gameStatus.text = when (it) {
                Status.X_WIN -> "게임 오버(X 승리)"
                Status.O_WIN -> "게임 오버(O 승리)"
                Status.DRAW -> "무승부"
                Status.CONTINUE -> teamToString(viewModel.team) + "의 차례입니다"
            }
            if (it == Status.CONTINUE) {
                binding.initButton.text = "초기화"
                binding.initButton.setBackgroundColor(Color.parseColor("#43000000"))
            }
            else {
                binding.initButton.text = "한판 더"
                binding.initButton.setBackgroundColor(Color.parseColor("#2196F3"))
            }
        }

        // board status changed -> change board text
        viewModel.liveBoard.observe(this) {
            binding.board1.text = teamToString(it[1])
            binding.board2.text = teamToString(it[2])
            binding.board3.text = teamToString(it[3])
            binding.board4.text = teamToString(it[4])
            binding.board5.text = teamToString(it[5])
            binding.board6.text = teamToString(it[6])
            binding.board7.text = teamToString(it[7])
            binding.board8.text = teamToString(it[8])
            binding.board9.text = teamToString(it[9])
        }

        binding.initButton.setOnClickListener {
            initGame()
        }

        binding.hamburgerMenu.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }

        // when each board is clicked
        binding.board1.setOnClickListener {
            clickedBoard(1)
        }
        binding.board2.setOnClickListener {
            clickedBoard(2)
        }
        binding.board3.setOnClickListener {
            clickedBoard(3)
        }
        binding.board4.setOnClickListener {
            clickedBoard(4)
        }
        binding.board5.setOnClickListener {
            clickedBoard(5)
        }
        binding.board6.setOnClickListener {
            clickedBoard(6)
        }
        binding.board7.setOnClickListener {
            clickedBoard(7)
        }
        binding.board8.setOnClickListener {
            clickedBoard(8)
        }
        binding.board9.setOnClickListener {
            clickedBoard(9)
        }

    }
}