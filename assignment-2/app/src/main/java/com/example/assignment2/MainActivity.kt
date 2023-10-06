package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var team: Boolean = true;
        var isEnd: Boolean = false;
        var record: Array<Array<Array<Boolean?>>> = Array(9){Array(3) { Array(3) { null } }}
        var board: Array<Array<Boolean?>> = Array(3) { Array(3) { null } }

        var count:Int = 0

        fun checkEnd(){
            // Check rows
            for (i in 0 until 3) {
                if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                    isEnd = true;
                }
            }

            // Check columns
            for (i in 0 until 3) {
                if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                    isEnd = true;
                }
            }

            // Check diagonals
            if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                isEnd = true;
            }
            if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                isEnd = true;
            }
        }

        fun boardClick(column:Int, row:Int, block:TextView, reset:TextView, status:TextView ){
            if(count<9 && board[column][row] == null && !isEnd){
                board[column][row] = team
                record[count] = board
                count++
                team = !team

                if(team) block.text = "O"
                else block.text = "X"

                checkEnd()

                if(isEnd) reset.text = getString(R.string.restart)
                else reset.text = getString(R.string.reset)

                if(isEnd || count==9){
                    if(!isEnd) status.text = getString(R.string.NoWin)
                    else status.text = getString(R.string.GameOver)
                }
                else{
                    if(team) status.text = getString(R.string.team2status)
                    else status.text = getString(R.string.team1status)
                }
            }
        }

        binding.menu.setOnClickListener{
            binding.root.openDrawer(binding.drawer)
        }

        binding.block00.setOnClickListener {
            boardClick(0,0, binding.block00, binding.reset, binding.status)
        }

        binding.block01.setOnClickListener {
            boardClick(0,1, binding.block01, binding.reset, binding.status)
        }

        binding.block02.setOnClickListener {
            boardClick(0,2, binding.block02, binding.reset, binding.status)
        }

        binding.block10.setOnClickListener {
            boardClick(1,0, binding.block10, binding.reset, binding.status)
        }

        binding.block11.setOnClickListener {
            boardClick(1,1, binding.block11, binding.reset, binding.status)
        }

        binding.block12.setOnClickListener {
            boardClick(1,2, binding.block12, binding.reset, binding.status)
        }

        binding.block20.setOnClickListener {
            boardClick(2,0, binding.block20, binding.reset, binding.status)
        }

        binding.block21.setOnClickListener {
            boardClick(2,1, binding.block21, binding.reset, binding.status)
        }

        binding.block22.setOnClickListener {
            boardClick(2,2, binding.block22, binding.reset, binding.status)
        }

        binding.reset.setOnClickListener {
            team = true;
            isEnd = false;
            record = Array(9){Array(3) { Array(3) { null } }}
            board = Array(3) { Array(3) { null } }
            count = 0

            binding.block00.text = getString(R.string.empty)
            binding.block01.text = getString(R.string.empty)
            binding.block02.text = getString(R.string.empty)
            binding.block10.text = getString(R.string.empty)
            binding.block11.text = getString(R.string.empty)
            binding.block12.text = getString(R.string.empty)
            binding.block20.text = getString(R.string.empty)
            binding.block21.text = getString(R.string.empty)
            binding.block22.text = getString(R.string.empty)

            binding.reset.text = getString(R.string.reset)

        }
    }
}