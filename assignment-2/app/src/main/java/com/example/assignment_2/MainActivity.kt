package com.example.assignment_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import androidx.drawerlayout.widget.DrawerLayout
import com.example.assignment_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        binding.openDrawer.setOnClickListener {
            binding.drawer.open()
        }



        var turn=1
        val numbers = Array(9) { 0 }//0은 비어있음, 1은 o, 2는 x
        val straight=arrayOf(
            arrayOf(0,1,2),
            arrayOf(0,3,6),
            arrayOf(0,4,8),
            arrayOf(1,4,7),
            arrayOf(2,4,6),
            arrayOf(2,5,8),
            arrayOf(3,4,5),
            arrayOf(6,7,8))
        var gameEnded=false
        var count=0
        fun tryClick(location:Int):Boolean{
            if(numbers[location]!=0||gameEnded) return false
            count+=1
            numbers[location]=turn
            //binding.logs.text=numbers.toString()
            if(turn==1) binding.whoseturn.text="X의 차례입니다"
            else binding.whoseturn.text="O의 차례입니다"
            turn=3-turn
            for (st in straight){
                val same=numbers[st[0]]
                if(same==0)continue
                var allsame=1
                for(i in st){
                    if(same!=numbers[i]) allsame=0
                }
                if(allsame==1){
                    binding.whoseturn.text="게임 오버"
                    binding.reset.text="한판 더"
                    gameEnded=true
                }
            }
            if(!gameEnded&&count==9){
                binding.whoseturn.text="무승부"
                binding.reset.text="한판 더"
                gameEnded=true
            }
            return true
        }
        //binding.button00.text = "hi!"
        binding.button00.setOnClickListener {
            if(tryClick(0))
            {
                if(turn==1)binding.button00.text="X"
                else binding.button00.text="O"
            }
        }
        binding.button01.setOnClickListener {
            if(tryClick(1))
            {
                if(turn==1)binding.button01.text="X"
                else binding.button01.text="O"
            }
        }
        binding.button02.setOnClickListener {
            if(tryClick(2))
            {
                if(turn==1)binding.button02.text="X"
                else binding.button02.text="O"
            }
        }
        binding.button10.setOnClickListener {
            if(tryClick(3))
            {
                if(turn==1)binding.button10.text="X"
                else binding.button10.text="O"
            }
        }
        binding.button11.setOnClickListener {
            if(tryClick(4))
            {
                if(turn==1)binding.button11.text="X"
                else binding.button11.text="O"
            }
        }
        binding.button12.setOnClickListener {
            if(tryClick(5))
            {
                if(turn==1)binding.button12.text="X"
                else binding.button12.text="O"
            }
        }
        binding.button20.setOnClickListener {
            if(tryClick(6))
            {
                if(turn==1)binding.button20.text="X"
                else binding.button20.text="O"
            }
        }
        binding.button21.setOnClickListener {
            if(tryClick(7))
            {
                if(turn==1)binding.button21.text="X"
                else binding.button21.text="O"
            }
        }
        binding.button22.setOnClickListener {
            if(tryClick(8))
            {
                if(turn==1)binding.button22.text="X"
                else binding.button22.text="O"
            }
        }

        binding.reset.setOnClickListener {

            gameEnded=false

            binding.button00.text=""
            binding.button01.text=""
            binding.button02.text=""
            binding.button10.text=""
            binding.button11.text=""
            binding.button12.text=""
            binding.button20.text=""
            binding.button21.text=""
            binding.button22.text=""

            turn=1

            for(i in 0..8){
                numbers[i]=0
            }

            binding.whoseturn.text="O의 차례입니다"
            binding.reset.text="초기화"
            count=0

        }
    }
}
