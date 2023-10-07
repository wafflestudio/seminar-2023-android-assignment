package com.example.assignment2

// GameState는 현재 게임의 전체적인 정보를 담고 있다.
class GameState(val turn: Int, val progress: Progress, val board: Array<Array<Progress>>) {

    // 바뀐 GameBoard
    private lateinit var boardChanged: Array<Array<Progress>>

    // 다음 GameState를 만들어 주는 함수
    fun nextGameState(row: Int, col: Int): GameState {
        boardChanged = nextBoard(row, col)
        return GameState(turn + 1, nextProgress(), boardChanged)
    }

    // 다음 progress를 만들어 주는 함수
    private fun nextProgress(): Progress {
        return when (winnerExists()) {
            "O" -> Progress.OWINS
            "X" -> Progress.XWINS
            else -> {
                if (turn == 8) Progress.DRAW // 이게 마지막 턴인데 승자가 없을 경우
                else when (progress) {
                    Progress.OPLAYING -> Progress.XPLAYING
                    Progress.XPLAYING -> Progress.OPLAYING
                    else -> Progress.NULL // This doesn't happen.
                }
            }
        }
    }

    // 승자가 있는지 확인하는 함수
    private fun winnerExists(): String{
        val checkHorizontal: Boolean = boardChanged.any { row -> row.all { it == progress } } // 가로 검사 - 방금 착수한 team이 이겼는지만 확인하면 된다.
        val checkVertical: Boolean = boardChanged.indices.map{ colIndex -> boardChanged.map{it[colIndex]} }.any { row -> row.all { it == progress } } // 세로 검사
        val checkDiagonal1: Boolean = boardChanged.indices.map{ idx -> boardChanged[idx][idx] }.all { it == progress } // 대각선 검사 1
        val checkDiagonal2: Boolean = boardChanged.indices.map{ idx -> boardChanged[boardChanged.size - idx - 1][idx] }.all { it == progress } // 대각선 검사 2

        return if (checkHorizontal || checkVertical || checkDiagonal1 || checkDiagonal2) progress.symbol()
        else return ""
    }

    // board를 deepcopy해서 옮기기 위한 함수 - 이전 기록을 변경하지 않기 위함임
    private fun deepCopy(original: Array<Array<Progress>>): Array<Array<Progress>> {
        return Array(original.size) { row ->
            Array(original[row].size) { col ->
                original[row][col]
            }
        }
    }

    // 다음 board를 만들어 주는 함수
    private fun nextBoard(row: Int, col: Int): Array<Array<Progress>> {
        val newBoard = deepCopy(board)
        newBoard[row][col] = progress
        return newBoard
    }

    // GameState를 MyMultiData로 변환해주는 함수
    fun toMyMultiData(): MyMultiData {
        return if(turn == 0) MyMultiData.Start()
        else if(progress.isFinished()) MyMultiData.Finish(this)
        else MyMultiData.Main(this)
    }

}