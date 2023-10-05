package com.example.tictac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.forEach

import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictac.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //test

        /*
        items.add(1)
        binding.history.adapter = MyMultiAdapter(items,viewModel)
        binding.history.layoutManager = LinearLayoutManager(this)
        */



        //refresh
        if(viewModel.gameEnded)binding.reset.text="한판 더"
        binding.whoseturn.text=viewModel.status
        binding.buttons.forEach {
            val textView=it as TextView
            val buttonNum=viewModel.getButtonNum(textView,binding)
            textView.text=when (viewModel.numbers[buttonNum]){
                1->"O"
                2->"X"
                else->""
            }
        }
        drawDrawer()

        //drawer
        binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.openDrawer.setOnClickListener {
            binding.drawer.open()
        }




        //틱택토 버튼들 누름
        binding.buttons.forEach {
            val textView=it as TextView
            it.setOnClickListener {
                val buttonNum=viewModel.getButtonNum(textView,binding)
                if(viewModel.tryClick(buttonNum,binding))
                {
                    if(viewModel.turn==1)textView.text="X"
                    else textView.text="O"
                    val newBoardArray=Array(9) { 0 }
                    for(i in 0..buttonNum) newBoardArray[i]=viewModel.numbers[i]
                    val new=MyData(viewModel.count,newBoardArray,viewModel.gameEnded)
                    viewModel.items.add(new)
                    drawDrawer()

                }
            }
        }

        //리셋
        binding.reset.setOnClickListener {

            viewModel.gameEnded=false
            viewModel.resetDrawer()
            drawDrawer()
            binding.buttons.forEach {
                val textView=it as TextView
                textView.text=""
            }

            viewModel.turn=1

            for(i in 0..8){
                viewModel.numbers[i]=0
                viewModel.order[i]=0
            }

            binding.whoseturn.text="O의 차례입니다"
            binding.reset.text="초기화"
            viewModel.count=0

        }
    }
    fun drawDrawer(){
        binding.history.adapter = MyMultiAdapter(viewModel.items,viewModel)
        binding.history.layoutManager = LinearLayoutManager(this)
    }
}
