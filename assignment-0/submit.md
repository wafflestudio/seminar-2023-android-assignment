# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷
![image](https://github.com/wafflestudio/seminar-2023-android-assignment/assets/111198830/99e36d25-f629-4b7a-b776-cd88ed93f6e0)

## 2. SNUTT 강의 찾기 코드
```kotlin
data class Lecture(val n: Int, val d: Int, val s: Int, val e: Int){
    fun isOverlappingWith(lecture: Lecture): Boolean = when{
        d != lecture.d -> false
        s < lecture.e && e > lecture.s -> true
        else -> false
    }
}

fun main() {
    // Get inputs - n(# of userLectures), m(# of wholeLectures)
    var inLine = readLine()?.trim()?.split(" ")?.map{it.toInt()} ?: return
    val n = inLine[0]; val m = inLine[1]

    // Get inputs - userLectures, wholeLectures
    val userLectures = mutableListOf<Lecture>()
    val wholeLectures = mutableListOf<Lecture>()
    for (i in 1..n) {
        inLine = readLine()?.trim()?.split(" ")?.map{it.toInt()} ?: return
        userLectures.add(Lecture(inLine[0], inLine[1], inLine[2], inLine[3]))
    }
    for (i in 1..m) {
        inLine = readLine()?.trim()?.split(" ")?.map{it.toInt()} ?: return
        wholeLectures.add(Lecture(inLine[0], inLine[1], inLine[2], inLine[3]))
    }

    // Process inputs - find NOT overlapping lectures and sort by lecture number
    var availableLectures = wholeLectures.toList()
    for(i in 0 until n){
        availableLectures = availableLectures.filterNot{it.isOverlappingWith(userLectures[i])}
    }
    availableLectures = availableLectures.toMutableList()
    availableLectures.sortBy{it.n}

    // Print outputs - print # of availableLectures which is sorted
    for (item in availableLectures){
        println(item.n)
    }
    if (availableLectures.isEmpty()){
        println(0)
    }
}
```

## 3. 쌍포는 와플도 녹인다 코드
```kotlin
data class Point(
    val x: Int,
    val y: Int,
)

sealed class Piece(open val pos: Point, open val team: Boolean) {
    data class Pho(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class Cha(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class Jol(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class King(override val pos: Point, override val team: Boolean) : Piece(pos, team)
}

// A function which returns how many pieces between 'first' point and 'second' point.
// If the return value is larger than 100, that means there is opponent's pho.
fun between(board: Array<Array<Piece?>>, first: Point, second: Point): Int {
    var cnt = 0
    if (first.x == second.x){
        val list = listOf(first.x, second.x)
        val min = list.min()
        val max = list.max()
        for (x in (min+1) until max){
            val piece: Piece? = board[first.y][x] ?: continue
            if (piece is Piece.Pho && !piece.team) cnt += 100
            else cnt += 1
        }
    }
    else {
        val list = listOf(first.y, second.y)
        val min = list.min()
        val max = list.max()
        for (y in (min+1) until max){
            val piece: Piece? = board[y][first.x] ?: continue
            if (piece is Piece.Pho && !piece.team) cnt += 100
            else cnt += 1
        }
    }
    return cnt
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, dest: Point): Boolean {
    // Find my pho
    var curr: Point? = null  // Current position of my pho
    for (y in 0 until 10){
        for (x in 0 until 9){
            if (board[y][x] is Piece.Pho && board[y][x]?.team == true) {
                curr = Point(x, y)
            }
        }
    }

    // Piece at destination
    val pieceAtDest: Piece? = board[dest.y][dest.x]

    // Return possibility of moving our pho to destination
    return when{
        // Our pho and destination is not in parallel of perpendicular line.
        curr?.x != dest.x && curr?.y != dest.y -> false
        // Piece in destination is our team, or opponent's pho
        pieceAtDest?.team == true || (pieceAtDest is Piece.Pho && pieceAtDest?.team == false) -> false
        // Piece in destination is opponents team, and there is only one piece between two points.
        pieceAtDest?.team == false && between(board, curr, dest) == 1 -> true
        // There is no piece in destination, and there is only one piece between two points.
        pieceAtDest?.team == null && between(board, curr, dest) == 1 -> true
        else -> false
    }
}
```
