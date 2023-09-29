package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.assignment2.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val mainViewModel : MainViewModel by viewModels()
    private var turn = 1
    private var flag = 0 // 게임 종료 여부
    private var board: Array<Array<Int>> = Array(3){ Array(3) {0} }
    private fun whoWins(newBoard: Array<Array<Int>>): String{
        for (row in 0..2){
            when(newBoard[row].sum()){
                3 -> return "O"
                -3 -> return "X"
                else -> {}
            }
        }
        for (col in 0..2){
            var colSum = 0
            for(row in 0..2){
                colSum += newBoard[row][col]
            }
            when(colSum){
                3 -> return "O"
                -3 -> return "X"
                else-> {}
            }
        }

        for(step in 0..2 step 2){
            var diagonalSum = 0
            for(i in 0..2){
                diagonalSum+= newBoard[abs(step-i)][i]
            }
            when(diagonalSum){
                3 -> return "O"
                -3 -> return "X"
                else-> {}
            }
        }
        return "Not yet"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cell1.setOnClickListener{
            if(flag == 0 && board[0][0] == 0){
                board[0][0] = turn
                when(turn) {
                    1 -> binding.cell1.text = "O"
                    -1 -> binding.cell1.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
/*
        binding.cell2.setOnClickListener{
            if(flag == 0 && board[0][1] == 0){
                board[0][1] = turn
                when(turn) {
                    1 -> binding.cell2.text = "O"
                    -1 -> binding.cell2.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
        binding.cell3.setOnClickListener{
            if(flag == 0 && board[0][2] == 0){
                board[0][2] = turn
                when(turn) {
                    1 -> binding.cell3.text = "O"
                    -1 -> binding.cell3.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
        binding.cell4.setOnClickListener{
            if(flag == 0 && board[1][0] == 0){
                board[1][0] = turn
                when(turn) {
                    1 -> binding.cell4.text = "O"
                    -1 -> binding.cell4.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
        binding.cell5.setOnClickListener{
            if(flag == 0 && board[1][1] == 0){
                board[1][1] = turn
                when(turn) {
                    1 -> binding.cell5.text = "O"
                    -1 -> binding.cell5.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
        binding.cell6.setOnClickListener{
            if(flag == 0 && board[1][2] == 0){
                board[1][2] = turn
                when(turn) {
                    1 -> binding.cell6.text = "O"
                    -1 -> binding.cell6.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
        binding.cell7.setOnClickListener{
            if(flag == 0 && board[2][0] == 0){
                board[2][0] = turn
                when(turn) {
                    1 -> binding.cell7.text = "O"
                    -1 -> binding.cell7.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
        binding.cell8.setOnClickListener{
            if(flag == 0 && board[2][1] == 0){
                board[2][1] = turn
                when(turn) {
                    1 -> binding.cell8.text = "O"
                    -1 -> binding.cell8.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
        binding.cell9.setOnClickListener{
            if(flag == 0 && board[2][2] == 0){
                board[2][2] = turn
                when(turn) {
                    1 -> binding.cell9.text = "O"
                    -1 -> binding.cell9.text = "X"
                }
                turn *= -1
                when(whoWins(board)){
                    "O" -> {flag = 1; }
                    "X" -> {flag = -1;}
                    else-> {}
                }
            }
        }
*/
    }
}