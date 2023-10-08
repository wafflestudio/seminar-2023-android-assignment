package com.example.assignment2

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.assignment2.MainModel


class MainViewModel() : ViewModel() {
    private val dataModel: MainModel = MainModel()
    private val _data = MutableLiveData<MutableList<ArrayList<Array<Int>>>>()
    val data: LiveData<MutableList<ArrayList<Array<Int>>>> get() = _data
    private val _boardData = MutableLiveData<MutableList<BoardData>>()
    val boardData : LiveData<MutableList<BoardData>> get() = _boardData

    init{
        initGame()
    }

    fun handleClickEvent(position: Int) {
        val turn = getTurn()
        Log.d("MV", "changed ${turn}")
        dataModel.updateBoard(turn, position)
        Log.d("MV", "2nd ${turn}")
        dataModel.updateGameStat(turn)
        Log.d("MV", "3rd ${turn}")
        addBoardDatas()
        Log.d("MV", "${boardData}")
        _data.setValue(dataModel.fetchDataList())
        dataModel.updateTurn(turn)
    }

    fun getTurn(): Int {
        return dataModel.getTurn()
    }

    fun getMark(position: Int): Char {
        return getBoard()[position]
    }

    fun getBoard(): CharArray {
        val board = dataModel.getBoard(getTurn())

        return board.map { cell ->
            when (cell) {
                0 -> ' '
                1 -> 'O'
                2 -> 'X'
                else -> ' ' // 예외 처리 - 유효하지 않은 경우 공백 문자
            }
        }.toCharArray()
    }

    fun getBoard(turn: Int): List<Char> {
        val board = dataModel.getBoard(getTurn())
        return board.map { cell ->
            when (cell) {
                0 -> ' '
                1 -> 'O'
                2 -> 'X'
                else -> ' ' // 예외 처리 - 유효하지 않은 경우 공백 문자
            }
        }.toCharArray().toList()
    }

    fun getGameStat(): MainModel.GameStats {
        return dataModel.getGameStat(getTurn())
    }

    fun getGameStat(turn: Int): MainModel.GameStats {
        return dataModel.getGameStat(turn)
    }

    fun fetchDataList(): MutableList<ArrayList<Array<Int>>> {
        return dataModel.fetchDataList()
    }

    fun getTitle(): String {
        val turn = getTurn()
        val gameStatus = dataModel.getGameStat(turn)
        val title = when (gameStatus) {
            MainModel.GameStats.O -> "O의 승리입니다"
            MainModel.GameStats.X -> "X의 승리입니다"
            MainModel.GameStats.P -> dataModel.getTeamMark(turn) + "의 차례입니다."
            MainModel.GameStats.D -> "무승부입니다"
            else -> "Error"
        }
        return title
    }

    fun initGame() {
        dataModel.initGame()
        _data.value = dataModel.fetchDataList() // _data의 값을 설정합니다.

        val newBoardDataList = mutableListOf<BoardData>() // 새로운 BoardData 리스트를 만듭니다.
        newBoardDataList.add(BoardData.ButtonText(false)) // 초기 데이터를 추가합니다.
        _boardData.value = newBoardDataList // _boardData의 값을 설정합니다.
    }

    fun addBoardDatas() {
        val turn = getTurn()
        val tempBoardData = _boardData.value
        tempBoardData!!.add(BoardData.TurnNumber(turn))
        Log.d("MV", "${getBoard(turn)}")
        tempBoardData!!.add(BoardData.BoardValue(getBoard(turn)))
        tempBoardData!!.add(BoardData.ButtonText(getGameStat(turn).isGameFinished()))
        _boardData.setValue(tempBoardData)
    }

    fun getBoardDatas(): MutableList<BoardData>? {

        return _boardData.value
    }

    fun observeBoardData(owner: LifecycleOwner, observer: Observer<MutableList<BoardData>>) {
        boardData.observe(owner, observer)
    }

}