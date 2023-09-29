package com.example.assignment2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    enum class Status {
        X_WIN, O_WIN, DRAW, CONTINUE
    }

    private var board = Array<Int>(10) { 0 }
    private var team = 1
    private var gameStatus = Status.CONTINUE

    private fun stringTeam(): String {
        return if (team == 1) "O" else "X"
    }
    private fun stringTeamBefore(): String {
        return if (team == 1) "X" else "O"
    }

    private fun initBoard() {

        binding.gameButton.text = "초기화"
        binding.gameButton.setBackgroundColor(Color.parseColor("#43000000"))

        gameStatus = Status.CONTINUE
        team = 1
        binding.gameStatus.text = (stringTeam() + "의 차례입니다")
        board[0] = -1
        for (i in 1..9) {
            board[i] = 0
        }
        binding.board1.text = ""
        binding.board2.text = ""
        binding.board3.text = ""
        binding.board4.text = ""
        binding.board5.text = ""
        binding.board6.text = ""
        binding.board7.text = ""
        binding.board8.text = ""
        binding.board9.text = ""
    }

    private fun changeBoard(position: Int): Boolean {
        if (board[position] == 0) {
            board[position] = team
            team = if (team == 1) 2 else 1
            // Check Game Status
            val resultList = listOf<Int>(
                board[1] * board[2] * board[3],
                board[4] * board[5] * board[6],
                board[7] * board[8] * board[9],
                board[1] * board[4] * board[7],
                board[2] * board[5] * board[8],
                board[3] * board[6] * board[9],
                board[1] * board[5] * board[9],
                board[3] * board[5] * board[7]
            )
            if (resultList.any { it == 1 }){
                gameStatus = Status.O_WIN
                binding.gameStatus.text = "게임 오버(O 승리)"
                binding.gameButton.text = "한판 더"
                binding.gameButton.setBackgroundColor(Color.parseColor("#2196F3"))
            }
            else if (resultList.any { it == 8 }){
                gameStatus = Status.X_WIN
                binding.gameStatus.text = "게임 오버(X 승리)"
                binding.gameButton.text = "한판 더"
                binding.gameButton.setBackgroundColor(Color.parseColor("#2196F3"))
            }
            else if (board.any { it == 0 }) {
                gameStatus = Status.CONTINUE
                binding.gameStatus.text = (stringTeam() + "의 차례입니다")
            }
            else {
                gameStatus = Status.DRAW
                binding.gameStatus.text = "무승부"
                binding.gameButton.text = "한판 더"
                binding.gameButton.setBackgroundColor(Color.parseColor("#2196F3"))
            }

            return true
        }
        return false      // not changed
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBoard()

        binding.gameButton.setOnClickListener {
            initBoard()
            // 서랍도 초기화
        }

        binding.hamburgerMenu.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }

        binding.board1.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(1))
                binding.board1.text = stringTeamBefore()
        }
        binding.board2.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(2))
                binding.board2.text = stringTeamBefore()
        }
        binding.board3.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(3))
                binding.board3.text = stringTeamBefore()
        }
        binding.board4.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(4))
                binding.board4.text = stringTeamBefore()
        }
        binding.board5.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(5))
                binding.board5.text = stringTeamBefore()
        }
        binding.board6.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(6))
                binding.board6.text = stringTeamBefore()
        }
        binding.board7.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(7))
                binding.board7.text = stringTeamBefore()
        }
        binding.board8.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(8))
                binding.board8.text = stringTeamBefore()
        }
        binding.board9.setOnClickListener {
            if (gameStatus == Status.CONTINUE && changeBoard(9))
                binding.board9.text = stringTeamBefore()
        }

    }
}