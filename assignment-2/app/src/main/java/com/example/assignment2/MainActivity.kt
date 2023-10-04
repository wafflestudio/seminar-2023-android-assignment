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

    var turn = true
    var count = 0
    var O_lst = mutableListOf<Int>()
    var X_lst = mutableListOf<Int>()
    var finished = false

    class DrawerViewHolder(private val binding: DrawerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : MainActivity.MyData){
            binding.turnNumber.text = data.num.toString() + "턴"

            val gridLayout = binding.miniTictacto
            for(i in 1..data.O_lst.size){
                val child = gridLayout.getChildAt(data.O_lst[i-1]) as TextView
                child.text = "O"
            }
            for(i in 1..data.X_lst.size){
                val child = gridLayout.getChildAt(data.X_lst[i-1]) as TextView
                child.text = "X"
            }
        }
    }

    class MyData(val num:Int, val O_lst: MutableList<Int>, val X_lst: MutableList<Int>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        turn = true
        count = 0
        O_lst = mutableListOf<Int>()
        X_lst = mutableListOf<Int>()
        finished = false

        var items = mutableListOf<MyData>()
        var adapter = MyAdapter(items)

        //0. binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1. screen change
        val currentContext = viewModel.currentContext
        if(currentContext!=null){
            turn = viewModel.turn
            count = viewModel.count

            O_lst = viewModel.O_lst
            X_lst = viewModel.X_lst
            writeOX(O_lst, X_lst)

            binding.condition.text = viewModel.conditionText

            finished = viewModel.finished
            if(finished) changeViewFinish()

            items = viewModel.items
            adapter = MyAdapter(items)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }

        //2. clear button event
        val button = binding.button
        button.setOnClickListener {
            turn = true
            count = 0
            O_lst = mutableListOf<Int>()
            X_lst = mutableListOf<Int>()
            writeOX(null, null)
            binding.condition.text = "O의 차례입니다"
            changeButton(-1)
            finished = false

            //save for recycler view
            items.clear()
            adapter = MyAdapter(items)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)

            //save for screen convert
            viewModel.turn = turn
            viewModel.count = count
            viewModel.O_lst = O_lst
            viewModel.X_lst = X_lst
            viewModel.finished = finished
            viewModel.conditionText = binding.condition.text as String
            viewModel.items = items
            viewModel.currentContext = this
        }

        //3. tic-tac-to button event
        val gridLayout = binding.tictacto
        for(i in 0..8){
            var child = gridLayout.getChildAt(i) as TextView
            child.setOnClickListener{
                if(turn) binding.condition.text = "X의 차례입니다"
                else binding.condition.text = "O의 차례입니다"

                var result : Int = -1 //1: someone wins, 0: draw
                if(++count==9) result = 0
                if(turn) {
                    child.text = "O"
                    O_lst.add(i)
                    if(hasFinished(O_lst)) result = 1
                }
                else {
                    child.text = "X"
                    X_lst.add(i)
                    if(hasFinished(X_lst)) result = 1
                }

                //check result
                if(result!=-1){
                    changeViewFinish()
                    finished = true
                    if(result==0) binding.condition.text = "무승부"
                    else binding.condition.text = "게임 오버"
                }
                else {
                    child.isClickable = false
                    turn = !turn
                }

                //save for recycler view
                val data = MyData(count, O_lst.toMutableList(), X_lst.toMutableList())
                items.add(data)
                adapter = MyAdapter(items)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)

                //save for screen convert
                viewModel.turn = turn
                viewModel.count = count
                viewModel.O_lst = O_lst
                viewModel.X_lst = X_lst
                viewModel.finished = finished
                viewModel.conditionText = binding.condition.text as String
                viewModel.items = items
                viewModel.currentContext = this
            }
        }

        //4. drawer button event
        val drawerButton = binding.menu
        drawerButton.setOnClickListener{
            binding.root.openDrawer(binding.drawer)
        }
    }

    fun changeButton(num : Int){
        val button = binding.button
        if(num==1){
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

    fun changeViewFinish() {
        //1. 모든 사각형 unclickable
        val gridLayout = binding.tictacto
        for(i in 1..gridLayout.childCount){
            var child = gridLayout.getChildAt(i-1) as TextView
            child.isClickable = false
        }

        //2. 한판더버튼 활성화
        changeButton(1)
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
