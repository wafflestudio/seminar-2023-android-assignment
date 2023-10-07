package com.example.assignment2

sealed class MyMultiData {
    class Start: MyMultiData()
    data class Main(val gameState: GameState): MyMultiData()
    data class Finish(val gameState: GameState): MyMultiData()
    enum class ViewType{ START, MAIN, FINISH }
}