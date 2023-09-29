package com.example.assignment2

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var bitmap: Bitmap
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cell1.setOnClickListener{
            when(mainViewModel.whoWins(1)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell1.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell1.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell1.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell1.text = "O"
                }
            }
            mainViewModel.boardLiveData.observe(this){
                binding.cell1.text = it[0][0].toString()
            }
            /*gridLayout = binding.board
            bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            gridLayout.draw(canvas)*/
        }
        binding.cell2.setOnClickListener{
            when(mainViewModel.whoWins(2)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell2.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell2.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell2.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell2.text = "O"
                }
            }
        }
        binding.cell3.setOnClickListener{
            when(mainViewModel.whoWins(3)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell3.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell3.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell3.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell3.text = "O"
                }
            }
        }
        binding.cell4.setOnClickListener{
            when(mainViewModel.whoWins(4)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell4.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell4.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell4.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell4.text = "O"
                }
            }
        }
        binding.cell5.setOnClickListener{
            when(mainViewModel.whoWins(5)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell5.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell5.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell5.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell5.text = "O"
                }
            }
        }
        binding.cell6.setOnClickListener{
            when(mainViewModel.whoWins(6)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell6.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell6.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell6.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell6.text = "O"
                }
            }
        }
        binding.cell7.setOnClickListener{
            when(mainViewModel.whoWins(7)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell7.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell7.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell7.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell7.text = "O"
                }
            }
        }
        binding.cell8.setOnClickListener{
            when(mainViewModel.whoWins(8)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell8.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell8.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell8.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell8.text = "O"
                }
            }
        }
        binding.cell9.setOnClickListener{
            when(mainViewModel.whoWins(9)){
                "O wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell9.text = "O"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "X wins"->{
                    binding.info.text = "게임 오버"
                    binding.cell9.text = "X"
                    binding.resetButton.text = "한판 더"
                    binding.resetButton.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                "its O turn now" -> {
                    binding.info.text = "O의 차례입니다"
                    binding.cell9.text = "X"
                }
                "its X turn now" -> {
                    binding.info.text = "X의 차례입니다"
                    binding.cell9.text = "O"
                }
            }
        }

        binding.drawerButton.setOnClickListener{
            binding.root.openDrawer(binding.drawer)
        }

        val adapter = HistoryAdapter(listOf(
            History.TypeA(3),
            /*History.TypeB(bitmap)*/
        ))
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}