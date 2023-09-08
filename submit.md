# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!!

![Giraffe 실행 화면](https://github.com/gazi52/seminar-2023-android-assignment/assets/142711741/4bdf6969-f152-4640-bb1b-be2d69106d54)

## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
fun main() {
	val nm = readln().split(" ").map {it.toInt()}
    val n = nm[0]
    val m = nm[1]

    schedule.info.clear()

    for( i in 1..n){
        val inputList: List<Int> = readln().split(" ").map {it.toInt()}
        schedule.info.add(inputList)
    }

    val castSchedule: List<List<Int>> = schedule.info

    val possibleLesson: MutableList<List<Int>> = mutableListOf(listOf(0))
    possibleLesson.clear()

    for(i in 0..m-1) {
        val newList = readln().split(" ").map { it.toInt() }
        possibleLesson.add(newList)
    }

    val pos = possibleLesson.filterNot{
        schedule.ifAdd(it, castSchedule, n) //겹치는 게 있을 때 true
        }

    val ans = pos.sortedWith(compareBy<List<Int>>({it[1]}, {it[2]}, {it[3]}, {it[0]}))

    when(ans.count()){
        0 -> print('0')
        else -> for(i in 1..ans.count()){
            println(ans[i-1][0])
        }
    }
}
```

## 3. 쌍포는 와플도 녹인다 코드

여기를 채워 주세요.
```kotlin
data class Point(
    val x: Int,
    val y: Int,
)

sealed class Piece(open val pos: Point, open val team: Boolean)  // team이 true이면 우리 편 기물
data class Pho(override val pos: Point, override val team: Boolean) : Piece(pos, team)
data class Cha(override val pos: Point, override val team: Boolean) : Piece(pos, team)
data class Jol(override val pos: Point, override val team: Boolean) : Piece(pos, team)
data class King(override val pos: Point, override val team: Boolean) : Piece(pos, team)

var px: Int = 0
var py: Int = 0
var epx: Int = 0
var epy: Int = 0
var flag = true
var cnt = 0
fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    for (i in 0..9){
        for (j in 0..8){
            if(board[i][j] == Pho(Point(i, j), true)) {px = i; py = j;}
            if(board[i][j] == Pho(Point(i, j), false)) {epx = i; epy = j;}
        }
    }

    when(board[next.x][next.y]?.team){
        true -> return false
        false -> if(board[next.x][next.y] == Pho(next, false)) return false
        null -> flag = true
    }

    when{
        next.x == px ->
            when{
            py >= next.y ->
                for(y in next.y..<py) {
                    if(px == epx && y == epy) return false
                    else if(board[px][y] != null) cnt++
                    }
            py < next.y ->
                for(y in py..< next.y) {
                    if(px == epx && y == epy) return false
                    else if(board[px][y] != null) cnt++
                }
            }

        next.y == py ->
            when{
                px >= next.x ->
                    for(x in next.x..<px) {
                        if(x == epx && py == epy) return false
                        else if(board[x][py] != null) cnt++
                    }
                px < next.x ->
                    for(x in px..< next.x) {
                        if(x == epx && py == epy) return false
                        else if(board[x][py] != null) cnt++
                    }
            }
        else -> return false
    }

    when(cnt){
        1 -> return flag
        else -> return false
    }
}
```

