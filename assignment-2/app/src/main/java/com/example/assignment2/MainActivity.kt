package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val viewModel:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slots:List<TextView> =listOf(binding.slot11,binding.slot12,binding.slot13,binding.slot21,
            binding.slot22,binding.slot23,binding.slot31,binding.slot32,binding.slot33)
        val textviews:List<TextView> =listOf(binding.resetbutton)

        val game=Game(slots,textviews,viewModel,0,binding)
        game.reset(viewModel.game_data[0])

        binding.slot11.setOnClickListener{
            game.process(1)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot12.setOnClickListener{
            game.process(2)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot13.setOnClickListener{
            game.process(3)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot21.setOnClickListener{
            game.process(4)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot22.setOnClickListener{
            game.process(5)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot23.setOnClickListener{
            game.process(6)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot31.setOnClickListener{
            game.process(7)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot32.setOnClickListener{
            game.process(8)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.slot33.setOnClickListener{
            game.process(9)
            binding.history.adapter=Adapter(list=viewModel.recyclerview_data,this)
        }
        binding.resetbutton.setOnClickListener{game.reset()}
        binding.drawerbutton.setOnClickListener{binding.root.openDrawer(binding.drawer)}

        val adapter=Adapter(list=viewModel.recyclerview_data,this)

        binding.history.adapter=adapter
        binding.history.layoutManager=LinearLayoutManager(this)
    }
}

class Game(s:List<TextView>,t:List<TextView>,m:MyViewModel,i:Int,b:ActivityMainBinding){
    var board =mutableListOf(0,0,0,0,0,0,0,0,0)
    var history:MutableList<Int> = emptyList<Int>().toMutableList()
    var gamekey:Int=0
    val slots:List<TextView> =s
    val textviews:List<TextView> =t
    val checks=listOf(listOf(0,1,2),listOf(3,4,5),listOf(6,7,8),listOf(0,3,6),listOf(1,4,7)
        ,listOf(2,5,8),listOf(0,4,8),listOf(2,4,6))
    val mod:MyViewModel=m
    val ind:Int=i
    val bind:ActivityMainBinding=b
    fun game_finish_check():Boolean{
        var key=0
        this.checks.forEach{
            if(this.gamekey==0) {
                this.gamekey = when (this.board.slice(it)) {
                    listOf(-1, -1, -1) -> -1
                    listOf(1, 1, 1) -> 1
                    else -> 0
                }
            }
        }
        when (this.gamekey!=0){
            true -> {
                textviews[0].text="한판 더"
                return true
            }
            false -> {
                textviews[0].text="초기화"
                return false
            }
        }
    }
    fun turn(): Int {
        return (10-this.board.count{it==0})
    }
    fun process(num:Int){
        this.history.add(num)
        if(this.gamekey==0 && board[num-1]==0){
            mod.game_data[ind]=this.history
            mod.recyclerview_data=mutableListOf(
                MyMultiData.TypeA(mod.game_data[ind])
            )
            Log.d("mmmm",mod.recyclerview_data.toString())
            when(this.turn()%2){
                0 -> {this.slot_change(num,"X")}
                else -> {this.slot_change(num,"O")}
            }
        }
    }
    fun slot_change(num:Int,tochange:String){
        this.board[num-1]=(this.turn()%2)*2-1
        this.slots[num-1].text=tochange
        this.game_finish_check()
    }
    fun reset(h: List<Int> =emptyList()){
        for (i in 1..9){
            this.slot_change(i,"")
        }
        this.board=mutableListOf(0,0,0,0,0,0,0,0,0)
        this.history=h.toMutableList()
        this.gamekey=0
        var turnkey:Int=0
        this.history.forEach{
            this.slot_change(it,when(turnkey==0){
                true -> "O"
                false -> "X"
            })
            turnkey=1-turnkey
        }
        this.textviews[0].text="초기화"
    }
}
