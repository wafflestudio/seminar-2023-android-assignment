package com.example.assignment2

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.forEachIndexed
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // MyViewModel class의 instance인 viewModel을 만들고, 'by viewModels()'로 생성을 위임한다. MyViewModel.kt가 MainActivity.kt와 같은 package에 있어서 import 안 해도 된다.
    private val viewModel: MyViewModel by viewModels()
    @SuppressLint("NotifyDataSetChanged") // 초기화 되거나 되돌아가기 버튼을 사용할 때에 대비하여 그냥 전체 데이터가 바뀌었는지를 알려주는 함수를 사용함.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also { this.binding = it }
        setContentView(binding.root)

        // Click이 들어오면 GameState를 바꿈 - 유효한 클릭인지에 따라 바뀌지 않을 수도 있음
        binding.button0.setOnClickListener { viewModel.changeGameState(0, 0) }
        binding.button1.setOnClickListener { viewModel.changeGameState(0, 1) }
        binding.button2.setOnClickListener { viewModel.changeGameState(0, 2) }
        binding.button3.setOnClickListener { viewModel.changeGameState(1, 0) }
        binding.button4.setOnClickListener { viewModel.changeGameState(1, 1) }
        binding.button5.setOnClickListener { viewModel.changeGameState(1, 2) }
        binding.button6.setOnClickListener { viewModel.changeGameState(2, 0) }
        binding.button7.setOnClickListener { viewModel.changeGameState(2, 1) }
        binding.button8.setOnClickListener { viewModel.changeGameState(2, 2) }
        binding.reset.setOnClickListener {
            viewModel.backTo(1)
            binding.reset.apply{
                text = "초기화"
                setBackgroundColor(Color.parseColor("#C2BEC0"))
            }
        }

        // drawer image가 클릭되면 drawer 열기
        binding.drawerIcon.setOnClickListener { binding.root.openDrawer(binding.drawer) }

        // RecyclerView에 adpater 연결
        binding.recyclerView.adapter = MyMultiAdapter( viewModel.history.map{ it.toMyMultiData() }, { turn: Int -> viewModel.backTo(turn) } )

        // currentGameState에 변화가 생기면 할 일들
        viewModel.currentGameState.observe(this) {
            // 현재 GameState의 progress와 board를 가져옴
            val progress: Progress = it.progress
            val board: Array<Array<Progress>> = it.board

            // game information 갱신
            binding.information.text = progress.toString()

            // board의 각 부분을 변경하는 함수
            binding.gridLayout.forEachIndexed { idx, button ->
                (button as TextView).text = board[idx/3][idx%3].symbol()
                (button).setBackgroundColor(board[idx/3][idx%3].color())
            }

            // drawer 뷰에 변경 사항을 알려주는 함수
            binding.recyclerView.adapter!!.notifyDataSetChanged()

            // 게임이 끝났다면 reset UI의 정보를 변경
            if (progress.isFinished()) {
                binding.reset.apply{
                    text = "한판더"
                    setBackgroundColor(Color.parseColor("#107AC0"))
                }
            }
        }

    }

}