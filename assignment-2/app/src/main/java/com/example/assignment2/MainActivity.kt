package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hamburgerMenu.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }

        val adapter = BoardAdapter(this, viewModel)
        binding.gameBoard.adapter = adapter

        binding.initBtn.setOnClickListener {
            viewModel.init()
        }


        viewModel.state.observe(this){
            binding.turnInformation.text = when(it){
                State.GAMEOVER -> "게임 오버"
                State.DRAW -> "무승부"
                State.PLAYER1 -> "O의 차례입니다"
                State.PLAYER2 -> "X의 차례입니다"
            }
            binding.initBtn.text = when(it){
                State.GAMEOVER, State.DRAW -> "한판더"
                else -> "초기화"
            }
        }


        val historyAdapter = HistoryAdapter(this, viewModel.history.value!!, viewModel)
        binding.recyclerView.adapter = historyAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //viewModel의 history를 observe해서, adapter에 notifyDataSetChanged()를 호출하는 방법 사용시
        //viewModel.init() 호출시, history의 reference가 그대로여서 바뀐것을 인식 못함
        //viewModel.init()에서, reference를 바꾸기 위해 history.value = MutableList()를 사용시
        //기존어댑터에 전달한 레퍼런스가 아닌 다른 레퍼런스를 참고하는? 이유는 잘 모르겠지만....
        //init()을 호출할 경우 서랍안의 recyclerView가 고장남...
        //그래서 아래와 같이 어차피 board가 바뀌면, history도 같이 바뀌어야 하니까,
        //notifyDataSetChanged를 board.observe로 옮겼습니다ㅜㅜ
        viewModel.board.observe(this, Observer {
            adapter.notifyDataSetChanged()
            historyAdapter.notifyDataSetChanged()
        })

    }



}

