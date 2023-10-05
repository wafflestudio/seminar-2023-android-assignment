package com.example.assignment2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.ActivityMainBinding
import com.example.assignment2.databinding.DrawerBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    class MyData(val num:Int, val O_lst: MutableList<Int>, val X_lst: MutableList<Int>)

    //맨 마지막에 if(finished) 모두 unclickable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var adapter : MyAdapter

        //1. binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2. clear button event
        val button = binding.button
        button.setOnClickListener {
            viewModel.turn = true
            viewModel.count = 0

            viewModel.O_lst.clear()
            viewModel.X_lst.clear()
            writeOX(null, null)

            viewModel.conditionText = "O의 차례입니다"
            binding.condition.text = viewModel.conditionText
            viewModel.finished = false
            changeButton(viewModel.finished)

            //save for recycler view
            viewModel.items.clear()
            adapter = MyAdapter(viewModel.items)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }

        //3. tic-tac-to button event
        val gridLayout = binding.tictacto
        for(i in 0..8){
            var child = gridLayout.getChildAt(i) as TextView
            child.setOnClickListener{
                viewModel.conditionText =
                    when(viewModel.turn) {
                        false -> "X의 차례입니다"
                        true -> "O의 차례입니다"
                    }
                binding.condition.text = viewModel.conditionText

                var result : Int = -1 //1: someone wins, 0: draw
                if(++viewModel.count==9) result = 0
                if(viewModel.turn) {
                    child.text = "O"
                    viewModel.O_lst.add(i)
                    if(hasFinished(viewModel.O_lst)) result = 1
                }
                else {
                    child.text = "X"
                    viewModel.X_lst.add(i)
                    if(hasFinished(viewModel.X_lst)) result = 1
                }

                //check result
                if(result!=-1){
                    viewModel.finished = true
                    changeViewFinish(viewModel.finished)
                    viewModel.conditionText =
                        when(result){
                            0 -> "무승부"
                            else -> "게임 오버"
                        }
                    binding.condition.text = viewModel.conditionText
                }
                else {
                    child.isClickable = false
                    viewModel.turn = !viewModel.turn
                }

                //save for recycler view
                val data = MyData(viewModel.count, viewModel.O_lst.toMutableList(), viewModel.X_lst.toMutableList())
                viewModel.items.add(data)
                adapter = MyAdapter(viewModel.items)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
            }
        }

        //4. drawer button event
        val drawerButton = binding.menu
        drawerButton.setOnClickListener{
            binding.root.openDrawer(binding.drawer)
        }

        //0. 기본 구성
        adapter = MyAdapter(viewModel.items)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.condition.text = viewModel.conditionText
        writeOX(viewModel.O_lst, viewModel.X_lst)
        changeViewFinish(viewModel.finished)
    }

    fun changeButton(finished : Boolean){
        val button = binding.button
        if(finished){
            button.text = "한판 더"
            button.setBackgroundResource(R.drawable.rectangle_blue)
        }else{
            button.text = "초기화"
            button.setBackgroundResource(R.drawable.rectangle_gray)
        }
    }

    fun writeOX(O_lst : MutableList<Int>?, X_lst : MutableList<Int>?){
        val gridLayout = binding.tictacto
        if(O_lst==null || X_lst==null){
            for(i in 0..8){
                var child = gridLayout.getChildAt(i) as TextView
                child.text = ""
                child.isClickable = true
            }
        }else{
            for(i in 1..O_lst.size){
                val child = gridLayout.getChildAt(O_lst[i-1]) as TextView
                child.text = "O"
                child.isClickable = false
            }
            for(i in 1..X_lst.size){
                val child = gridLayout.getChildAt(X_lst[i-1]) as TextView
                child.text = "X"
                child.isClickable = false
            }
        }
    }

    fun changeViewFinish(finished : Boolean) {
        if(finished) {
            //1. 모든 사각형 unclickable
            val gridLayout = binding.tictacto
            for (i in 1..gridLayout.childCount) {
                var child = gridLayout.getChildAt(i - 1) as TextView
                child.isClickable = false
            }

            //2. 한판더버튼 활성화
            changeButton(true)
        }
    }

    fun hasFinished(gameList : MutableList<Int>) : Boolean {
        val winList = listOf(
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )
        for(lst in winList){
            var count = 0
            for(num in lst){
                for(comp in gameList){
                    if(num==comp){
                        count++
                        break
                    }
                }
                if(count==3) return true
            }
        }
        return false
    }
}
