package com.example.tictac

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.tictac.databinding.ActivityMainBinding
import com.example.tictac.databinding.HistoryBinding

class MainViewModel: ViewModel() {

    private val _myHistory: MutableLiveData<MutableList<MyData>> = MutableLiveData(mutableListOf())
    val myHistory: LiveData<MutableList<MyData>> = _myHistory

    var turn=1
    var status="O의 차례입니다"
    val numbers = Array(9) { 0 }//0은 비어있음, 1은 o, 2는 x
    val order = Array(9) { 0 }//클릭이 된 순서
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

    fun addData(new:MyData){
        val currentList = _myHistory.value!!
        currentList.add(new)
        _myHistory.value = currentList
        Log.d("aaaa",_myHistory.value.toString())
    }

    fun resetDrawer(){
        _myHistory.value= mutableListOf()
    }

    fun getButtonNum(textView: TextView,binding: ActivityMainBinding):Int{
        return when (textView){
            binding.button00->0
            binding.button01->1
            binding.button02->2
            binding.button10->3
            binding.button11->4
            binding.button12->5
            binding.button20->6
            binding.button21->7
            binding.button22->8
            else -> 0
        }
    }
    fun getButtonNum2(textView: TextView,binding: HistoryBinding):Int{
        return when (textView){
            binding.historyButton0->0
            binding.historyButton1->1
            binding.historyButton2->2
            binding.historyButton3->3
            binding.historyButton4->4
            binding.historyButton5->5
            binding.historyButton6->6
            binding.historyButton7->7
            binding.historyButton8->8
            else -> 0
        }
    }
    fun tryClick(location:Int,binding:ActivityMainBinding):Boolean{
        if(numbers[location]!=0||gameEnded) return false
        order[count]=location
        count+=1
        numbers[location]=turn
        //binding.logs.text=numbers.toString()
        if(turn==1) status="X의 차례입니다"
        else status="O의 차례입니다"
        binding.whoseturn.text=status
        turn=3-turn
        for (st in straight){
            val same=numbers[st[0]]
            if(same==0)continue
            var allsame=1
            for(i in st){
                if(same!=numbers[i]) allsame=0
            }
            if(allsame==1){
                status="게임 오버"
                binding.whoseturn.text=status
                binding.reset.text="한판 더"
                gameEnded=true
            }
        }
        if(!gameEnded&&count==9){
            status="무승부"
            binding.whoseturn.text=status
            binding.reset.text="한판 더"
            gameEnded=true
        }
        return true
    }
}