package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.forEach
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 로딩
        binding.title.text = getTitle(viewModel.gameStatus.value)

        binding.reset.setOnClickListener {
            viewModel.resetViewModel()
            binding.ticTacToePlayground.forEach { view ->
                val textView = view as TextView
                textView.text = ""
            }
        }

        binding.ticTacToePlayground.forEach { view ->
            val textView = view as TextView
            view.setOnClickListener {
                val buttonNumber = getTickTacToeButtonNumber(textView) ?: return@setOnClickListener
                Log.d("my", "buttonNumber = $buttonNumber")
                val turnText = viewModel.handleClickEvent(buttonNumber)
                turnText?.let { textView.text = it }
            }
        }

        viewModel.gameStatus.observe(this) {
            binding.title.text = getTitle(it)
            binding.reset.text = if (it == GameStatus.GAME_OVER) {
                "한판 더"
            } else {
                "초기화"
            }
        }

    }

    private fun getTitle(gameStatus: GameStatus?): String = when (gameStatus) {
        GameStatus.START -> "게임 시작!"
        GameStatus.GAME_OVER -> "게임 오버"
        GameStatus.EVEN_TURN -> "O의 차례입니다"
        GameStatus.ODD_TURN -> "X의 차례입니다"
        GameStatus.DRAW -> "무승부"
        else -> "UNKNOWN"
    }

    private fun getTickTacToeButtonNumber(button: TextView): Int? = when (button) {
        binding.ticTacToe0 -> 0
        binding.ticTacToe1 -> 1
        binding.ticTacToe2 -> 2
        binding.ticTacToe3 -> 3
        binding.ticTacToe4 -> 4
        binding.ticTacToe5 -> 5
        binding.ticTacToe6 -> 6
        binding.ticTacToe7 -> 7
        binding.ticTacToe8 -> 8
        else -> null
    }

}